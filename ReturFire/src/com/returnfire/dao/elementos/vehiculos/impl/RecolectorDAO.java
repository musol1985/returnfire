package com.returnfire.dao.elementos.vehiculos.impl;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.vehiculos.VehiculoRecolectorDAO;

@Serializable
public class RecolectorDAO extends VehiculoRecolectorDAO{


	@Override
	public int getMaxVida() {
		return 10000;
	}

    @Override
    public int getMaxSlots() {
        return 6;
    }

	@Override
	public boolean puedeRecolectar(RECURSOS recurso) {
		return true;
	}

	@Override
	public int getVelocidadRecoleccion(RECURSOS recurso) {
		return 10;
	}

}
