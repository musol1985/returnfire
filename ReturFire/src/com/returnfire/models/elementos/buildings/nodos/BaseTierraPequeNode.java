package com.returnfire.models.elementos.buildings.nodos;

import com.entity.anot.entities.ModelEntity;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.BaseTierraDAO;

@ModelEntity(asset = "Models/buildings/base_tierra_peque.j3o")
public class BaseTierraPequeNode extends BuildNode {

	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}

	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(4,4,4));
	}

	@Override
	public EdificioDAO getNewDAO(JugadorDAO jugador) {
		return new BaseTierraDAO(jugador, null);
	}

	
}
