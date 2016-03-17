package com.returnfire.models;

import com.entity.anot.Service;
import com.entity.core.EntityManager;
import com.entity.network.core.items.IWorldInGameScene;
import com.entity.network.core.models.NetPlayer;
import com.jme3.math.Vector3f;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.JugadorDAO.VEHICULOS;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.factory.VehiculosFactory;
import com.returnfire.service.HeightService;

public class JugadorModel extends NetPlayer<JugadorDAO>{
    private VehiculoModel vehiculo;
    
    @Service
    private VehiculosFactory factory;
    
    public boolean hasVehicle(){
        return vehiculo!=null;
    }

    public VehiculoModel getVehiculo() {
        return vehiculo;
    }
    
    public void setVehiculo(VehiculoModel vehiculo){    	
        this.vehiculo=vehiculo;
    }
    
    public void seleccionarVehiculo()throws Exception{
    	seleccionarVehiculo(dao.getVehiculo());
    }
    
    public void seleccionarVehiculo(VEHICULOS tipo)throws Exception{
    	if(dao.getVehiculo()!=tipo){
    		dao.setVehiculo(tipo);
    		//TODO send net
    	}
    	
    	VehiculoModel newV=null;

    	if(tipo==VEHICULOS.HAMMER){
    		newV=factory.crearHammer(this);
    	}    
    	
    	
    	if(newV==vehiculo)
    		throw new RuntimeException("Mismo vehiculo!!!!!");
    	
    	if(vehiculo!=null)
    		throw new RuntimeException("TODO DETACH!!!");
    	
    	vehiculo=newV;
    	    	
    	vehiculo.attachToParent(getMundo());    	
        vehiculo.setPosicionInicial(dao.getPosition().add(0, HeightService.MAX_HEIGHT+30, 0));
        
        if(!isRemote()){
        	vehiculo.netControl();
        }
    }
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
    public Vector3f getPosicion(){
    	if(vehiculo!=null)
    		return vehiculo.getPhysicsLocation();
    	return dao.getPosition();
    }
}
