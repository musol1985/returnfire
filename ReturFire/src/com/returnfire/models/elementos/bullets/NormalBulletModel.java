package com.returnfire.models.elementos.bullets;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;

@ModelEntity(asset = "Models/bullets/bala.j3o", attach = true)
public class NormalBulletModel extends BulletModel{


    public SphereCollisionShape getCollisionShape() {
        return new SphereCollisionShape(0.2f);    
    }

	@Override
	public int getDanyo() {
		return 25;
	}
}
