package com.returnfire.dao.elementos.buildings;

import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class EdificioDAO extends EstaticoDAO{
	public static final String CPU="cpu";
	
	public String jugador;
    
    public EdificioDAO(){
        
    }
	
	public EdificioDAO(JugadorDAO j){
		super();
		jugador=j.getId();
	}
	@Override
	public int getVidaInicial() {
		return 100;
	}

	public boolean isCPU(){
		return CPU.equals(jugador);
	}
	
	public boolean isEnergia(){
		return false;
	}

    public boolean isConstruyendo(){
        return this instanceof ConstruyendoDAO;
    }
    
    public boolean puedeAlmacenarAlgo(){
    	return this instanceof EdificioAlmacenDAO;
    }
	
	public abstract List<RecursoDAO> getRecursosNecesarios();

	public int getNecesarioByTipo(RECURSOS tipo){
		return getNecesarioByRecurso(getRecursoNecesarioByTipo(tipo));
	}
	
	public int getNecesarioByRecurso(RecursoDAO recurso){
		if(recurso==null){
			return 0;
		}
		return recurso.cantidad;
	}
	
	public RecursoDAO getRecursoNecesarioByTipo(RECURSOS tipo){
		for(RecursoDAO r:getRecursosNecesarios()){
			if(r.tipo==tipo)
				return r;
		}
		return null;
	}
        
        
    public static EdificioDAO getFromConstruyendoDAO(ConstruyendoDAO cDao){
        EdificioDAO dao=cDao.getEdificio();
        dao.jugador=cDao.jugador;
        dao.pos=cDao.getPos();
        dao.vida=dao.getVidaInicial();
        return dao;
    }
}
