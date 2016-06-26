/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.vehicles;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.entity.anot.OnCollision;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.network.core.beans.CellId;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.buildings.EdificioAlmacenModel;
import com.returnfire.models.elementos.buildings.IAlmacenable;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;
import com.returnfire.msg.MsgSyncRecursos;

/**
 *
 * @author Edu
 */
public abstract class VehiculoTransporteModel<T extends PhysicsRigidBody, D extends VehiculoTransporteDAO> extends VehiculoModel<T, D>{	
    
    private IAlmacenable edificio;
    
	@SubModelComponent(name="contenedoresReference")
	public Node contenedoresReference;

    
	
	public abstract Vector3f getCoordenadasContenedorByIndex(int index);
	public abstract Vector3f getPosicionContenedorByCoordenadas(Vector3f coordenadas);

    
    @OnCollision(includeSubClass = true)
    public void onColisionContenedor(ContenedorModel<ContenedorDAO> contenedor)throws Exception{
    	/*int size=dao.getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
    		contenedor.dettach();
    		contenedor.attachToParent(this);
    		colocarContenedor(contenedor, size);
    	}*/   
        if(tieneSitio()){
            contenedor.body.setEnabled(false);
            new MsgOnVehiculoCogeContenedor(contenedor.getCelda().dao.getId(), dao.getIdLong(), contenedor.getDAO().getIdLong()).send();
        }
    }
    
    public boolean tieneSitio(){
        return dao.getContenedoresSize()<dao.getMaxSlots();
    }
    
    public void cogeContenedor(ContenedorModel<ContenedorDAO> contenedor, CeldaModel celda)throws Exception{
    	int size=getDao().getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
    		if(contenedor.getParent()!=null)
    			celda.removeContenedor(contenedor, true);
            
            contenedor.attachToParent(this);
            contenedor.getBody().setEnabled(false);
            colocarContenedor(contenedor, size);
    	}else{
    		throw new Exception("No se ha podido a�adir el contenedor "+contenedor.getDAO().getId());
    	}
    }
    
    @OnCollision
   public void onColisionVehiculo(ConstruyendoModel construccion)throws Exception{
    	onColisionAlmacenable(construccion, construccion.getCelda(), construccion.getDAO());
   }
    
    @OnCollision(includeSubClass=true)
    public void onColisionVehiculo(EdificioAlmacenModel almacen)throws Exception{
     	onColisionAlmacenable(almacen, almacen.getCelda(), (EdificioAlmacenDAO)almacen.getDAO());
    }
    
    private void onColisionAlmacenable(IAlmacenable edificio, CeldaModel celda, EdificioAlmacenDAO dao)throws Exception{
    	if(this.edificio!=edificio){
    		this.edificio=edificio;
                if(!(edificio instanceof ConstruyendoModel))
                    new MsgSyncRecursos(celda.dao.getId(), dao).send();
            edificio.onVehiculoEnZona(this);
    	}
    }
   
   public void onUpdate(float tpf)throws Exception{
	   if(this.edificio!=null && !edificio.isVehiculoEnZona(this)){
		   edificio.onVehiculoFueraZona(this);
		   edificio=null;
	   }      
   }
   
   @Override
	public void onAccion(boolean valor) {
		new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
	}
    
    public ContenedorModel<ContenedorDAO> getContenedorById(long id){
        for(Spatial s:getChildren()){
            if(s instanceof ContenedorModel){
                ContenedorModel<ContenedorDAO> c=(ContenedorModel<ContenedorDAO>)s;
                if(c.getDAO().getIdLong()==id)
                    return c;
            }
        }
        return null;
    }
    
    public ContenedorModel quitaContenedor(long id){
        return quitaContenedor(getContenedorById(id));
    }
    
    public ContenedorModel quitaContenedor(ContenedorModel c){
        try{
            ContenedorDAO cDao=(ContenedorDAO)c.getDAO();
            dao.getContenedores().get(cDao.getTipo()).remove(cDao);
            c.dettach();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

    
	
	public boolean existeContenedorInPosicion(Vector3f pos){
		for(Entry<RECURSOS, List<ContenedorDAO>> ce:dao.getContenedores().entrySet()){
			for(ContenedorDAO c:ce.getValue()){
				if(c.getPos().equals(pos))
					return true;
			}
		}
		return false;
	}


	public void colocarContenedor(ContenedorModel c, int size) throws Exception {		
		for(int i=0;i<dao.getMaxSlots();i++){
			Vector3f coordenadas=getCoordenadasContenedorByIndex(i);
			if(!existeContenedorInPosicion(coordenadas)){
				c.getDAO().setPos(coordenadas.clone());
				c.setLocalTranslation(contenedoresReference.getLocalTranslation().add(getPosicionContenedorByCoordenadas(coordenadas)));
                                return ;
			}			
		}
	}
}
