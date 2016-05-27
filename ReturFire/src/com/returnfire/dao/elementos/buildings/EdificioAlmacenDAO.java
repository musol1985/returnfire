package com.returnfire.dao.elementos.buildings;

import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import java.util.ArrayList;

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
	
        @Override
	public void onNew(){
            recursos=new ArrayList<RecursoDAO>();
        }
	/**
	 * Indica si puede almacenar mas unidades de ese recurso
	 * @param tipo
	 * @return
	 */
	public abstract boolean puedeAlmacenarMas(RECURSOS tipo);

	/**
	 * Cantidad de ese recurso que puede ser almacenada
	 * Tiene en cuenta la cantidad que ya tiene almacenado
	 * @param tipo
	 * @return
	 */
	public abstract int getCantidadQuePuedeAlmacenar(RECURSOS tipo);
	
	/**
	 * Cantidad maxima de ese recurso que puede ser almacenada
	 * No tiene en cuenta lo que tiene almacenado
	 * @param tipo
	 * @return
	 */
	public abstract int getCantidadMaximaQuePuedeAlmacenar(RECURSOS tipo);
        
        public boolean puedeAlmacenar(RECURSOS tipo){
            return getCantidadMaximaQuePuedeAlmacenar(tipo)>0;
        }

	
	public int getCantidadRecursoByTipo(RECURSOS tipo){
		RecursoDAO r=getRecursoByTipo(tipo);
		if(r!=null)
			return r.cantidad;
		return 0;
	}

	public RecursoDAO getRecursoByTipo(RECURSOS tipo){
            return getRecursoByTipo(tipo, false);
	}
	
	/*
	 * Comprueba si tiene este recurso en la lista de recursos
	 */
	public boolean hasRecurso(RECURSOS tipo){
		return getRecursoByTipo(tipo,false)!=null;
	}
        
        public RecursoDAO getRecursoByTipo(RECURSOS tipo, boolean addIfNotExist){
		for(RecursoDAO r:recursos){
			if(r.tipo==tipo)
				return r;
		}
                if(addIfNotExist){
                    RecursoDAO r=new RecursoDAO(tipo, 0);
                    recursos.add(r);
                    return r;
                }
		return null;
	}
        
    public List<RecursoDAO> getRecursosAlmacenados(){
    	return recursos;
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
	
	/*
	 * Le resta cantidad al recurso R
	 * En caso de que no tenga de ese recurso, lo anyade
	 */
	public void removeRecursoByTipo(RECURSOS r, int cantidad){
		RecursoDAO rEdificio=getRecursoByTipo(r);
		if(rEdificio!=null){
			rEdificio.cantidad-=cantidad;			
		}else{
			recursos.add(new RecursoDAO(r, cantidad));
		}
	}
}
