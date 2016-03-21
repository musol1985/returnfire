package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.elementos.BulletModel.BALAS;

@Serializable
public class MsgOnDisparar extends BaseNetMessage {
	public BALAS tipo;
	public String from;
	public String id;
	public Vector3f position;
	public float[] rotation;
	
	public MsgOnDisparar() {
		
	}
}
