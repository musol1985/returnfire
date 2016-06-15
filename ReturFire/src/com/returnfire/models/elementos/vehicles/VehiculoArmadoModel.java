/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.vehicles;

import com.entity.anot.components.model.SubModelComponent;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.scene.Node;
import com.returnfire.dao.elementos.vehiculos.VehiculoArmadoDAO;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.msg.MsgDisparar;

/**
 *
 * @author Edu
 */
public abstract class VehiculoArmadoModel<T extends PhysicsRigidBody> extends VehiculoModel<T, VehiculoArmadoDAO>{	
    
	@SubModelComponent(name="gun")
	protected Node arma;
    @SubModelComponent(name="gunEmitter")
	protected Node armaEmitter;

	
	public Node getArma(){
		return arma;
	}
        
    public Node getArmaEmitter(){
        return armaEmitter;
    }


	@Override
	public void onAccion(boolean valor) {
		new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
	}

}
