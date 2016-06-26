package com.returnfire.models.elementos.contenedores.impl;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.contenedores.CajaDAO;
import com.returnfire.models.elementos.contenedores.ContenedorModel;

@ModelEntity(asset = "Models/contenedores/caja.j3o")
public class CajaModel extends ContenedorModel<CajaDAO>{

	@Override
	public CollisionShape getColisionShape() {
		return new BoxCollisionShape(new Vector3f(1,1,1));
	}

	@Override
	public boolean onEliminar(Vector3f vel) {
		return false;
	}

}
