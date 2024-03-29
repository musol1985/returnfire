package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

@Serializable
public class MsgOnBuilt extends BaseNetMessage {
	public EdificioDAO edificio;
	public CellId cellId;

	public MsgOnBuilt(EdificioDAO edificio, CellId id) {
		this.edificio=edificio;
		this.cellId=id;
	}
	
	public MsgOnBuilt() {
		
	}
}
