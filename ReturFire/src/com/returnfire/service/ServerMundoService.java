package com.returnfire.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.entity.anot.RunOnGLThread;
import com.entity.network.core.beans.CellId;
import com.entity.network.core.service.impl.ServerNetWorldService;
import com.entity.utils.Utils;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.buildings.ExtensionDAO;
import com.returnfire.dao.elementos.buildings.impl.BaseTierraDAO;
import com.returnfire.dao.elementos.contenedores.BarrilDAO;
import com.returnfire.dao.elementos.vehiculos.impl.CamionDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.buildings.EdificioAlmacenModel;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.buildings.IAlmacenable;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.bullets.BulletModel;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;
import com.returnfire.msg.MsgBuild;
import com.returnfire.msg.MsgErrOnBuilt;
import com.returnfire.msg.MsgOnBuilding;
import com.returnfire.msg.MsgOnContenedorEdificio;
import com.returnfire.msg.MsgOnDisparar;
import com.returnfire.msg.MsgOnEdificioConstruido;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;
import com.returnfire.msg.MsgSyncRecursos;

public class ServerMundoService extends ServerNetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO>{
        private Random rnd;
        
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
	public void preload() throws Exception{
		world.getDao().setSeed(System.currentTimeMillis());
		
		rnd=new Random(world.getDao().getSeed());
		
		int offset=10;
		
		float x=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()*CeldaModel.CELL_SIZE-offset);
		float z=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()*CeldaModel.CELL_SIZE-offset);
                
                x=600;
                z=600;
		Vector3f posInicial=new Vector3f(x, HeightService.MAX_HEIGHT-10, z);
		//We put the players near
		for(JugadorDAO j:world.getDao().getPlayers().values()){
			j.setPosition(posInicial.clone());
			VehiculoDAO vehiculoInicial=VehiculoDAO.getNew(CamionDAO.class, posInicial, 0);
                                                
			BaseTierraDAO base=new BaseTierraDAO(j, VehiculoDAO.getVacio());
            base.addExtension(new ExtensionDAO("zonaA", ExtensionDAO.EXTENSIONES.PIEZAS));
			addEdificio(base, posInicial);
                        
			j.setVehiculo(vehiculoInicial);
			posInicial.addLocal(20,0,20);
		}

	}
	
	public void addEdificio(EdificioVehiculosDAO edificio, Vector3f worldPos)throws Exception{
		Vector2 pos=getCellPosByReal(worldPos);

        CeldaModel celda=createNewCell(pos, null, false);
        
        Vector3f cellLocalPosition=celda.worldToLocal(worldPos, null);
        edificio.setPos(cellLocalPosition);

        celda.addEdificio(edificio, true, false);            
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(20,0,40)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(20,0,60)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(20,0,80)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(40,0,40)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(40,0,60)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(40,0,80)), true, false);
            celda.addContenedor(ContenedorDAO.getNew(BarrilDAO.class, cellLocalPosition.add(40,0,90)), true, false);
	}


	@Override
	public void onNewPlayerDAO(JugadorDAO player) {
		player.setPosition(new Vector3f());
	}

	@Override
	public CeldaDAO onNewCellDAO(Vector2 cellId) {
		CeldaDAO celda=new CeldaDAO();
		celda.setId(new CellId(cellId, System.currentTimeMillis()));
		
		//Aplicar la logica de colocar arboles, terreno, etc.
                if(rnd==null)
                    rnd=new Random(world.getDao().getSeed());
                
                celda.generar(rnd, getWorld());
		
		return celda;
	}


    @Override
    public Class<JugadorModel> getPlayerClass() {
        return JugadorModel.class;
    }


	@Override
	public int getCellCacheSize() {
		return 50;
	}
	
	@RunOnGLThread
	public void disparar(String from, BALAS tipo)throws Exception{
		//Crea la bala y la attach
		BulletModel bala=getWorld().getFactory().balasFactory.crearBala(getWorld(), from, tipo);
		if(bala!=null){
			MsgOnDisparar msg=new MsgOnDisparar();
			msg.id=bala.idBala;
			msg.from=from;
			msg.tipo=tipo;
			msg.position=bala.getWorldTranslation();
			msg.rotation=bala.getWorldRotation().toAngles(new float[3]);
			
			msg.send();
		}
	}        
	
	public MsgErrOnBuilt build(MsgBuild msg)throws Exception{
		CeldaModel celda=getCellById(getCellPosByReal(msg.pos));

		JugadorDAO jugador=world.getPlayers().get(msg.from).dao;
		ConstruyendoDAO dao=new ConstruyendoDAO(jugador, msg.edificio, getCellLocalPosByReal(msg.pos, celda.dao));
		
		if(!celda.isZonaOcupada(msg.pos, dao.getSize())){
			celda.addConstruyendoEdificio(dao, true, true);
			new MsgOnBuilding(dao, celda.dao.getId()).send();

		}else{
                        return new MsgErrOnBuilt(msg.nodo);
			//throw new Exception("Atenci�n, la zona "+msg.pos+" est� ocupada!!!");
		}
                return null;
	}
	
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
		msg.send();
		//TODO send msg de contenedor cogido, con el contenedor DAO, y el vehiculoId.
	}
	
    public void onContenedorEdificio(MsgOnContenedorEdificio msg)throws Exception{
		CeldaModel celda=getCellById(msg.cellId.id);
		
		VehiculoModel v=(VehiculoModel) getWorld().getVehiculos().getVehiculo(msg.vehiculoId);
		if(v==null)
			throw new Exception("Vehiculo con id: "+msg.vehiculoId+" no ecnotrnado");
		
		if(!v.isTransporte())
			throw new Exception("El vehiculo con id: "+msg.vehiculoId+" no es un transporte y no puede coger un contenedor!");
		
        VehiculoTransporteModel vt=(VehiculoTransporteModel)v;
        
        IAlmacenable e=(IAlmacenable)celda.getEdificio(msg.edificioId);


        List<Long> contenedoresAdded=new ArrayList<Long>(msg.contenedorId.size());
        Iterator<Long> it=msg.contenedorId.iterator();
        
        try{
	        while(it.hasNext()){
	        	long cId=it.next();
	            ContenedorModel<ContenedorDAO> c=vt.getContenedorById(cId);
	            if(c==null)
	            	throw new Exception("Contenedor con id: "+cId+" no encontrado en "+msg.cellId.id);
	
	            if(e.getAlmacenDAO().puedeAlmacenarMas(c.getDAO().getTipo())){
	            	e.getAlmacenDAO().addRecursoByTipo(c.getDAO().getTipo(), 1);
	                contenedoresAdded.add(c.getDAO().getIdLong());
	                vt.quitaContenedor(c);
	                it.remove();
	            }            
	        }
        }catch(Exception ee){
        	ee.printStackTrace();
        }
        
        if(e.getAlmacenDAO().isConstruyendo() && contenedoresAdded.size()>0){
            ConstruyendoDAO dao=(ConstruyendoDAO)e.getAlmacenDAO();
            if(dao.isConstruido()){
                EdificioDAO daoNuevo=celda.onEdificioConstruido((ConstruyendoModel)e);
                new MsgOnEdificioConstruido(msg.cellId, msg.vehiculoId, msg.edificioId, daoNuevo, contenedoresAdded).send();
            }else{
                new MsgOnContenedorEdificio(msg.cellId, msg.vehiculoId, msg.edificioId, contenedoresAdded).send();
            }
        }
                
        if(msg.contenedorId.size()>0){
        	//TODO Error, se debe de enviar al tio una sincronizacion del edificio
        }
		//TODO send msg de contenedor cogido, con el contenedor DAO, y el vehiculoId.
	}
    
    
    public EdificioAlmacenDAO syncRecursos(MsgSyncRecursos msg)throws Exception{
    	CeldaModel celda=getCellById(msg.cellId.id);
    	EdificioModel e=celda.getEdificio(msg.edificioId);
    	
    	if(!(e.getDAO() instanceof EdificioAlmacenDAO)){
    		throw new Exception("No se pueden sincronizar recursos de un vehiculo que no es almacen: "+msg.edificioId+ " en "+msg.cellId.id);    		
    	}
    	return (EdificioAlmacenDAO)e.getDAO();
    }
}
