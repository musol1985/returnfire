package com.returnfire.models.elementos.buildings.nodos;

import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.impl.ExtractorHierroDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoHierroDAO;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/buildings/extractor_hierro.j3o")
public class ExtractorHierroNode extends BuildNode {

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
       
    }

	@Override
	public boolean puedeConstruirseAqui(EstaticoModel edificioColision) {
		return edificioColision!=null && edificioColision.getDAO() instanceof RecursoHierroDAO;
	}

	
}
