package com.returnfire.dao.elementos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public abstract class ElementoDAO implements java.io.Serializable{
	protected Vector3f pos;

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	
	public abstract String getId();	
}
