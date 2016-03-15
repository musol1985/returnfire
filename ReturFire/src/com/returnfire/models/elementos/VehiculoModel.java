/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.anot.components.model.VehicleComponent;
import com.entity.anot.components.model.WheelComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.items.Model;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.JugadorDAO;

/**
 *
 * @author Edu
 */
public abstract class VehiculoModel extends Model{
	
	
	@VehicleComponent(mass=400.0f,
			wheels={
				@WheelComponent(nodeName="wheel_fl", offset={1.5f,1f,2.5f}, frontWheel=true),
				@WheelComponent(nodeName="wheel_fr", offset={-1.5f,1f,2.5f}, frontWheel=true),
				@WheelComponent(nodeName="wheel_rl", offset={1.5f,1f,-2.5f}),
				@WheelComponent(nodeName="wheel_rr", offset={-1.5f,1f,-2.5f}),
			})
	@CustomCollisionShape(methodName="getCollisionShape")
    public VehicleControl vehicle;
    

    private float steeringValue = 0;
    private float accelerationValue = 0;        

    
    public void setPosicionInicial(Vector3f pos){
    	vehicle.setPhysicsLocation(pos);
    }
    
    public abstract CompoundCollisionShape getCollisionShape();
    
    public void attachCamera(ScrollCameraAdapter cam){
        attachChild(cam);
    }
    
    public void onLeft(boolean value){
    	if (value) {
            steeringValue += .5f;
        } else {
            steeringValue += -.5f;
        }
        vehicle.steer(steeringValue);
    }
    
    public void onRight(boolean value){
        if (value) {
            steeringValue += -.5f;
        } else {
            steeringValue += .5f;
        }
        vehicle.steer(steeringValue);
    }
    
    public void onAccelerate(boolean value){
        if (value) {
            accelerationValue += getAccelerationForce();
        } else {
            accelerationValue -= getAccelerationForce();
        }
        vehicle.accelerate(accelerationValue);
    }
    
    public void onBrake(boolean value){
        if (value) {
            vehicle.brake(getBrakeForce());
        } else {
            vehicle.brake(0f);
        }
    }
    
    
    public float getAccelerationForce(){
    	return 1000.0f;
    }
    
    public float getBrakeForce(){
    	return 100.0f;
    }
    
    public JugadorDAO.VEHICULOS getTipoVehiculo(){
    	return JugadorDAO.VEHICULOS.HAMMER;
    }
    
    public Vector3f getPhysicsLocation(){
    	return vehicle.getPhysicsLocation();
    }
}
