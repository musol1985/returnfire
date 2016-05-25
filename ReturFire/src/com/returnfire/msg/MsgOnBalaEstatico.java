package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.EstaticoDAO.ELEMENTOS_ESTATICOS;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.EstaticoModel;

@Serializable
public class MsgOnBalaEstatico extends MsgOnImpactoBala {
	public ELEMENTOS_ESTATICOS tipo;
	public String idEstatico;
	public long celdaTimestampOld;
        public int vida;
        public CellId cellId;
        public Vector3f v;
	
	public MsgOnBalaEstatico() {
		
	}
	
	public MsgOnBalaEstatico(String idBala, Vector3f v, EstaticoModel<? extends EstaticoDAO, ? extends PhysicsControl> estatico, CeldaModel celda, long t) {
		super(idBala);
                this.v=v;
		this.celdaTimestampOld=t;
		this.tipo=estatico.getDAO().getTipo();
		this.idEstatico=estatico.getDAO().getId();
                this.vida=estatico.getDAO().getVida();
                this.cellId=celda.dao.getId();
	}
}
