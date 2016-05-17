package com.returnfire.dao;

import com.entity.network.core.dao.NetPlayerDAO;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.VehiculoDAO;
@Serializable
public class JugadorDAO extends NetPlayerDAO{
	private int energia;
	
	private VehiculoDAO vehiculo;

	public VehiculoDAO getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoDAO vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	
}
