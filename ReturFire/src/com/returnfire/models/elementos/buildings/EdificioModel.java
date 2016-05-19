package com.returnfire.models.elementos.buildings;

import com.entity.modules.gui.events.IOnLeftClick;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.EstaticoModel;

public abstract class EdificioModel<T extends EdificioDAO> extends EstaticoModel<T> implements IOnLeftClick{

	@Override
	public boolean onLeftClick(boolean value, float tpf) {
		System.out.println("On left click!!!!!"+this);
		return true;
	}



}
