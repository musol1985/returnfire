package com.returnfire.dao;

import com.entity.network.core.dao.NetPlayerDAO;
import com.jme3.network.serializing.Serializable;
@Serializable
public class JugadorDAO extends NetPlayerDAO{
	public enum VEHICULOS{NINGUNO, HAMMER}
	
	private VEHICULOS vehiculo;

	public VEHICULOS getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VEHICULOS vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	
}
