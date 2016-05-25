package com.returnfire.models.elementos.buildings;

import com.entity.modules.gui.events.IOnLeftClick;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.EstaticoModel;

public abstract class EdificioModel<T extends EdificioDAO,  P extends PhysicsCollisionObject> extends EstaticoModel<T, P> implements IOnLeftClick{

	@Override
	public boolean onLeftClick(boolean value, float tpf) {
		System.out.println("On left click!!!!!"+this);
		return true;
	}



}
