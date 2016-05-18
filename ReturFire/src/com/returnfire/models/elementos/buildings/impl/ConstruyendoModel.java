/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings.impl;

import com.entity.anot.OnCollision;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.VehiculoModel;

/**
 *
 * @author Edu
 */

@ModelEntity
public class ConstruyendoModel extends EdificioModel<ConstruyendoDAO>{
	
    @Override
    public CollisionShape getColisionShape() {
            // TODO Auto-generated method stub
            return new BoxCollisionShape(new Vector3f(dao.getSize().x,0.3f,dao.getSize().z));
    }

    @Override
    public boolean onEliminar(Vector3f vel) {
            // TODO Auto-generated method stub
            return false;
    }



    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); 
        
        
        //TODO add suelo y add de las vallas
    }
    
    public CollisionShape getZona(){
    	return new SphereCollisionShape(dao.getSize().x*2);
    }
    
    @OnCollision
    public void onColisionVehiculo(VehiculoModel vehiculo)throws Exception{
    	
    }
}
