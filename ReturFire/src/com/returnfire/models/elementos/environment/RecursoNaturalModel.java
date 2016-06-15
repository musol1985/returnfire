package com.returnfire.models.elementos.environment;

import com.entity.core.IBuilder;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
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
        
    @Override
    public Class getBodyType(){
        return GhostControl.class;
    }

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
        body.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_03);
        body.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);
    }
    
    
}
