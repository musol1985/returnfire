package com.returnfire.msg.sync;

import com.entity.network.NetMessage;
import com.jme3.math.Vector3f;
import com.returnfire.models.elementos.VehiculoModel;

public  class Posicion extends NetMessage<VehiculoModel>{

	public Vector3f pos=new Vector3f();
	public Vector3f vel=new Vector3f();
	public float ang=0;
	
	@Override
	public void onSend(VehiculoModel model) {
		pos=model.getWorldTranslation();
		vel=model.getBody().getLinearVelocity();
		//ang=model.getWorldRotation().getW();
	}
	@Override
	public void onReceive(VehiculoModel model) {		
		model.setLocalTranslation(pos);		
		model.getBody().setLinearVelocity(vel);
		model.getPlayer().dao.setPosition(pos);
		//model.setLocalRotation(new Quaternion().fromAngles(0, ang, 0));
	}
	

}
