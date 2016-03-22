/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.anot.OnCollision;
import com.entity.anot.OnUpdate;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.items.Model;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.models.elementos.environment.ArbolModel;
import com.returnfire.msg.MsgOnImpactoBala;

/**
 *
 * @author Edu
 */
public abstract class BulletModel extends Model{	
	public enum BALAS{NORMAL, GRANDE}
	
    @PhysicsBodyComponent(type=PhysicsBodyType.RIGID_BODY,mass=1f)
    @CustomCollisionShape(methodName = "getCollisionShape")
    public RigidBodyControl body;
    
    public VehiculoModel from;
    public String idBala;
    private long t;

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
    	
    	setLocalTranslation(pos.add(0,1,0));
        setLocalRotation(rotation);    	

        body.setLinearVelocity(rotation.mult(new Vector3f(0,-75f,0)));
        
        t=System.currentTimeMillis();
    }
    
    @OnCollision
    public void onImpactarArbol(ArbolModel arbol)throws Exception{
    	if(GameContext.isServer()){
    		new MsgOnImpactoBala(idBala).send();
    	}
    	GameContext.getMundo().getBalas().eliminar(this);
    }
    
    @OnUpdate
    public void onUpdate(float tpf){        
        if(System.currentTimeMillis()-t>2000){
            GameContext.getMundo().getBalas().eliminar(this);
        }
    }
}
