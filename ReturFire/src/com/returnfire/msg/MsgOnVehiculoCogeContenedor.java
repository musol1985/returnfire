package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
public class MsgOnVehiculoCogeContenedor extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public long contenedorId;
	
	public MsgOnVehiculoCogeContenedor() {
		
	}
	
	public MsgOnVehiculoCogeContenedor(CellId cellId, long vehiculoId, long contenedorId) {
		this.cellId=cellId;
		this.vehiculoId=vehiculoId;
		this.contenedorId=contenedorId;
	}
}
