package com.returnfire.models.elementos.environment;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/palm_03.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO>{
 

   
   
    
    @Override
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,5,1));
    }
}
