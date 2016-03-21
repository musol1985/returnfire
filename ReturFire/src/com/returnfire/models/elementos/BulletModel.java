/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.items.NetworkModel;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

/**
 *
 * @author Edu
 */
public abstract class BulletModel<T extends PhysicsRigidBody> extends NetworkModel{	
	public enum BALAS{NORMAL, GRANDE}
	
    @PhysicsBodyComponent(type=PhysicsBodyType.RIGID_BODY,mass=1f)
    @CustomCollisionShape(methodName = "getCollisionShape")
    public RigidBodyControl body;
    
    public VehiculoModel from;
    public String idBala;

	public RigidBodyControl getBody(){
		return body;
	}

    
    public void setFrom(VehiculoModel vehiculo){
    	String id=vehiculo.getPlayer().getDao().getId()+"#Bala"+System.currentTimeMillis();
    	setFrom(id, vehiculo.getArma().getWorldTranslation(), vehiculo.getArma().getWorldRotation(), vehiculo);
    }
    
    public void setFrom(String id, Vector3f pos, Quaternion rotation, VehiculoModel vehiculo){
    	idBala=id;
    	from=vehiculo;
    	
    	setLocalTranslation(pos.addLocal(10, 0, 0));
    	getBody().getPhysicsLocation(pos.addLocal(10, 0, 0));
    	
    	setLocalRotation(rotation);    	
    	
    	body.setLinearVelocity(new Vector3f(0,0,500f));
        
    }
}
