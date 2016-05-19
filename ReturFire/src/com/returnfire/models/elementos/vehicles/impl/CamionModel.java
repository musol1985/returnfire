package com.returnfire.models.elementos.vehicles.impl;

import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.components.model.VehicleComponent;
import com.entity.anot.components.model.WheelComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

@ModelEntity(asset = "Models/vehicles/camion.j3o", attach = false)
public class CamionModel extends VehiculoTransporteModel<VehicleControl>{

	@VehicleComponent(mass=800.0f,
		wheels={
			@WheelComponent(nodeName="wheel_fl", offset={1.8f,1f,4.6f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_fr", offset={-1.8f,1f,4.6f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_rl2", offset={1.8f,1f,-3.5f}),
			@WheelComponent(nodeName="wheel_rr2", offset={-1.8f,1f,-3.5f}),
		})
	@CustomCollisionShape(methodName="getCollisionShape")
	public VehicleControl body;


	
    public CompoundCollisionShape getCollisionShape() {
    	CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.8f, 1f, 6f));
        compoundShape.addChildShape(box, new Vector3f(0, 2, 0));
        return compoundShape;    
    }

	@Override
	public VehicleControl getBody() {
		return body;
	}

	@Override
	public void colocarContenedor(ContenedorModel c, int size) throws Exception {		
		if(size>1){
			Vector3f pos=null;
			if(size%2==0){
				pos=dao.getContenedores().get(size-1).getPos().add(0, 0, 10);
			}else{
				pos=contenedoresReference.getChild(size-2).getLocalTranslation().add(10, 0, 0);
			}
			c.setLocalTranslation(pos);
		}else{
			c.setLocalTranslation(contenedoresReference.getLocalTranslation());
		}
		c.getDAO().setPos(c.getLocalTranslation().clone());
	}

 
}
