package com.returnfire.dao.elementos.buildings;

import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class EdificioAlmacenDAO extends EdificioDAO{
	public List<RecursoDAO> recursos;
	
	public static final String CPU="cpu";
	
	public String jugador;
    
    public EdificioAlmacenDAO(){
        
    }
	
	public EdificioAlmacenDAO(JugadorDAO j){
		super(j);
	}
	
	
	public abstract boolean puedeAlmacenarMas(RECURSOS tipo);
	public abstract List<RecursoDAO> getCantidadesQuePuedeAlmacenar();
	public abstract int getCantidadQuePuedeAlmacenar(RECURSOS tipo);

	
	public int getCantidadRecursoByTipo(RECURSOS tipo){
		RecursoDAO r=getRecursoByTipo(tipo);
		if(r!=null)
			return r.cantidad;
		return 0;
	}

	public RecursoDAO getRecursoByTipo(RECURSOS tipo){
		for(RecursoDAO r:recursos){
			if(r.tipo==tipo)
				return r;
		}
		return null;
	}
	
	/*
	 * Le suma cantidad al recurso R
	 * En caso de que no tenga de ese recurso, lo anyade
	 */
	public void addRecursoByTipo(RECURSOS r, int cantidad){
		RecursoDAO rEdificio=getRecursoByTipo(r);
		if(rEdificio!=null){
			rEdificio.cantidad+=cantidad;			
		}else{
			recursos.add(new RecursoDAO(r, cantidad));
		}
	}
}
