package com.returnfire.msg;

import java.util.List;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public class MsgOnRecursoToVehiculo extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public String edificioId;
    public List<ContenedorDAO> contenedores;
	
	public MsgOnRecursoToVehiculo() {
		
	}
	
	

	public MsgOnRecursoToVehiculo(CellId cellId, long vehiculoId,
			String edificioId, List<ContenedorDAO> contenedores) {
		this.cellId = cellId;
		this.vehiculoId = vehiculoId;
		this.edificioId = edificioId;
		this.contenedores = contenedores;
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

	public List<ContenedorDAO> getContenedores() {
		return contenedores;
	}

	public void setContenedores(List<ContenedorDAO> contenedores) {
		this.contenedores = contenedores;
	}

}
