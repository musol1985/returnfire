package com.returnfire.models.elementos;

import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

public class EdificioModel<T extends EdificioDAO> extends EstaticoModel<T> {

	@Override
	public CollisionShape getColisionShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onEliminar(Vector3f vel) {
		// TODO Auto-generated method stub
		return false;
	}

}
