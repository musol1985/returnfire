package com.returnfire.dao.elementos.contenedores;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO;

@Serializable
public class CajaDAO extends RecursoDAO{

	@Override
	public RECURSO getTipo() {
		return RECURSO.PIEZAS;
	}

	@Override
	public int getCantidad() {
		return 50;
	}

}
