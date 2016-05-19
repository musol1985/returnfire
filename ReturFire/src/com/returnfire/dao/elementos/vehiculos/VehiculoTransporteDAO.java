package com.returnfire.dao.elementos.vehiculos;

import java.util.ArrayList;
import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public abstract class VehiculoTransporteDAO extends VehiculoDAO{
	protected List<ContenedorDAO> contenedores;
	public abstract int getMaxSlots();
	
	@Override
	public void onNew() {
		contenedores=new ArrayList<ContenedorDAO>(getMaxSlots());
	}
	
	/**
	 * Retorna true si se ha anyadido, false sino
	 * @param contenedor
	 * @return
	 */
	public boolean addContenedor(ContenedorDAO contenedor){
		if(contenedores.size()<getMaxSlots()){
			contenedores.add(contenedor);
		}
		return false;
	}
	
	public int getContenedoresSize(){
		return contenedores.size();
	}

	public List<ContenedorDAO> getContenedores() {
		return contenedores;
	}
	
	
}
