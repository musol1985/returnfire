package com.returnfire.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.entity.anot.RunOnGLThread;
import com.entity.network.core.service.impl.ClientNetWorldService;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.buildings.EdificioExtractorDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.buildings.BuildModel;
import com.returnfire.models.elementos.buildings.EdificioAlmacenModel;
import com.returnfire.models.elementos.buildings.IAlmacenable;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.models.elementos.bullets.BulletModel;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.elementos.vehicles.VehiculoRecolectorModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;
import com.returnfire.msg.MsgBuild;
import com.returnfire.msg.MsgOnBalaEstatico;
import com.returnfire.msg.MsgOnBuilding;
import com.returnfire.msg.MsgOnContenedorEdificio;
import com.returnfire.msg.MsgOnDisparar;
import com.returnfire.msg.MsgOnEdificioConstruido;
import com.returnfire.msg.MsgOnRecursoToVehiculo;
import com.returnfire.msg.MsgOnSyncRecursos;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;
import com.returnfire.msg.MsgOnVehiculoRecolecta;
import com.returnfire.msg.MsgRecursoToVehiculo;

public class ClientMundoService extends ClientNetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO>{
    @Override
    public Class<CeldaModel> getCellClass() {
        return CeldaModel.class;
    }

	@Override
	public Class<MundoModel> getWorldClass() {
		return MundoModel.class;
	}


	@Override
	public Class<JugadorDAO> getPlayerDAOClass() {
		return JugadorDAO.class;
	}


	@Override
	public void onNewPlayerDAO(JugadorDAO player) {
		player.setPosition(new Vector3f());
	}

    @Override
    public Class<JugadorModel> getPlayerClass() {
        return JugadorModel.class;
    }

	@Override
	public int getCellCacheSize() {
		return 20;
	}

        @RunOnGLThread
	public void onDisparar(MsgOnDisparar onDisparar)throws Exception{
		//Crea la bala, pero a diferencia dle server, no la attacha
		BulletModel bala=getWorld().getFactory().balasFactory.crearBala(getWorld(), onDisparar.from, onDisparar.tipo, onDisparar);
		//Solo attach si esta en rango de vision
		if(bala!=null){
			if(bala.getWorldTranslation().distance(player.getVehiculo().getWorldTranslation())<100f){
				getWorld().addBala(bala);
			}
		}
	}
	
    @RunOnGLThread
    public void onImpactoBala(String bala)throws Exception{
    	getWorld().getBalas().eliminar(bala);
    }
    
    @RunOnGLThread
    public void onImpactoBalaEstatico(MsgOnBalaEstatico msg)throws Exception{
    	getWorld().getBalas().eliminar(msg.idBala);
        
        CeldaModel cell=getCellById(msg.cellId.id);
        if(cell!=null){
            if(msg.vida>0){
                cell.getEstatico(msg.idEstatico).getDAO().setVida(msg.vida);
            }else{
                //cell.eliminarEstatico(msg.idEstatico);
                cell.getEstatico(msg.idEstatico).eliminar(msg.v);
            }
            //Si el timestamp actual es igual al de antes de eliminar el estatico en el server
            //quiere decir que estamos sincronizados, eliminamos y actualizamos el timestamp.
            if(cell.dao.getId().timestamp==msg.celdaTimestampOld){
            	cell.dao.getId().timestamp=msg.cellId.timestamp;
            	cell.save();
            }
        }
    }
    
    public void construirEdificio(BuildModel model)throws Exception{    	
    	new MsgBuild(model.getEdificio().getDAO(), model.getEdificio().getClass(), model.getWorldTranslation(), 0, getPlayer().dao.getId()).send();
    }
    
    public void errorConstruir(String edificio)throws Exception{
    	log.warning("Error al constuir un edificio: "+edificio);
    	//Volvemos a hacer drag de ese tipo de edificio ya que seguramente en el server se haya puesto otro edificio en esa misma pos
    	getWorld().buildDrag.setEdificio((Class<? extends BuildNode>) Class.forName(edificio));
    }
    
