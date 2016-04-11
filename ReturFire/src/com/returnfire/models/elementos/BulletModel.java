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
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.environment.ArbolModel;
import com.returnfire.msg.MsgOnBalaEstatico;

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
	
	public abstract int getDanyo();

    
    public void setFrom(VehiculoModel vehiculo){
    	String id=vehiculo.getPlayer().getDao().getId()+"#Bala"+System.currentTimeMillis();
    	setFrom(id, vehiculo.getArmaEmitter().getWorldTranslation(), vehiculo.getArmaEmitter().getWorldRotation(), vehiculo);
        
    }
    
    public void setFrom(String id, Vector3f pos, Quaternion rotation, VehiculoModel vehiculo){
    	idBala=id;
    	from=vehiculo;
    	
    	setLocalTranslation(pos);
        setLocalRotation(rotation);   
        rotate(-FastMath.QUARTER_PI, 0, 0);        

        body.setLinearVelocity(getWorldRotation().mult(new Vector3f(0,0,-150f)).setY(0));
        t=System.currentTimeMillis();
        
        body.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_01);
        body.setCcdMotionThreshold(0.1f);
        //addControl(new BillboardControl());
    }
    
    @OnCollision
    public void onImpactarArbol(ArbolModel arbol)throws Exception{                
    	if(GameContext.isServer()){    
    		if(arbol.isDestructible()){
                        CeldaModel celda=arbol.getCelda();
    			long t=celda.dao.getId().timestamp;
	    		arbol.onImpacto(this);
	    		new MsgOnBalaEstatico(idBala, body.getLinearVelocity(), arbol, celda, t).send();
    		}
    	}else{
            arbol.onImpactoLocal(this);
        }
    	GameContext.getMundo().getBalas().eliminar(this);
    }
    
    @OnUpdate
    public void onUpdate(float tpf){        
        if(System.currentTimeMillis()-t>2000){
            GameContext.getMundo().getBalas().eliminar(this);
        }        
        //body.setLinearVelocity(body.getLinearVelocity().setY(0));
    }
}
