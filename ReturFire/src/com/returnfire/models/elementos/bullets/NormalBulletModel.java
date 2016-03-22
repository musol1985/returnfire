package com.returnfire.models.elementos.bullets;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.returnfire.models.elementos.BulletModel;

@ModelEntity(asset = "Models/vehicles/bala.j3o", attach = true)
public class NormalBulletModel extends BulletModel{


    public SphereCollisionShape getCollisionShape() {
        return new SphereCollisionShape(0.2f);    
    }
}
