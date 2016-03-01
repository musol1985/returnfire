/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.LobbyServerScene;
import com.entity.network.core.listeners.LobbyServerMessageListener;
import com.returnfire.Server;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.service.ServerMundoService;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyServerScene<LobbyServerMessageListener, ServerMundoService, MundoDAO, JugadorDAO, Server>{

    @MessageListener
    private LobbyServerMessageListener listener;
    
    @WorldService
    private ServerMundoService service;

    @Override
    public void onPlayerJoined(JugadorDAO player) {

    }

    @Override
    public void onStarGame() {
        getApp().showScene(getApp().inGame);
    }

    @Override
    public LobbyServerMessageListener getListener() {
        return listener;
    }

    @Override
    public ServerMundoService getService() {
        return service;
    }
    
}
