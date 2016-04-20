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
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;
import com.returnfire.models.factory.ModelFactory;
import com.returnfire.models.factory.VehiculosFactory;

/**
 *
 * @author Edu
 */

public abstract class BaseVehiculosModel<T extends EdificioVehiculosDAO> extends EdificioModel<T>{

	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getParkingZoneColisionShape")
	public GhostControl parkingZone;
	

	@Entity(conditional="injectVehiculo",  substituteNode="vehiculo")
	public VehiculoModel vehiculo;

	public abstract CollisionShape getParkingZoneColisionShape();

	
	public BuildingExtension injectZona(EdificioVehiculosDAO dao, String zonaId){		
		return GameContext.getMundo().getFactory().modelFactory.crearExtension(dao.getExtensionByZona(zonaId));
	}

	public VehiculoModel injectVehiculo(EdificioVehiculosDAO dao){	
		if(dao.tieneVehiculo()){
			return GameContext.getMundo().getFactory().vehiculosFactory.crearVehiculo(null, dao.getvDAO());
		}
		return null;
	}
}
