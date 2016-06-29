package com.returnfire.dao.elementos.vehiculos.impl;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;

@Serializable
public class CamionDAO extends VehiculoTransporteDAO{


	@Override
	public int getMaxVida() {
		return 10000;
	}

    @Override
    public int getMaxSlots() {
        return 16;
    }
}
