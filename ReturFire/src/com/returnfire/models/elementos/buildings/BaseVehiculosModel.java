/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;
import com.returnfire.models.factory.ModelFactory;

/**
 *
 * @author Edu
 */

public abstract class BaseVehiculosModel<T extends EdificioVehiculosDAO> extends EdificioModel<T>{

	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getParkingZoneColisionShape")
	public GhostControl parkingZone;
	

	

	public abstract CollisionShape getParkingZoneColisionShape();

	
	public BuildingExtension injectZona(EdificioVehiculosDAO dao, ModelFactory factory, String zonaId){		
		return factory.crearExtension(dao.getExtensionByZona(zonaId));
	}

}
