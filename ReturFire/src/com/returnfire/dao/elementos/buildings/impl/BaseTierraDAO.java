package com.returnfire.dao.elementos.buildings.impl;

import java.util.ArrayList;
import java.util.List;

import com.entity.utils.Vector2;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;

@Serializable
public class BaseTierraDAO extends EdificioVehiculosDAO{
	public static final String ICO="baseTierra.png";
	
	
	public BaseTierraDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseTierraDAO(JugadorDAO j, VehiculoDAO vDAO) {
		super(j, vDAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}
	
	@Override
	public List<RecursoDAO> getRecursosNecesarios() {
		List<RecursoDAO> recursos=new ArrayList<RecursoDAO>(1);
		
		recursos.add(new RecursoDAO(RECURSOS.PETROLEO, 3));
		recursos.add(new RecursoDAO(RECURSOS.PIEZAS, 5));
		
		return recursos;
	}

	@Override
	public String getNombre() {
		return "Base tierra";
	}

	@Override
	public String getICO() {
		return ICO;
	}
}
