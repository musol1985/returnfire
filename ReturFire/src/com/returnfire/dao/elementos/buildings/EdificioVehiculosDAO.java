package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public class EdificioVehiculosDAO extends EdificioDAO{

	private VehiculoDAO vDAO;
	
	private int petroleo;
	private int materiales;
	private int municion;
	
    public EdificioVehiculosDAO(){
        
    }
        
	public EdificioVehiculosDAO(JugadorDAO j, VehiculoDAO vDAO, EDIFICIOS tipo){
		super(j, tipo);
		this.vDAO=vDAO;
	}

	public boolean tieneVehiculo(){
		return vDAO!=null;
	}
	
	public void setExtensiones(int petroleo, int materiales, int municion){
		this.petroleo=petroleo;
		this.materiales=materiales;
		this.municion=municion;
	}
	
	public boolean tieneExtensionPetroleo(){
		return petroleo>-1;
	}
	
	public boolean tieneExtensionPiezas(){
		return materiales>-1;
	}
	
	public boolean tieneExtensionMunicion(){
		return municion>-1;
	}

	public int getPetroleo() {
		return petroleo;
	}

	public void setPetroleo(int petroleo) {
		this.petroleo = petroleo;
	}

	public int getMateriales() {
		return materiales;
	}

	public void setMateriales(int materiales) {
		this.materiales = materiales;
	}

	public int getMunicion() {
		return municion;
	}

	public void setMunicion(int municion) {
		this.municion = municion;
	}
	
	
}
