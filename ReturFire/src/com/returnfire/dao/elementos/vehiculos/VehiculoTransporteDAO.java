package com.returnfire.dao.elementos.vehiculos;

import java.util.ArrayList;
import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public abstract class VehiculoTransporteDAO extends VehiculoDAO{
	protected List<RecursoDAO> recursos;
	public abstract int getMaxSlots();
	
	@Override
	public void onNew() {
		recursos=new ArrayList<RecursoDAO>(getMaxSlots());
	}
	
	
}
