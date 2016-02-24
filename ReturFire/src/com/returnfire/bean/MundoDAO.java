package com.returnfire.bean;

import com.entity.network.core.bean.NetWorld;
import com.jme3.network.serializing.Serializable;
import com.returnfire.service.MundoService;
@Serializable
public class MundoDAO extends NetWorld<JugadorDAO, MundoService, CeldaDAO>{

	@Override
	public MundoService initWorldService() {
		return new MundoService(this);
	}

}
