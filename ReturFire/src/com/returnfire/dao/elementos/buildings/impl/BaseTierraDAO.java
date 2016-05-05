package com.returnfire.dao.elementos.buildings.impl;

import com.entity.utils.Vector2;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;

@Serializable
public class BaseTierraDAO extends EdificioVehiculosDAO{

	
	
	public BaseTierraDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseTierraDAO(JugadorDAO j, VehiculoDAO vDAO) {
		super(j, vDAO);
		// TODO Auto-generated constructor stub
	}

}
