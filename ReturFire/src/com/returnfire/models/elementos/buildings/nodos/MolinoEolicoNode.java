package com.returnfire.models.elementos.buildings.nodos;

import com.entity.adapters.Modifier;
import com.entity.adapters.listeners.IModifierOnFinish;
import com.entity.adapters.modifiers.ModifierPosition;
import com.entity.adapters.modifiers.ModifierRotation;
import com.entity.anot.components.model.SubModelComponent;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.GameContext;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;

@ModelEntity(asset = "Models/buildings/eolic.j3o")
public class MolinoEolicoNode extends BuildNode {
    @SubModelComponent(name = "helix")
    public Node helix;

	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}

	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(11,1,11));
	}

	@Override
	public EdificioDAO getNewDAO(JugadorDAO jugador) {
		return new MolinoEolicoDAO(jugador);
	}

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
        helix.addControl(new ModifierRotation(new Vector3f(0,0,0), new Vector3f(0,0,FastMath.TWO_PI), 10000, true, null));
 
    }

	
}
