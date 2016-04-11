package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public class EdificioVehiculosDAO extends EdificioDAO{

	private VehiculoDAO vDAO;
	
        public EdificioVehiculosDAO(){
            
        }
        
	public EdificioVehiculosDAO(JugadorDAO j, VehiculoDAO vDAO, EDIFICIOS tipo){
		super(j, tipo);
		this.vDAO=vDAO;
	}

	public boolean tieneVehiculo(){
		return vDAO!=null;
	}
}
