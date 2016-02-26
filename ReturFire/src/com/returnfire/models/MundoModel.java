package com.returnfire.models;

import com.entity.network.core.dao.NetWorldCellDAO;
import com.entity.network.core.models.NetWorld;
import com.returnfire.dao.MundoDAO;


public class MundoModel extends NetWorld<MundoDAO, NetWorldCellDAO>{

	@Override
	public int getCellSize() {
		return 100;
	}

}
