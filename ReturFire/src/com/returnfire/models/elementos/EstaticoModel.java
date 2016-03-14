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
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.models.MundoModel;

/**
 *
 * @author Edu
 */
public abstract class EstaticoModel<T extends EstaticoDAO> extends Model {
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
    
     
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
     @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); 
        
        setDao((T)params[0]);
    }
}
