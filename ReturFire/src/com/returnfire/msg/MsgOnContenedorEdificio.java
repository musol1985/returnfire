package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import java.util.List;

@Serializable
public class MsgOnContenedorEdificio extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public String edificioId;
    public List<Long> contenedorId;
	
	public MsgOnContenedorEdificio() {
		
	}
	
	public MsgOnContenedorEdificio(CellId cellId, long vehiculoId, String edificioId, List<Long> contenedorId) {
		this.cellId=cellId;
		this.vehiculoId=vehiculoId;
		this.contenedorId=contenedorId;
                this.edificioId=edificioId;
	}
}
