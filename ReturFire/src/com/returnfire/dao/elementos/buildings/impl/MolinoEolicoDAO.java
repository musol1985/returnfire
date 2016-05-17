package com.returnfire.dao.elementos.buildings.impl;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

@Serializable
public class MolinoEolicoDAO extends EdificioDAO{

	
	
	public MolinoEolicoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MolinoEolicoDAO(JugadorDAO j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	public boolean isEnergia(){
		return true;
	}

	@Override
	public int getPetroleoNecesario() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPiezasNecesarias() {
		// TODO Auto-generated method stub
		return 0;
	}
}
