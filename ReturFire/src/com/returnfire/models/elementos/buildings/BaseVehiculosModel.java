/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings;

import com.entity.anot.Entity;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.buildings.ext.PetroleoExt;

/**
 *
 * @author Edu
 */

public abstract class BaseVehiculosModel<T extends EdificioVehiculosDAO> extends EdificioModel<T>{

	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getParkingZoneColisionShape")
	public GhostControl parkingZone;
	
	@Entity(conditional="isTienePetroleo", substituteNode="petroleoExt")
	public PetroleoExt petroleo;
	

	public abstract CollisionShape getParkingZoneColisionShape();



	public boolean isTienePetroleo(){
		return dao.tieneExtensionPetroleo();
	}

}
