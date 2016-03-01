/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.anot.Entity;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.InGameClientScene;
import com.entity.network.core.listeners.InGameClientMessageListener;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.service.ClientMundoService;

/**
 *
 * @author Edu
 */
public class InGame extends InGameClientScene<InGameClientMessageListener, MundoModel, JugadorModel,  ClientMundoService> {
    @Entity(attach=false)
    private MundoModel world;
    
    @Entity(attach = false)
    private JugadorModel player;
    
    @WorldService
    private ClientMundoService service;
    
    @MessageListener
    private InGameClientMessageListener listener;
    
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
                DirectionalLight l=new DirectionalLight();
        l.setDirection( new Vector3f(0.5f,-0.5f,0).normalizeLocal());
        
        getApp().getRootNode().addLight(l);
        
        AmbientLight a=new AmbientLight();
        a.setColor(ColorRGBA.White.mult(1));
        getApp().getRootNode().addLight(a);
        
        
               Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(getApp().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        
        getNode().attachChild(geom);
        getApp().getFlyByCamera().setMoveSpeed(30);
    }



}
