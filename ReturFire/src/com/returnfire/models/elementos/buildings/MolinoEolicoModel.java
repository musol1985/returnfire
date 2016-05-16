/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings;

import com.entity.anot.Entity;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.buildings.nodos.MolinoEolicoNode;

/**
 *
 * @author Edu
 */

@ModelEntity
public class MolinoEolicoModel extends EdificioModel<MolinoEolicoDAO, MolinoEolicoNode>{
	
    @Entity
    public MolinoEolicoNode building;
	
	
    @Override
    public CollisionShape getColisionShape() {
            // TODO Auto-generated method stub
            return new BoxCollisionShape(new Vector3f(11,0.3f,11));
    }

    @Override
    public boolean onEliminar(Vector3f vel) {
            // TODO Auto-generated method stub
            return false;
    }

    @Override
    public MolinoEolicoNode getNodo() {
        return building;
    }



}
