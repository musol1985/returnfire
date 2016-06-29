package com.returnfire.models.elementos.buildings;

import com.entity.core.EntityGame;
import com.entity.core.EntityManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.PhysicsTickListener;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.models.elementos.vehicles.VehiculoModel;

public abstract class EdificioConstruibleModel<T extends EdificioDAO, N extends BuildNode> extends EdificioModel<T, RigidBodyControl> implements  PhysicsTickListener{

	private GhostControl recienConstruido;

	public abstract N getNodo();
	
	@Override
	public void physicsTick(PhysicsSpace space, float tpf) {
		if(!isRecienConstruidoColisionaVehiculo() || recienConstruido.getOverlappingCount()==0){
			EntityManager.getGame().getPhysics().removeTickListener(this);
			EntityManager.getGame().getPhysics().remove(recienConstruido);
			removeControl(recienConstruido);
			getBody().setEnabled(true);
			recienConstruido=null;
		}
	}

	@Override
	public void prePhysicsTick(PhysicsSpace space, float tpf) {
		
	}
    
    public void onConstruido(){
    	EntityManager.getGame().getPhysics().addTickListener(this);
    	getBody().setEnabled(false);    
    	
    	recienConstruido=new GhostControl(getColisionShape());
    	addControl(recienConstruido);
    	EntityManager.getGame().getPhysics().addTickListener(this);
    	EntityManager.getGame().getPhysics().add(recienConstruido);
    }

    private boolean isRecienConstruidoColisionaVehiculo(){    	
    	for(PhysicsCollisionObject o:recienConstruido.getOverlappingObjects()){
    		if(o.getUserObject() instanceof VehiculoModel)
    			return true;
    	}
    	return false;
    }
}
