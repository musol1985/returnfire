/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.network.core.items.LobbyClientScene;
import com.entity.network.core.listeners.LobbyClientMessageListener;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.service.MundoService;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyClientScene<LobbyClientMessageListener,MundoService, JugadorDAO>{


    
    
    @Override
    public void onPlayerReady(JugadorDAO player) {
        if(!player.isAdmin() && "Player2".equals(player)){
        	startGame();
        }
    }

	@Override
	public void onStartGame() {
		
		
	}
    
}
