package com.returnfire.models.elementos.vehicles.impl;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.returnfire.models.elementos.vehicles.VehiculoArmadoModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;

@ModelEntity(asset = "Models/vehicles/hammer.j3o", attach = false)
public class HammerModelRemote extends VehiculoArmadoModel<RigidBodyControl>{

	@PhysicsBodyComponent(mass=400f, type = PhysicsBodyComponent.PhysicsBodyType.KINEMATIC_BODY)
	@CustomCollisionShape(methodName="getCollisionShape")
	public RigidBodyControl body;
	
    public CompoundCollisionShape getCollisionShape() {
    	CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.5f, 1f, 3f));
        compoundShape.addChildShape(box, new Vector3f(0, 2, 0));
        return compoundShape;    
    }

	@Override
	public RigidBodyControl getBody() {
		return body;
	}

	@Override
	public void onAccion(boolean value) {
		
	}


 
}
