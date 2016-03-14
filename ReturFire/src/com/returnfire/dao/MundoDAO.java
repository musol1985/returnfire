package com.returnfire.dao;

import java.util.HashMap;

import com.entity.network.core.dao.NetWorldDAO;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import java.util.List;
@Serializable
public class MundoDAO extends NetWorldDAO<JugadorDAO>{
	public static final int MAX_REAL_SIZE=5;

	
	public MundoDAO(){
		super();
	}
	
	public MundoDAO(String id){
		super(new HashMap<String, JugadorDAO>(), id, System.currentTimeMillis(), MAX_REAL_SIZE);
	}        
}
