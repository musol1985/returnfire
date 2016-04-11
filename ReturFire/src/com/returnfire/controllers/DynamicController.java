/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.controllers;

import com.entity.adapters.ControlAdapter;
import com.entity.adapters.Modifier;
import com.entity.adapters.listeners.IModifierOnFinish;
import com.entity.adapters.modifiers.ModifierPosition;
import com.entity.core.EntityManager;
import com.entity.core.items.Model;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.scene.Spatial;
import com.returnfire.GameContext;
import com.returnfire.models.elementos.environment.CocoModel;

/**
 *
 * @author Edu
 */
public class DynamicController extends ControlAdapter{
    private RigidBodyControl body;
    private Model m;
    
    public DynamicController(RigidBodyControl body, Model m){
        this.body=body;
        this.m=m;
    }
    
    @Override
    public void update(float tpf) {
            if(!body.isActive()){
                EntityManager.getGame().getPhysics().remove(body);
                m.removeControl(this);
                m.addControl(new ModifierPosition(m.getLocalTranslation(), m.getLocalTranslation().add(0,-5,0), 3000, false, new IModifierOnFinish() {
                    @Override
                    public void onFinish(Modifier mod, Spatial model) {
                        GameContext.getMundo().getDinamicos().dettachEntity(m);
                    }
                }));
            }
    }
}
