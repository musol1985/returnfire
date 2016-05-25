package com.returnfire.dao.elementos.environment.impl;

import com.entity.utils.Vector2;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.environment.RecursoNaturalDAO;

public class RecursoHierroDAO extends RecursoNaturalDAO{

	@Override
	public RECURSOS getTipoRecurso() {
		return RECURSOS.HIERRO;
	}
	
	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}
}
