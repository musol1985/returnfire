package com.returnfire.dao.elementos.vehiculos;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class VehiculoRecolectorDAO extends VehiculoTransporteDAO{
	public abstract boolean puedeRecolectar(RECURSOS recurso);
	/**
	 * Segundos que necesita el vehiculo para recolectar 1 unidad
	 * @param recurso
	 * @return
	 */
	public abstract int getVelocidadRecoleccion(RECURSOS recurso);
	
}
