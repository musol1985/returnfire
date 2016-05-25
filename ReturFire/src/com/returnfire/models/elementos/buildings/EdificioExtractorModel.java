package com.returnfire.models.elementos.buildings;

import com.entity.anot.OnUpdate;
import com.returnfire.dao.elementos.buildings.EdificioExtractorDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

public abstract class EdificioExtractorModel<T extends EdificioExtractorDAO, N extends BuildNode> extends EdificioAlmacenModel<T, N>{
	private long t;
	
	@OnUpdate
	public void onUpdate(float tpf){
		if(System.currentTimeMillis()-t>EdificioExtractorDAO.VELOCIDAD_EXTRACCION){
			
			getDAO().producir();
			
			t=System.currentTimeMillis();
		}
	}
	
	
}
