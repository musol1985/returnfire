package com.returnfire.msg;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO.ELEMENTOS_ESTATICOS;

@Serializable
public class MsgOnBalaEstatico extends MsgOnImpactoBala {
	public ELEMENTOS_ESTATICOS tipo;
	public String idEstatico;
	
	public MsgOnBalaEstatico() {
		
	}
	
	public MsgOnBalaEstatico(String idBala, ELEMENTOS_ESTATICOS tipo, String idEstatico) {
		super(idBala);
		this.tipo=tipo;
		this.idEstatico=idEstatico;
	}
}
