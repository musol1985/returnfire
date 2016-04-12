package com.returnfire.models.elementos.buildings.ext;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.items.Model;
import com.jme3.bullet.control.RigidBodyControl;

@ModelEntity(asset="Models/buildings/ext/petroleo.j3o")
public class PetroleoExt extends Model {
	@PhysicsBodyComponent(nodeName="petroleo")
	public RigidBodyControl body;
}
