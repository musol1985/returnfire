/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings;

import com.entity.anot.Entity;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.IEstaticoNode;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;

/**
 *
 * @author Edu
 */

public abstract class BaseVehiculosModel<T extends EdificioVehiculosDAO, N extends IEstaticoNode> extends EdificioModel<T, N>{

//	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
   // @CustomCollisionShape(methodName = "getParkingZoneColisionShape")
	public GhostControl parkingZone;
	

	@Entity(conditional="injectVehiculo",  substituteNode="vehiculo")
	public VehiculoModel vehiculo;

	public abstract CollisionShape getParkingZoneColisionShape();

	
	public BuildingExtension injectZona(EdificioVehiculosDAO dao, String zonaId)throws Exception{		
		BuildingExtension zona=GameContext.getMundo().getFactory().modelFactory.crearExtension(dao.getExtensionByZona(zonaId));
                
                zona.attachToParent(this);
                return zona;
	}

	public VehiculoModel injectVehiculo(EdificioVehiculosDAO dao){	
		if(dao.tieneVehiculo()){
			return GameContext.getMundo().getFactory().vehiculosFactory.crearVehiculo(null, dao.getvDAO());
		}
		return null;
	}
}
