package com.returnfire.dao.elementos.contenedores;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public class BarrilDAO extends ContenedorDAO{


	@Override
	public RECURSOS getTipo() {
		return RECURSOS.PETROLEO;
	}

}
