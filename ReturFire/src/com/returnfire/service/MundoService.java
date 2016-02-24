package com.returnfire.service;

import com.entity.network.core.service.WorldService;
import com.entity.utils.Vector2;
import com.returnfire.bean.CeldaDAO;
import com.returnfire.bean.JugadorDAO;
import com.returnfire.bean.MundoDAO;

public class MundoService extends WorldService<MundoDAO, JugadorDAO, CeldaDAO>{

	public MundoService(MundoDAO world) {
		super(world);
	}



	@Override
	public boolean isCellInLimits(Vector2 celldId) {
		return super.isCellInLimits(celldId);
	}


	/**
	 * Solo se llama en el server. El cliente NO puede crear una nueva celda
	 */
	@Override	
	public CeldaDAO createNewCell(Vector2 cellId) {
		return null;
	}



	@Override
	public JugadorDAO createNewPlayer(String name) {
            JugadorDAO jugador=new JugadorDAO();
            jugador.setId(name);
            return jugador;
	}

}
