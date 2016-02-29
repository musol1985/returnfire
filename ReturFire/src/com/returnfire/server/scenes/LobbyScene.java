/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

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


	@Override
	public void onPlayerJoined(JugadorDAO player) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void onStarGame() {
        getApp().showScene(getApp().inGame);
    }
    
}
