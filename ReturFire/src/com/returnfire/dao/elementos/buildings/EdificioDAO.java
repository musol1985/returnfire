package com.returnfire.dao.elementos.buildings;

import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;

public class EdificioDAO extends EstaticoDAO{
	public static final String CPU="cpu";
	
	public String jugador;
	
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
}
