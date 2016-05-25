package com.returnfire.models.elementos.environment;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/environment/recursoPetroleo.j3o")
public class RecursoPetroleoModel extends EstaticoModel<RecursoPetroleoDAO, GhostControl>{


    
    @Override
    public CollisionShape getColisionShape(){
        return new SphereCollisionShape(4);
    }
    

    @Override
    public boolean onEliminar(Vector3f vel) {   
        return false;
    }
}
