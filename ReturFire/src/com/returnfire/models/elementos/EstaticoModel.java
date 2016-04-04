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
import com.entity.core.items.Model;
import com.entity.network.core.items.IWorldInGameScene;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.batchs.EstaticosBatch;

/**
 *
 * @author Edu
 */
public abstract class EstaticoModel<T extends EstaticoDAO> extends Model<EstaticosBatch> {
    protected T dao;
    @PhysicsBodyComponent
    @CustomCollisionShape(methodName = "getColisionShape")
    public RigidBodyControl body;

    public void setDao(T dao) {
        this.dao = dao;
    }
    
    public T getDAO(){
        return dao;
    }
    
    public abstract CollisionShape getColisionShape();
    public abstract void onEliminar();
     
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
     @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); 
        
        setDao((T)params[0]);
        
         setName(dao.getId());
    }
     
     /**
      * Retorna true si destruye el elemento
      * @param bala
      * @return
      */
     public boolean onImpacto(BulletModel bala)throws Exception{
		 getCelda().dao.getId().update();
		 if(dao.addDanyo(bala.getDanyo())){
			 eliminar();
			 return true;
		 }
    	 
    	 return false;
     }
     
     public boolean isDestructible(){
    	 return dao.isDestructible();
     }
     
     
     public void eliminar(){
         onEliminar();
    	 getParentModel().eliminar(this);
    	 
    	 getCelda().dao.getEstaticos().remove(dao);
    	 getCelda().save();
     }
     
     public CeldaModel getCelda(){
         return getParentModel().getCelda();
     }
}
