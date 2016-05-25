package com.returnfire.models.elementos.buildings;

import com.entity.anot.Entity;
import com.jme3.bullet.control.RigidBodyControl;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

public abstract class EdificioConstruibleModel<T extends EdificioDAO, N extends BuildNode> extends EdificioModel<T, RigidBodyControl>{

    @Entity
    public N building;

	public N getNodo() {
		return building;
	}

}
