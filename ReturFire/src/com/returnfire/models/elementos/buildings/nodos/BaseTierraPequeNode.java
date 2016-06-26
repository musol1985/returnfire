package com.returnfire.models.elementos.buildings.nodos;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.BaseTierraDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/buildings/base_tierra_peque.j3o")
public class BaseTierraPequeNode extends BuildNode {


	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(11,1,11));
	}

	@Override
	public Class<? extends EdificioDAO> getDAO() {
		return BaseTierraDAO.class;
	}

	@Override
	public boolean puedeConstruirseAqui(EstaticoModel edificioColision) {
		return true;
	}

	
}
