package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

@Serializable
public class MsgBuild extends BaseNetMessage {
	public String edificio; 
        public String nodo;
	public String from;
	public Vector3f pos;
	public float ang;

	public MsgBuild(Class<? extends EdificioDAO> edificio, Class<? extends BuildNode> nodo, Vector3f pos, float ang, String from) {
		this.edificio=edificio.getName();
                this.nodo=nodo.getName();
                this.pos=pos;
		this.ang=ang;
		this.from=from;
	}
	
	public MsgBuild() {
		
	}
}
