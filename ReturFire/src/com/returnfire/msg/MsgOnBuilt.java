package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

@Serializable
public class MsgOnBuilt extends BaseNetMessage {
	public EdificioDAO edificio;

	public MsgOnBuilt(EdificioDAO edificio) {
		this.edificio=edificio;
	}
	
	public MsgOnBuilt() {
		
	}
}
