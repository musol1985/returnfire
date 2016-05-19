package com.returnfire.dao.elementos.contenedores;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO;

@Serializable
public class GarrafaDAO extends RecursoDAO{

	@Override
	public RECURSO getTipo() {
		return RECURSO.PETROLEO;
	}

	@Override
	public int getCantidad() {
		return 50;
	}

}
