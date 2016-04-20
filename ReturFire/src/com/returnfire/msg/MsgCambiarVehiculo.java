package com.returnfire.msg;

import com.entity.network.core.msg.BaseNetMessage;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public class MsgCambiarVehiculo extends BaseNetMessage {
	public VehiculoDAO old;
	public VehiculoDAO newVehiculo;
	
	public MsgCambiarVehiculo() {
		
	}
	
	public MsgCambiarVehiculo(VehiculoDAO newVehiculo, VehiculoDAO old) {
		this.newVehiculo=newVehiculo;
		this.old=old;
	}
}
