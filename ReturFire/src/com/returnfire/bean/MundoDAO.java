package com.returnfire.bean;

import java.util.HashMap;

import com.entity.network.core.dao.NetWorldDAO;
import com.jme3.network.serializing.Serializable;
import com.returnfire.service.MundoService;
@Serializable
public class MundoDAO extends NetWorldDAO<JugadorDAO, MundoService, CeldaDAO>{
	public static final int MAX_REAL_SIZE=5000;
	
	public MundoDAO(){
		super();
	}
	
	public MundoDAO(String id){
		super(new HashMap<String, JugadorDAO>(), id, System.currentTimeMillis(), MAX_REAL_SIZE);
	}

	@Override
	public MundoService initWorldService() {
		return new MundoService(this);
	}

}