    @RunOnGLThread
    public void onConstruyendoEdificio(MsgOnBuilding msg)throws Exception{
    	CeldaModel celda=getCellById(msg.cellId.id);
    	celda.addConstruyendoEdificio(msg.edificio, true, true);
    }
    
    @RunOnGLThread
    public void onVehiculoCogeContenedor(MsgOnVehiculoCogeContenedor msg)throws Exception{
		CeldaModel celda=getCellById(msg.cellId.id);
		
		VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
		if(v==null)
			throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
		
		ContenedorModel c=celda.getContenedor(msg.contenedorId);
		if(c==null)
			throw new Exception("Contenedor con id: "+msg.contenedorId+" no encontrado en "+msg.cellId.id);
		
		if(!v.isTransporte())
			throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un transporte y no puede coger un contenedor!");
		
		VehiculoTransporteModel vt=(VehiculoTransporteModel)v;
		vt.cogeContenedor(c, celda);
	}
    
    /**
	    * Envia al servidor que tiene que a�adir ese recurso
	    * Se comprueba que se pueda a�adir ese recurso
	    * 
	    * @param edificio
	    * @param recurso
	    * @param all
	    * @return la cantidad de recursos added
	    */
    public int addRecursoToEdificio(CeldaDAO celda, EdificioAlmacenDAO daoEdificio, VehiculoTransporteDAO daoVehiculo, RECURSOS tipoRecurso, boolean all){
        List<Long> contenedoresToAdd=new ArrayList<Long>();
        
        if(daoEdificio instanceof EdificioExtractorDAO)
            return 0;
        
        boolean seguir=true;
        Iterator<ContenedorDAO> it=daoVehiculo.getContenedoresByTipoRecurso(tipoRecurso).iterator();
        int cantidadPuedeAlmacenar=daoEdificio.getCantidadQuePuedeAlmacenar(tipoRecurso);
        
        while(it.hasNext() && seguir){
        	ContenedorDAO c=it.next();

			if(cantidadPuedeAlmacenar>0){
				cantidadPuedeAlmacenar--;
				contenedoresToAdd.add(c.getIdLong());
				if(!all)
					seguir=false;
			}

        }

        new MsgOnContenedorEdificio(celda.getId(), daoVehiculo.getIdLong(), daoEdificio.getId(), contenedoresToAdd).send();
        
        return contenedoresToAdd.size();
    }
    
    /**
	    * Envia al servidor que tiene que a�adir ese recurso
	    * Se comprueba que se pueda a�adir ese recurso
	    * 
	    * @param edificio
	    * @param recurso
	    * @param all
	    * @return la cantidad de recursos added
	    */
	 public int addRecursoToVehiculo(CeldaDAO celda, EdificioAlmacenDAO daoEdificio, VehiculoTransporteDAO daoVehiculo, RECURSOS tipoRecurso, boolean all){
	     int cantidadPuedeAlmacenar=daoVehiculo.getMaxSlots()-daoVehiculo.getContenedoresSize();
	     
	     if(cantidadPuedeAlmacenar>0 && daoEdificio.getCantidadRecursoByTipo(tipoRecurso)>0){
	    	 int cantidadQueSeAlmacenara=1;
	    	 
	    	 if(all)
	    		 cantidadQueSeAlmacenara=cantidadPuedeAlmacenar;
	    	 
	    	 new MsgRecursoToVehiculo(celda.getId(), daoVehiculo.getIdLong(), daoEdificio.getId(), tipoRecurso, cantidadQueSeAlmacenara).send();	    		        
	    	 
	    	 return cantidadQueSeAlmacenara;
	     }
	     
	     return 0;
	 }
    
