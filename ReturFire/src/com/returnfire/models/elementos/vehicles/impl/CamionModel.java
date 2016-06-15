package com.returnfire.models.elementos.vehicles.impl;

import com.entity.anot.components.model.VehicleComponent;
import com.entity.anot.components.model.WheelComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.vehiculos.impl.CamionDAO;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

@ModelEntity(asset = "Models/vehicles/camion.j3o", attach = false)
public class CamionModel extends VehiculoTransporteModel<VehicleControl, CamionDAO>{

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
	public Vector3f getCoordenadasContenedorByIndex(int index) {
		int z=(int)index/2;
		int x=index-(z*2);
		return new Vector3f(x,0,z);
	}

	@Override
	public Vector3f getPosicionContenedorByCoordenadas(Vector3f coordenadas){
		return coordenadas.multLocal(-2, 0, -2);
	}
}
