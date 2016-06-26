package com.returnfire.models.elementos.vehicles.impl;

import com.entity.adapters.modifiers.ModifierPosition;
import com.entity.adapters.modifiers.ModifierRotation;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.components.model.VehicleComponent;
import com.entity.anot.components.model.WheelComponent;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.entity.utils.Utils;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.returnfire.dao.elementos.vehiculos.impl.RecolectorDAO;
import com.returnfire.models.elementos.vehicles.VehiculoRecolectorModel;

@ModelEntity(asset = "Models/vehicles/extractor.j3o", attach = false)
public class RecolectorModel extends VehiculoRecolectorModel<VehicleControl, RecolectorDAO>{

	@VehicleComponent(mass=800.0f,
		wheels={
			@WheelComponent(nodeName="wheel_fl", offset={1.8f,1f,4.6f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_fr", offset={-1.8f,1f,4.6f}, frontWheel=true),
			@WheelComponent(nodeName="wheel_rl2", offset={1.8f,1f,-3.5f}),
			@WheelComponent(nodeName="wheel_rr2", offset={-1.8f,1f,-3.5f}),
		})
	@CustomCollisionShape(methodName="getCollisionShape")
	public VehicleControl body;

    @SubModelComponent(name = "extractor")
    public Node extractor;

    private Vector3f extractorPos;
	
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
		return new Vector3f(0,0,index);
	}

	@Override
	public Vector3f getPosicionContenedorByCoordenadas(Vector3f coordenadas){
		return coordenadas.multLocal(0, 0, -2);
	}

	@Override
	public void extenderGrua(){
		if(!isGruaExtendida()){
			setGruaExtendida(true);
			clearAnims();
                        extractorPos=extractor.getLocalTranslation().clone();
                        extractor.addControl(new ModifierPosition(extractor.getLocalTranslation(), extractor.getLocalTranslation().add(0, 0, -0.5f), 1000));		 
		}
	}
	
	@Override
	public void contraerGrua(){
		if(isGruaExtendida()){
			setGruaExtendida(false);
			clearAnims();
                        extractor.addControl(new ModifierPosition(extractor.getLocalTranslation(), extractorPos, 1000));		 
		}
	}
	
	private void clearAnims(){
		extractor.removeControl(ModifierPosition.class);
	}
}
