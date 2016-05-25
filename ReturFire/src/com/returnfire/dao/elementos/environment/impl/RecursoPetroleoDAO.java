package com.returnfire.dao.elementos.environment.impl;

import com.entity.utils.Vector2;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.environment.RecursoNaturalDAO;

@Serializable
public class RecursoPetroleoDAO extends RecursoNaturalDAO{

	@Override
	public RECURSOS getTipoRecurso() {
		return RECURSOS.PETROLEO;
	}
	
	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}
}
