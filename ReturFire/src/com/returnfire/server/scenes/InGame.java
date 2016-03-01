/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

import com.entity.anot.Entity;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.InGameServerScene;
import com.entity.network.core.listeners.InGameServerMessageListener;
import com.returnfire.models.MundoModel;
import com.returnfire.service.ServerMundoService;

/**
 *
 * @author Edu
 */
public class InGame extends InGameServerScene<InGameServerMessageListener, MundoModel, ServerMundoService> {

    @Entity(attach=false)
    private MundoModel world;
    
    @WorldService
    private ServerMundoService service;
    
    @MessageListener
    private InGameServerMessageListener listener;
    
    @Override
    public MundoModel getWorld() {
        return world;
    }

    @Override
    public ServerMundoService getService() {
        return service;
    }

    @Override
    public InGameServerMessageListener getListener() {
        return listener;
    }
}
