/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.network.NetSync;
import com.entity.core.IBuilder;
import com.entity.core.items.NetworkModel;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.elementos.BulletModel.BALAS;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.sync.Posicion;

/**
 *
 * @author Edu
 */
public abstract class VehiculoModel<T extends PhysicsRigidBody> extends NetworkModel{	
	protected float steeringValue = 0;
    protected float accelerationValue = 0;     
    protected JugadorModel player;
    
    @NetSync(timeout=10)
    public Posicion posicion;
    
	@SubModelComponent(name="arma")
	protected Node arma;
    

    @Override
	public String getName() {
		return player.dao.getId()+"#";
	}

	@Override
	public void onInstance(IBuilder builder, Object[] params) {
		player=(JugadorModel) params[0];
	}

	public abstract T getBody();
	
	public Node getArma(){
		return arma;
	}
    
    public void setPosicionInicial(Vector3f pos){
        setLocalTranslation(pos);
    	getBody().setPhysicsLocation(pos);
    }
    
    public void attachCamera(ScrollCameraAdapter cam){
        attachChild(cam);
    }
    
    public void onLeft(boolean value){
    	if(isVehicleControl()){
	       	 if (value) {
	                steeringValue += .5f;
	            } else {
	                steeringValue += -.5f;
	            }
	       	getVehicleControl().steer(steeringValue);
	   	}else{
	   		throw new RuntimeException("No vehicle control onLeft!!");
	   	}
    }
    
    public void onRight(boolean value){
        if(isVehicleControl()){
        	 if (value) {
                 steeringValue += -.5f;
             } else {
                 steeringValue += .5f;
             }
        	getVehicleControl().steer(steeringValue);
    	}else{
    		throw new RuntimeException("No vehicle control onRight!!");
    	}
    }
    
    public void onAccelerate(boolean value){
        if(isVehicleControl()){
        	if (value) {
                accelerationValue += getAccelerationForce();
            } else {
                accelerationValue -= getAccelerationForce();
            }
        	getVehicleControl().accelerate(accelerationValue);
    	}else{
    		throw new RuntimeException("No vehicle control onAccelerate!!");
    	}
    }
    
    public void onBrake(boolean value){
    	if(isVehicleControl()){
	        if (value) {
	        	getVehicleControl().brake(getBrakeForce());
	        } else {
	        	getVehicleControl().brake(0f);
	        }
    	}else{
    		throw new RuntimeException("No vehicle control onbrake!!");
    	}
    }
    
    public boolean isVehicleControl(){
    	return getBody() instanceof VehicleControl;
    }
    
    public VehicleControl getVehicleControl(){
    	return (VehicleControl)getBody();
    }
    
    
    public float getAccelerationForce(){
    	return 1000.0f;
    }
    
    public float getBrakeForce(){
    	return 100.0f;
    }
    
    public abstract JugadorDAO.VEHICULOS getTipoVehiculo();    
    
    public Vector3f getPhysicsLocation(){
    	return getBody().getPhysicsLocation();
    }

	public JugadorModel getPlayer() {
		return player;
	}
    
    public void disparar(){
    	new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
    }
}
