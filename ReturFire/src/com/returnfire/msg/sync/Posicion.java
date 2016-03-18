package com.returnfire.msg.sync;

import com.entity.network.core.msg.sync.NetMessage;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.models.elementos.VehiculoModel;

@Serializable
public  class Posicion extends NetMessage<VehiculoModel>{

	public Vector3f pos=new Vector3f();
	public Vector3f vel=new Vector3f();
	public float[] direccion=new float[3];
	public Vector3f velAngular=new Vector3f();
	
	@Override
	public void onSend(VehiculoModel model) {
		pos=model.getWorldTranslation();
		vel=model.getBody().getLinearVelocity();
		direccion=model.getWorldRotation().toAngles(direccion);
		velAngular=model.getBody().getAngularVelocity();
	}
	@Override
	public void onReceive(VehiculoModel model) {		
		model.setLocalTranslation(pos);		
		model.getBody().setLinearVelocity(vel);
		
		model.setLocalRotation(new Quaternion().fromAngles(direccion));
		model.getBody().setAngularVelocity(velAngular);
		
		model.getPlayer().dao.setPosition(pos);
	}
	

}
