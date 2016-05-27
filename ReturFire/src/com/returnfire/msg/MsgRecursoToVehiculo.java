package com.returnfire.msg;

import java.util.List;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public class MsgRecursoToVehiculo extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public String edificioId;
    public RECURSOS tipoRecurso;
    public int cantidad;
	
	public MsgRecursoToVehiculo() {
		
	}
	
	

	public MsgRecursoToVehiculo(CellId cellId, long vehiculoId,
			String edificioId, RECURSOS tipoRecurso, int cantidad) {
		this.cellId = cellId;
		this.vehiculoId = vehiculoId;
		this.edificioId = edificioId;
		this.tipoRecurso = tipoRecurso;
		this.cantidad = cantidad;
	}



	public CellId getCellId() {
		return cellId;
	}

	public void setCellId(CellId cellId) {
		this.cellId = cellId;
	}

	public long getVehiculoId() {
		return vehiculoId;
	}

	public void setVehiculoId(long vehiculoId) {
		this.vehiculoId = vehiculoId;
	}

	public String getEdificioId() {
		return edificioId;
	}

	public void setEdificioId(String edificioId) {
		this.edificioId = edificioId;
	}

	public RECURSOS getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(RECURSOS tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	

}
