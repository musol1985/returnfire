package com.returnfire.models.elementos.environment;

import com.entity.anot.entities.ModelEntity;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.IEstaticoNode;

@ModelEntity(asset = "Models/environment/rock_brown_02.j3o")
public class BrownRock2 extends EstaticoModel<RockDAO, BrownRock2> implements IEstaticoNode{
    
    
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,1,1));
    }
    
    @Override
    public boolean onEliminar(Vector3f vel) {
        return true;
    }
    

	@Override
	public Vector2 getSize() {
		return new Vector2(1,1);
	}

	@Override
	public BrownRock2 getNodo() {
		return this;
	}
}
