package com.returnfire.models.elementos.environment;

import com.entity.adapters.ControlAdapter;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.EntityManager;
import com.entity.core.items.Model;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;

@ModelEntity(asset = "Models/environment/coco.j3o")
public class CocoModel extends Model<ArbolModel>{

    @PhysicsBodyComponent(attachWorld=false, mass=10)
    @CustomCollisionShape(methodName = "getColisionShape")
    public RigidBodyControl body;
    
    public CollisionShape getColisionShape(){
        return new SphereCollisionShape(1);
    }


    public void caer()throws Exception{    	
    	Vector3f posWorld=getWorldTranslation();
    	dettach();
    	getParentModel().getCelda().getMundo().getDinamicos().attachEntity(this);
    	setLocalTranslation(posWorld);
    	EntityManager.getGame().getPhysics().add(body);
    	
    	addControl(new ControlAdapter(){
			@Override
			public void update(float tpf) {
				if(!body.isActive()){
					EntityManager.getGame().getPhysics().remove(body);
				}
			}
    	});
    }
}
