/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings;

import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;

/**
 *
 * @author Edu
 */
@ModelEntity(asset = "Models/buildings/base_tierra_peque.j3o")
public class BaseTierraPequeModel extends BaseVehiculosModel<EdificioVehiculosDAO>{
    @Override
    public CollisionShape getColisionShape() {
            // TODO Auto-generated method stub
            return new BoxCollisionShape(new Vector3f(4,0.3f,4));
    }

    @Override
    public boolean onEliminar(Vector3f vel) {
            // TODO Auto-generated method stub
            return false;
    }

	@Override
	public CollisionShape getParkingZoneColisionShape() {
		// TODO Auto-generated method stub
		return null;
	}
}
