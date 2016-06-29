package com.returnfire.models.elementos.buildings.nodos;

import com.entity.adapters.EffectParticleAdapter;
import com.entity.adapters.listeners.IModifierOnFinish;
import com.entity.adapters.modifiers.ModifierDelay;
import com.entity.adapters.modifiers.ModifierPosition;
import com.entity.adapters.modifiers.ModifierRotation;
import com.entity.adapters.modifiers.anim.SeqAnim;
import com.entity.anot.components.model.OnlyPosition;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.effects.EffectParticle;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.ExtractorHierroDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoHierroDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/buildings/extractor_hierro.j3o")
public class ExtractorHierroNode extends BuildNode {
	@SubModelComponent(name = "motor")
    public Node motor;
	
	@OnlyPosition
	@SubModelComponent(name = "wp1", dettach=true)
    public Vector3f wp1;
	
	@OnlyPosition
	@SubModelComponent(name = "wp2", dettach=true)
    public Vector3f wp2;
	
	@SubModelComponent(name = "hierro")
    public Node hierro;
	
    @EffectParticle(asset = "Models/fx/hojas.j3o")
    public EffectParticleAdapter<ExtractorHierroNode> polvo;
	
    
	@OnlyPosition
	@SubModelComponent(name = "humoMotor", dettach=true)
    public Vector3f humoMotorPos;
	
	@EffectParticle(asset = "Models/fx/hojas.j3o")
    public EffectParticleAdapter<ExtractorHierroNode> humoMotor;


	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(1,1,1));
	}

	@Override
	public Class<? extends EdificioDAO> getDAO() {
		return ExtractorHierroDAO.class;
	}

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
       
        motor.addControl(new ModifierRotation(new Vector3f(0,0,0), new Vector3f(0,FastMath.TWO_PI,0), 10000, true, null));
        
        humoMotor.attach(this, humoMotorPos, true);
        
        
        hierro.addControl(new SeqAnim(true, 
        			new ModifierPosition<Node>(wp1, wp2, 2000),
        			new ModifierPosition<Node>(wp2, wp2.add(0, -25, 0), 1000, new IModifierOnFinish<ModifierPosition<Node>, Node>() {
						public void onFinish(ModifierPosition<Node> m, Node model) {
							polvo.attach(ExtractorHierroNode.this, model.getLocalTranslation().clone());
						}
					}),
        			new ModifierDelay<Spatial>(2000)
        		));
    }

	@Override
	public boolean puedeConstruirseAqui(EstaticoModel edificioColision) {
		return edificioColision!=null && edificioColision.getDAO() instanceof RecursoHierroDAO;
	}


}
