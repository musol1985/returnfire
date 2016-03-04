/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.adapters.listeners.IScrollCameraListener;
import com.entity.anot.Entity;
import com.entity.anot.ScrollCameraNode;
import com.entity.anot.Sky;
import com.entity.anot.components.lights.AmbientLightComponent;
import com.entity.anot.components.lights.DirectionalLightComponent;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.InGameClientScene;
import com.entity.network.core.listeners.InGameClientMessageListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.service.ClientMundoService;

/**
 *
 * @author Edu
 */
public class InGame extends InGameClientScene<InGameClientMessageListener, MundoModel, JugadorModel,  ClientMundoService> {
    @Entity
    private MundoModel world;
    
    @Entity(attach = false)
    private JugadorModel player;
    
    @Sky(texture = "Textures/sky.jpg")
    private Spatial sky;
    
    @WorldService
    private ClientMundoService service;
    
    @MessageListener
    private InGameClientMessageListener listener;
    
            
    @ScrollCameraNode(speed = 100, debug = true)
    private ScrollCameraAdapter camera;

    
    @Override
    public MundoModel getWorld() {
        return world;
    }

    @Override
    public JugadorModel getPlayer() {
        return player;
    }

    @Override
    public ClientMundoService getService() {
        return service;
    }

    @Override
    public InGameClientMessageListener getListener() {
        return listener;
    }

    @Override
    public void onLoadScene() throws Exception {
        super.onLoadScene(); //To change body of generated methods, choose Tools | Templates.
        
        


        getApp().getFlyByCamera().setMoveSpeed(30);
        
        camera.setListener(new IScrollCameraListener() {
            @Override
            public void onUpdate(ScrollCameraAdapter adapter) {
                getService().updatePlayerLocation(camera.getWorldTranslation());
            }
        });
   
        camera.setLocalTranslation(getService().getPlayerDAO().getPosition());
    }



}
