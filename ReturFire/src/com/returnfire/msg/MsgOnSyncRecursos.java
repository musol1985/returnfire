package com.returnfire.msg;

import java.util.List;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;

@Serializable
public class MsgOnSyncRecursos extends BaseNetMessage {
    public CellId cellId;
    public String edificioId;
    public List<RecursoDAO> recursos;
	
	public MsgOnSyncRecursos() {
		
	}
	
	public MsgOnSyncRecursos(CellId cellId, EdificioAlmacenDAO edificio) {
		this.cellId=cellId;
        this.edificioId=edificio.getId();
        this.recursos=edificio.getRecursosAlmacenados();        
	}
}
