package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class EdificioExtractorDAO extends EdificioAlmacenDAO{
	public static int VELOCIDAD_EXTRACCION=1000;//30000;
	
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
	
	/**
	 * Retorna true/false si ha podido producir el recurso
	 * @return
	 */
	public boolean producir(){
		RecursoDAO r=getRecursoByTipo(recursoProducido(), true);
		if(puedeAlmacenarMas(r.tipo)){
			r.cantidad+=getVelocidadProduccion();
			return true;
		}
		return false;
	}
}
