package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class MsgErrOnBuilt extends BaseNetMessage {	
	public String tipoEdificio; 
	
	public MsgErrOnBuilt() {
		
	}

	public MsgErrOnBuilt(String tipoEdificio) {
		this.tipoEdificio = tipoEdificio;
	}
	
	
}
