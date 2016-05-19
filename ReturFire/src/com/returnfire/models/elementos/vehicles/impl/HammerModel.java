package com.returnfire.models.elementos.vehicles.impl;

import com.entity.anot.components.model.VehicleComponent;
import com.entity.anot.components.model.WheelComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.Vector3f;
import com.returnfire.models.elementos.vehicles.VehiculoArmadoModel;

@ModelEntity(asset = "Models/vehicles/hammer.j3o", attach = false)
public class HammerModel extends VehiculoArmadoModel<VehicleControl>{

	@VehicleComponent(mass=400.0f,
		wheels={
			@WheelComponent(nodeName="wheel_fl", offset={1.5f,1f,2.5f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_fr", offset={-1.5f,1f,2.5f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_rl", offset={1.5f,1f,-2.5f}),
			@WheelComponent(nodeName="wheel_rr", offset={-1.5f,1f,-2.5f}),
		})
	@CustomCollisionShape(methodName="getCollisionShape")
	public VehicleControl body;

	
    public CompoundCollisionShape getCollisionShape() {
    	CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.5f, 1f, 3f));
        compoundShape.addChildShape(box, new Vector3f(0, 2, 0));
        return compoundShape;    
    }

	@Override
	public VehicleControl getBody() {
		return body;
	}

 
}
