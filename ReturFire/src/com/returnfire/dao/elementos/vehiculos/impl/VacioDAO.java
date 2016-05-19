package com.returnfire.dao.elementos.vehiculos.impl;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public class VacioDAO extends VehiculoDAO{

	@Override
	public int getMaxVida() {
		return 0;
	}

	@Override
	public void onNew() {
		
	}

}
