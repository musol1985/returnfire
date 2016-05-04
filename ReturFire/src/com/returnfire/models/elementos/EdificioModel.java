package com.returnfire.models.elementos;

import com.entity.modules.gui.events.IOnLeftClick;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

public abstract class EdificioModel<T extends EdificioDAO, N extends IEstaticoNode> extends EstaticoModel<T, N> implements IOnLeftClick{

	@Override
	public boolean onLeftClick(boolean value, float tpf) {
		System.out.println("On left click!!!!!"+this);
		return true;
	}



}
