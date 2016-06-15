package com.returnfire.models.elementos.environment;

import com.entity.anot.entities.ModelEntity;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.impl.RockDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/environment/rock_brown_03.j3o")
public class BrownRock3 extends EstaticoModel<RockDAO, RigidBodyControl>{
    
    
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,1,1));
    }
    
    @Override
    public boolean onEliminar(Vector3f vel) {
        return true;
    }
    
    @Override
    public Class getBodyType(){
        return RigidBodyControl.class;
    }
}
