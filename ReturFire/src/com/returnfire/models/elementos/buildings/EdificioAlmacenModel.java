package com.returnfire.models.elementos.buildings;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

public abstract class EdificioAlmacenModel<T extends EdificioDAO, N extends BuildNode> extends EdificioConstruibleModel<T, N>{

	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getZonaShape")
    public GhostControl zona;

	
	public abstract CollisionShape getZonaShape();
	
	public GhostControl getZona(){
		return zona;
	}
}
