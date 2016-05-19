/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.contenedores;

import com.entity.core.IBuilder;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.ElementoModel;

/**
 *
 * @author Edu
 */
public abstract class ContenedorModel<T extends ContenedorDAO> extends ElementoModel<T, EstaticosBatch>{

    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        body.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);
        body.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
    }
    


}
