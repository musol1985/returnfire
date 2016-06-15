package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;

@Serializable
public class MsgOnVehiculoRecolecta extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public ContenedorDAO contenedor;
	
	public MsgOnVehiculoRecolecta() {
		
	}
	
	public MsgOnVehiculoRecolecta(CellId cellId, long vehiculoId, ContenedorDAO contenedor) {
		this.cellId=cellId;
		this.vehiculoId=vehiculoId;
		this.contenedor=contenedor;
	}
}
