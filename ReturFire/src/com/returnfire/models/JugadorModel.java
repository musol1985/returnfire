package com.returnfire.models;

import com.entity.anot.Service;
import com.entity.network.core.models.NetPlayer;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.vehiculos.impl.VacioDAO;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.factory.VehiculosFactory;
import com.returnfire.msg.MsgCambiarVehiculo;

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

    
    public void seleccionarSinVehiculo()throws Exception{
    	VehiculoDAO old=dao.getVehiculo();
    	
    	if(!old.isVacio())
    		dao.setVehiculo(new VacioDAO());    	    	
    	
    	if(vehiculo!=null){
    		if(!isRemote()){
    			vehiculo.netUnControl();
    		}
    		vehiculo=null;
    	}
    	
    	if(old!=null && dao.getVehiculo().equalsTipo(old))
    		new MsgCambiarVehiculo(dao.getVehiculo(), old).send();
    }
    
    public void setVehiculoInicial()throws Exception{
    	if(dao.getVehiculo().isVacio()){
    		seleccionarSinVehiculo();
    	}else{
	    	vehiculo=factory.crearVehiculo(this, dao.getVehiculo());
	    	
	    	
	    	vehiculo.attachToParent(GameContext.getMundo());    	
	        vehiculo.setPosicionInicial(dao.getVehiculo().getPos());
	        
	        if(!isRemote()){
	        	vehiculo.netControl();
	        }
    	}    	    	    	
    }

    
    public Vector3f getPosicion(){
    	if(vehiculo!=null)
    		return vehiculo.getPhysicsLocation();
    	return dao.getPosition();
    }
}
