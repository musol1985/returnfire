package com.returnfire.dao.elementos.buildings.impl;

import java.util.ArrayList;
import java.util.List;

import com.entity.utils.Vector2;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.buildings.EdificioExtractorDAO;

@Serializable
public class ExtractorHierroDAO extends EdificioExtractorDAO{

	
	
	public ExtractorHierroDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExtractorHierroDAO(JugadorDAO j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	public boolean isEnergia(){
		return false;
	}
	
	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}

	@Override
	public List<RecursoDAO> getRecursosNecesarios() {
		List<RecursoDAO> recursos=new ArrayList<RecursoDAO>(1);
		
		recursos.add(new RecursoDAO(RECURSOS.PETROLEO, 2));
		
		return recursos;
	}

	@Override
	public boolean puedeAlmacenarMas(RECURSOS tipo) {
		return getCantidadQuePuedeAlmacenar(tipo)>0;
	}

	@Override
	public int getCantidadQuePuedeAlmacenar(RECURSOS tipo) {
		int tiene=getCantidadRecursoByTipo(tipo);
		return getCantidadMaximaQuePuedeAlmacenar(tipo)-tiene;
	}

	@Override
	public int getCantidadMaximaQuePuedeAlmacenar(RECURSOS tipo) {
		if(tipo==RECURSOS.HIERRO)
			return 20;
		
		return 0;
	}

	@Override
	public RECURSOS recursoProducido() {
		return RECURSOS.HIERRO;
	}

	@Override
	public int getVelocidadProduccion() {
		return 1;
	}
}
