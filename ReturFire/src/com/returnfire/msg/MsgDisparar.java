package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;

@Serializable
public class MsgDisparar extends BaseNetMessage {
	public BALAS tipo;
	public String from;

	public MsgDisparar(BALAS tipo, String from) {
		this.tipo=tipo;
		this.from=from;
	}
	
	public MsgDisparar() {
		
	}
}
