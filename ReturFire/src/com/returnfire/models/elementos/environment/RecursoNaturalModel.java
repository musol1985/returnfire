package com.returnfire.models.elementos.environment;

import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;


public abstract class RecursoNaturalModel<T extends EstaticoDAO, P extends GhostControl> extends EstaticoModel<T, P>{

	public boolean isVehiculoEncima(VehiculoModel v){
		for(PhysicsCollisionObject obj:body.getOverlappingObjects()){
			if(obj==v.getBody())
				return true;
		}
		return false;
	}
}
