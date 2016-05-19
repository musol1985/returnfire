package com.returnfire.dao.elementos.vehiculos.impl;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.vehiculos.VehiculoArmadoDAO;

@Serializable
public class HammerDAO extends VehiculoArmadoDAO{

	@Override
	public int getMaxMunicionA() {
		return 100;
	}

	@Override
	public int getMaxMunicionB() {
		return 100;
	}

	@Override
	public int getMaxVida() {
		return 10000;
	}

}
