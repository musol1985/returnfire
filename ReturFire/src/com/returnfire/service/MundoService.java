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
	public CeldaDAO createNewCell(Vector2 cellId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public JugadorDAO createNewPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
