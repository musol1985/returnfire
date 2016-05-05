package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

@Serializable
public class MsgBuild extends BaseNetMessage {
	public Class<? extends BuildNode> edificio;
	public String from;
	public Vector3f pos;
	public float ang;

	public MsgBuild(Class<? extends BuildNode> edificio, Vector3f pos, float ang, String from) {
		this.edificio=edificio;
		this.ang=ang;
		this.from=from;
	}
	
	public MsgBuild() {
		
	}
}
