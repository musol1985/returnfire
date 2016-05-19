package com.returnfire.dao.elementos.contenedores;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO;

@Serializable
public class BarrilDAO extends RecursoDAO{

	@Override
	public RECURSO getTipo() {
		return RECURSO.PETROLEO;
	}

	@Override
	public int getCantidad() {
		return 200;
	}

}
