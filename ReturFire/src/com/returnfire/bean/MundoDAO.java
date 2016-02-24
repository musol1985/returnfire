package com.returnfire.bean;

import com.entity.network.core.bean.NetWorld;
import com.returnfire.service.MundoService;

public class MundoDAO extends NetWorld<JugadorDAO, MundoService, CeldaDAO>{

	@Override
	public MundoService initWorldService() {
		return new MundoService(this);
	}

}
