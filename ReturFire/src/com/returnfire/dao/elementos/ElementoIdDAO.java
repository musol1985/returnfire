package com.returnfire.dao.elementos;

import com.entity.utils.SUID;
import com.jme3.network.serializing.Serializable;

@Serializable
public class ElementoIdDAO extends ElementoDAO{
	protected long id;

	public long getIdLong() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setNewID(){
		this.id=SUID.id().get();
	}

	@Override
	public String getId() {
		return String.valueOf(id);
	}
}
