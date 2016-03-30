package com.returnfire.models.elementos.environment;

import com.returnfire.models.elementos.*;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.RockDAO;

@ModelEntity(asset = "Models/environment/rock_brown_01.j3o")
public class BrownRock1 extends EstaticoModel<RockDAO>{
    
    
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,1,1));
    }
    
    @Override
    public void onEliminar() {
        
    }
}
