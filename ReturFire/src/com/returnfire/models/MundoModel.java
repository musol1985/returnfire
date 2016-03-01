package com.returnfire.models;

import com.entity.network.core.models.NetWorld;
import com.returnfire.dao.MundoDAO;


public class MundoModel extends NetWorld<MundoDAO, CeldaModel>{
        public static final int CELL_SIZE=512;
        
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

        
}
