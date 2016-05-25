package com.returnfire.models.elementos.buildings.nodos;

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
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/buildings/eolic.j3o")
public class MolinoEolicoNode extends BuildNode {
    @SubModelComponent(name = "helix")
    public Node helix;


	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(11,1,11));
	}

	@Override
	public Class<? extends EdificioDAO> getDAO() {
		return MolinoEolicoDAO.class;
	}

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
        helix.addControl(new ModifierRotation(new Vector3f(0,0,0), new Vector3f(0,0,FastMath.TWO_PI), 10000, true, null));
 
    }

	@Override
	public boolean puedeConstruirseAqui(EstaticoModel edificioColision) {
		return false;
	}

	
}