    @RunOnGLThread
    public void onContenedorEdificio(MsgOnContenedorEdificio msg)throws Exception{
        CeldaModel celda=getCellById(msg.cellId.id);
        ConstruyendoModel e=(ConstruyendoModel)celda.getEdificio(msg.edificioId);
        VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
        if(v==null)
                throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
        if(!v.isTransporte())
        	throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un transporte y no puede coger un contenedor!");		
        VehiculoTransporteModel vt=(VehiculoTransporteModel)v;
        
        for(Long cId:msg.contenedorId){
            ContenedorModel<ContenedorDAO> c=vt.getContenedorById(cId);
            if(c==null)
                throw new Exception("Contenedor con id: "+cId+" no encontrado en "+msg.cellId.id);

            if(e.getDAO().puedeAlmacenarMas(c.getDAO().getTipo())){   
            	e.getDAO().addRecursoByTipo(c.getDAO().getTipo(), 1);
                vt.quitaContenedor(c);
            }
        }
    }
    
    @RunOnGLThread
    public void onVehiculoRecolecta(MsgOnVehiculoRecolecta msg)throws Exception{
        CeldaModel celda=getCellById(msg.cellId.id);
		
        VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
        if(v==null)
            throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
		
        if(!v.isRecolector())
            throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un recolector y no puede recolectar!");
        
        VehiculoRecolectorModel vr=(VehiculoRecolectorModel)v;

        ContenedorModel c=getWorld().getFactory().modelFactory.crearContenedor(msg.contenedor);

        vr.cogeContenedor(c, celda);
    }       

    @RunOnGLThread
    public void onRecursoToVehiculo(MsgOnRecursoToVehiculo msg)throws Exception{
		CeldaModel celda=getCellById(msg.cellId.id);
		
		VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
		if(v==null)
			throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
		
		if(!v.isTransporte())
			throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un transporte y no puede coger un contenedor!");
		
		IAlmacenable e=(IAlmacenable)celda.getEdificio(msg.edificioId);
		if(e==null)
			throw new Exception("El edificio con id: "+msg.edificioId+" no existe en la celda "+msg.cellId.id);
		
		VehiculoTransporteModel vt=(VehiculoTransporteModel)v;
		VehiculoTransporteDAO vDAO=(VehiculoTransporteDAO)vt.getDao();
		
		for(ContenedorDAO cDAO:msg.contenedores){
			ContenedorModel c=getWorld().getFactory().modelFactory.crearContenedor(cDAO);

			vt.cogeContenedor(c, celda);
		}
		
		if(msg.contenedores.size()>0)
			e.getAlmacenDAO().removeRecursoByTipo(msg.getContenedores().get(0).getTipo(), msg.getContenedores().size());
	}

    @RunOnGLThread
    public void onEdificioConstruido(MsgOnEdificioConstruido msg)throws Exception{
        CeldaModel celda=getCellById(msg.cellId.id);
        ConstruyendoModel e=(ConstruyendoModel)celda.getEdificio(msg.construyendoId);
        VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
        if(v==null)
                throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
        if(!v.isTransporte())
		throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un transporte y no puede coger un contenedor!");		
	VehiculoTransporteModel vt=(VehiculoTransporteModel)v;
        
        celda.onEdificioConstruido(e, msg.nuevoEdificio);
        
        for(Long cId:msg.contenedorId){
            if(vt.quitaContenedor(cId)==null){
                throw new Exception("Error al quitar el contenedor "+cId+"del vehiculo "+vt.getDao().getId());
            }
        }                
    }
    
    @RunOnGLThread
    public void onSyncRecursos(MsgOnSyncRecursos msg)throws Exception{
        CeldaModel celda=getCellById(msg.cellId.id);
        EdificioAlmacenModel e=(EdificioAlmacenModel)celda.getEdificio(msg.edificioId);       
        
        EdificioAlmacenDAO dao=(EdificioAlmacenDAO)e.getDAO();
        
        for(RecursoDAO r:msg.recursos){
        	dao.getRecursoByTipo(r.tipo, true).cantidad=r.cantidad;
        }
        
        if(e.getWindow()!=null)
        	e.getWindow().actualizar();
    }
}
