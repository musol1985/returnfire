package com.returnfire.models.elementos.buildings;

import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

public abstract class EdificioConstruibleModel<T extends EdificioDAO, N extends BuildNode> extends EdificioModel<T>{
	public abstract N getNodo();
}
