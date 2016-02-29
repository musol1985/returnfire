package com.returnfire.models;

import com.entity.network.core.models.NetWorld;
import com.returnfire.dao.MundoDAO;


public class MundoModel extends NetWorld<MundoDAO, CeldaModel>{

	@Override
	public int getCellSize() {
		return 100;
	}

}
