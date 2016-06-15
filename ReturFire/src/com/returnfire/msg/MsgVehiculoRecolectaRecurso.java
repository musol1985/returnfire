package com.returnfire.msg;

import java.util.List;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public class MsgVehiculoRecolectaRecurso extends BaseNetMessage {
    public long vehiculoId;
    public RECURSOS tipoRecurso;
	
	public MsgVehiculoRecolectaRecurso() {
		
	}
	
	

	public MsgVehiculoRecolectaRecurso(long vehiculoId,RECURSOS tipoRecurso) {
		this.vehiculoId = vehiculoId;
		this.tipoRecurso = tipoRecurso;
	}

	public long getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(long vehiculoId) {
		this.vehiculoId = vehiculoId;
	}


	public RECURSOS getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(RECURSOS tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

}
