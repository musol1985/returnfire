package com.returnfire.msg;

import com.entity.network.core.beans.CellId;
import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import java.util.List;

@Serializable
public class MsgOnEdificioConstruido extends BaseNetMessage {
    public CellId cellId;
    public long vehiculoId;
    public String construyendoId;
    public EdificioDAO nuevoEdificio;
    public List<Long> contenedorId;
	
    public MsgOnEdificioConstruido() {

    }

    public MsgOnEdificioConstruido(CellId cellId, long vehiculoId, String construyendoId, EdificioDAO nuevoEdificio, List<Long> contenedorId) {
            this.cellId=cellId;
            this.vehiculoId=vehiculoId;
            this.contenedorId=contenedorId;
            this.construyendoId=construyendoId;
            this.nuevoEdificio=nuevoEdificio;
    }
}
