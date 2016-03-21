/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.anot.Entity;
import com.entity.anot.ScrollCameraNode;
import com.entity.anot.network.ActivateNetSync;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.InGameServerScene;
import com.returnfire.Server;
import com.returnfire.models.MundoModel;
import com.returnfire.server.listeners.InGameServerListener;
import com.returnfire.service.ServerMundoService;

/**
 *
 * @author Edu
 */
@ActivateNetSync
public class InGame extends InGameServerScene<InGameServerListener, MundoModel, ServerMundoService, Server> {

    @Entity
    private MundoModel world;
    
    @WorldService
    private ServerMundoService service;
    
    @MessageListener
    private InGameServerListener listener;
    
    
                
    @ScrollCameraNode(debug = false, speed = 100)
    private ScrollCameraAdapter camera;
    
    @Override
    public MundoModel getWorld() {
        return world;
    }

    @Override
    public ServerMundoService getService() {
        return service;
    }

    @Override
    public InGameServerListener getListener() {
        return listener;
    }

    @Override
    public void showLobby() {
        getApp().showScene(getApp().lobby);
    }
    
    
    @Override
    public void onLoadRemotePlayers() throws Exception{
            world.cargarJugadores();
    }
}
