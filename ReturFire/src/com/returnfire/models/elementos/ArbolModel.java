package com.returnfire.models.elementos;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.returnfire.models.*;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.items.IWorldInGameScene;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;

@ModelEntity(asset = "Models/pino.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO>{
    @PhysicsBodyComponent
    @CustomCollisionShape(methodName = "getColisionShape")
    private RigidBodyControl body;

    @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); 
        
        setDao((ArbolDAO)params[0]);
        
        
    }
    
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
    
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,1,1));
    }
}
