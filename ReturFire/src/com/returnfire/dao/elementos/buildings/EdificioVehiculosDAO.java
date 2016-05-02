package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public abstract class EdificioVehiculosDAO extends EdificioExtensibleDAO{

	private VehiculoDAO vDAO;
	
	
    public EdificioVehiculosDAO(){
        
    }
        
	public EdificioVehiculosDAO(JugadorDAO j, VehiculoDAO vDAO){
		super(j);
		this.vDAO=vDAO;
	}

	public boolean tieneVehiculo(){
		return vDAO!=null;
	}

	public VehiculoDAO getvDAO() {
		return vDAO;
	}
	
	
}
