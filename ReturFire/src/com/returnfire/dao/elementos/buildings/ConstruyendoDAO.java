package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;

@Serializable
public class ConstruyendoDAO extends EstaticoDAO{
	public static final String CPU="cpu";
	
	public String jugador;
	public String tipo;
    
    public ConstruyendoDAO(){
        
    }
	
	public ConstruyendoDAO(JugadorDAO j, String tipo){
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
	

}
