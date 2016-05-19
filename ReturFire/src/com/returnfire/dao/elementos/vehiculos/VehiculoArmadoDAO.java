package com.returnfire.dao.elementos.vehiculos;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public abstract class VehiculoArmadoDAO extends VehiculoDAO{
	protected int municionA;
	protected int municionB;
	
	public int getMunicionA() {
		return municionA;
	}
	public int getMunicionB() {
		return municionB;
	}
	public void setMunicionA(int municionA) {
		this.municionA = municionA;
	}
	public void setMunicionB(int municionB) {
		this.municionB = municionB;
	}
	public abstract int getMaxMunicionA();
	public abstract int getMaxMunicionB();
	
	@Override
	public void onNew() {
		municionA=getMaxMunicionA();
		municionB=getMaxMunicionB();
	}
	
	
}
