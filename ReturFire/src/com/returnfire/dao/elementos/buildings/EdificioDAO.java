package com.returnfire.dao.elementos.buildings;

import java.util.ArrayList;
import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;

@Serializable
public class EdificioDAO extends EstaticoDAO{
    public enum EDIFICIOS{BASE_TIERRA_PEQUE}
    
	public static final String CPU="cpu";
	
	public String jugador;
    private EDIFICIOS tipo;
    
    public EdificioDAO(){
        
    }
	
	public EdificioDAO(JugadorDAO j, EDIFICIOS tipo){
		super();
		jugador=j.getId();
        this.tipo=tipo;
	}
	@Override
	public int getVidaInicial() {
		return 100;
	}

	public boolean isCPU(){
		return CPU.equals(jugador);
	}
        
        public EDIFICIOS getTipoEdificio(){
            return tipo;
        }
}
