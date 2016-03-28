package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO.ELEMENTOS_ESTATICOS;
import com.returnfire.models.elementos.EstaticoModel;

@Serializable
public class MsgOnBalaEstatico extends MsgOnImpactoBala {
	public ELEMENTOS_ESTATICOS tipo;
	public String idEstatico;
        public int vida;
        public CellId cellId;
	
	public MsgOnBalaEstatico() {
		
	}
	
	public MsgOnBalaEstatico(String idBala, EstaticoModel estatico) {
		super(idBala);
		this.tipo=estatico.getDAO().getTipo();
		this.idEstatico=estatico.getDAO().getId();
                this.vida=estatico.getDAO().getVida();
                this.cellId=estatico.getCelda().dao.getId();
	}
}
