package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.ContenedorDAO.RECURSO;
import com.returnfire.dao.elementos.EstaticoDAO;

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
        
        public boolean puedeCogerMas(ContenedorDAO.RECURSO r){
            return false;
        }
        public boolean addRecurso(RECURSO r){
            return false;
        }
        public boolean isConstruyendo(){
            return this instanceof ConstruyendoDAO;
        }
	
	public abstract int getPetroleoNecesario();
	public abstract int getPiezasNecesarias();
        
        
        public static EdificioDAO getFromConstruyendoDAO(ConstruyendoDAO cDao){
            EdificioDAO dao=cDao.getEdificio();
            dao.jugador=cDao.jugador;
            dao.pos=cDao.getPos();
            dao.vida=dao.getVidaInicial();
            return dao;
        }
}
