/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.network.core.items.LobbyClientScene;
import com.entity.network.core.listeners.LobbyClientMessageListener;
import com.returnfire.Client;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.service.ServerMundoService;
import java.util.concurrent.Callable;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyClientScene<LobbyClientMessageListener,ServerMundoService, JugadorDAO, Client>{


    
    
    @Override
    public void onNewPlayer(JugadorDAO player) {
        if(!player.isAdmin() && "Player2".equals(player.getId())){
        	startGame();
        }
    }

	@Override
	public void onStartGame() {
            getApp().enqueue(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    getApp().showScene(getApp().inGame);
                    return true;
                }
            });
	}

    @Override
    public void onLoadScene() throws Exception {
        super.onLoadScene(); //To change body of generated methods, choose Tools | Templates.
        if(Client.isTest1Player())
            startGame();
    }
    
}
