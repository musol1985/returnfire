package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class MsgOnImpactoBala extends BaseNetMessage {
	public String idBala;
	
	public MsgOnImpactoBala() {
		
	}
	
	public MsgOnImpactoBala(String idBala) {
		this.idBala=idBala;
	}
}
