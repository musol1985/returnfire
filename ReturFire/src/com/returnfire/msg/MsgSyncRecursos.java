package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;

@Serializable
public class MsgSyncRecursos extends BaseNetMessage {
    public CellId cellId;
    public String edificioId;
	
	public MsgSyncRecursos() {
		
	}
	
	public MsgSyncRecursos(CellId cellId, EdificioAlmacenDAO edificio) {
		this.cellId=cellId;
        this.edificioId=edificio.getId();    
	}
}
