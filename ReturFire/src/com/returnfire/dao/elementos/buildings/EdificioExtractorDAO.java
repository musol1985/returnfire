package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class EdificioExtractorDAO extends EdificioAlmacenDAO{
	public static int VELOCIDAD_EXTRACCION=30000;
	
	public abstract RECURSOS recursoProducido();
	public abstract int getVelocidadProduccion();
	
	public EdificioExtractorDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EdificioExtractorDAO(JugadorDAO j) {
		super(j);
		// TODO Auto-generated constructor stub
	}
	
	public int producir(){
		RecursoDAO r=getRecursoByTipo(recursoProducido(), true);
		r.cantidad+=getVelocidadProduccion();
		return r.cantidad;
	}
}
