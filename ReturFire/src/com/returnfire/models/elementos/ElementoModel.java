/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.core.items.BatchModel;
import com.entity.core.items.Model;
import com.entity.network.core.items.IWorldInGameScene;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.ElementoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.batchs.EstaticosBatch;

/**
 *
 * @author Edu
 */
public abstract class ElementoModel<T extends ElementoDAO, B extends BatchModel, P extends PhysicsCollisionObject> extends Model<B>{
    protected T dao;
    

    @PhysicsBodyComponent(typeMethod = "getBodyType")
    @CustomCollisionShape(methodName = "getColisionShape")
    public P body;
    
    public abstract CollisionShape getColisionShape();
    
    public abstract Class getBodyType();

    public void setDao(T dao) {
        this.dao = dao;
    }
    
    public T getDAO(){
        return dao;
    }
    
    public abstract boolean onEliminar(Vector3f vel);
     
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
    public P getBody(){
        return body;
    }
    
     @Override
	public void onPreInject(IBuilder builder, Object[] params) throws Exception {
         setDao((T)params[0]);
	}

	@Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception{
        super.onInstance(builder, params); 

         setName(dao.getId());
    }
    
    public void setLocation(Vector3f p, CeldaModel c ){
        //setLocalTranslation();
        if(body instanceof RigidBodyControl){            
            ((RigidBodyControl)body).setPhysicsLocation(c.localToWorld(p));
        }else{
            setLocalTranslation(p);
        }
    }
     
     
     public CeldaModel getCelda(){
    	 if(getParentModel() instanceof EstaticosBatch){
    		 return ((EstaticosBatch)getParentModel()).getCelda();
    	 }else 
    		 return null;
     }
}
