/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.anot.OnCollision;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.network.NetSync;
import com.entity.core.IBuilder;
import com.entity.core.items.NetworkModel;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.VehiculoDAO.VEHICULOS;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.elementos.BulletModel.BALAS;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.sync.Posicion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Edu
 */
public abstract class VehiculoModel<T extends PhysicsRigidBody> extends NetworkModel{	
	protected float steeringValue = 0;
    protected float accelerationValue = 0;     
    protected JugadorModel player;
    protected VehiculoDAO dao;
    
    private List<ConstruyendoModel> construcciones=new ArrayList<ConstruyendoModel>();

    @NetSync(timeout=10)
    public Posicion posicion;
    
	@SubModelComponent(name="gun")
	protected Node arma;
    @SubModelComponent(name="gunEmitter")
	protected Node armaEmitter;


    @Override
	public String getName() {
		return dao.getId()+"#";
	}

	@Override
	public void onInstance(IBuilder builder, Object[] params) {
		if(params.length==2){//Vehiculos que tienen los jugadores
			player=(JugadorModel) params[0];
	        if(player.isMe()){
	            getBody().setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
	        }                    
	        dao=(VehiculoDAO)params[1];
		}else{//Vehiculos aparcados(solo vien el dao
			dao=(VehiculoDAO)params[0];
		}
	}

	public abstract T getBody();
	
	public Node getArma(){
		return arma;
	}
        
    public Node getArmaEmitter(){
        return armaEmitter;
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
    
    public VEHICULOS getTipoVehiculo(){
    	return dao.getTipo();
    }
    
    public Vector3f getPhysicsLocation(){
    	return getBody().getPhysicsLocation();
    }

	public JugadorModel getPlayer() {
		return player;
	}
    
    public void disparar(){
    	new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
    }
    
    
     @OnCollision
    public void onColisionVehiculo(ConstruyendoModel construccion)throws Exception{
    	System.out.println("oeeeeeee");
        if(!construcciones.contains(construccion)){
            construcciones.add(construccion);
        }
    }
    
    public void onUpdate(float tpf)throws Exception{
        Iterator<ConstruyendoModel> it=construcciones.iterator();
        while(it.hasNext()){
            if(!it.next().isVehiculoEnZona(this)){
                System.out.println("------------------------>remove!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                it.remove();
            }
        }
    }
}
