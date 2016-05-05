package com.returnfire.models.elementos.buildings.nodos;

import com.entity.core.items.Model;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.IEstaticoNode;




public abstract class BuildNode extends Model implements IEstaticoNode{
	public abstract CollisionShape getCollisionShape(); 
	public abstract EdificioDAO getNewDAO(JugadorDAO jugador);
}
