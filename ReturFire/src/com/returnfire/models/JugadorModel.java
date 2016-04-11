package com.returnfire.models;

import com.entity.anot.Service;
import com.entity.network.core.models.NetPlayer;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.VehiculoDAO.VEHICULOS;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.factory.VehiculosFactory;

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
    
    public void seleccionarVehiculo(VehiculoDAO vDao)throws Exception{
    	if(dao.getVehiculo().getTipo()!=vDao.getTipo()){
    		dao.setVehiculo(vDao);
    		//TODO send net
    	}
    	
    	VehiculoModel newV=null;

    	if(vDao.getTipo()==VEHICULOS.HAMMER){
    		newV=factory.crearHammer(this, vDao);
    	}    
    	
    	
    	if(newV==vehiculo)
    		throw new RuntimeException("Mismo vehiculo!!!!!");
    	
    	if(vehiculo!=null)
    		throw new RuntimeException("TODO DETACH!!!");
    	
    	vehiculo=newV;
    	    	
    	vehiculo.attachToParent(GameContext.getMundo());    	
        vehiculo.setPosicionInicial(vDao.getPos());
        
        if(!isRemote()){
        	vehiculo.netControl();
        }
    }

    
    public Vector3f getPosicion(){
    	if(vehiculo!=null)
    		return vehiculo.getPhysicsLocation();
    	return dao.getPosition();
    }
}
