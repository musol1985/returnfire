package com.returnfire.dao.elementos.buildings.impl;

import java.util.ArrayList;
import java.util.List;

import com.entity.utils.Vector2;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

@Serializable
public class MolinoEolicoDAO extends EdificioDAO{
	public static final String ICO="molinoEolico.png";
	
	
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
	public Vector2 getSize() {
		return new Vector2(2,2);
	}

	@Override
	public List<RecursoDAO> getRecursosNecesarios() {
		List<RecursoDAO> recursos=new ArrayList<RecursoDAO>(1);
		
		recursos.add(new RecursoDAO(RECURSOS.PETROLEO, 3));
		
		return recursos;
	}
	
	@Override
	public String getNombre() {
		return "Molino Eolico";
	}
	
	@Override
	public String getICO() {
		return ICO;
	}
}
