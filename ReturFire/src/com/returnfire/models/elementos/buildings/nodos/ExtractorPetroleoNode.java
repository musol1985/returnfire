package com.returnfire.models.elementos.buildings.nodos;

import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.ExtractorPetroleoDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/buildings/extractorPetroleo.j3o")
public class ExtractorPetroleoNode extends BuildNode {

	@Override
	public CollisionShape getCollisionShape() {
		return new BoxCollisionShape(new Vector3f(11,1,11));
	}

	@Override
	public Class<? extends EdificioDAO> getDAO() {
		return ExtractorPetroleoDAO.class;
	}

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
       
    }

	@Override
	public boolean puedeConstruirseAqui(EstaticoModel edificioColision) {
		return edificioColision.getDAO() instanceof RecursoPetroleoDAO;
	}

	
}
