/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.network.core.dao.NetPlayerDAO;
import com.entity.network.core.dao.NetWorldDAO;
import com.entity.network.core.items.LobbyClientScene;
import com.entity.network.core.listeners.LobbyClientMessageListener;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyClientScene<LobbyClientMessageListener, NetWorldDAO, NetPlayerDAO>{


    
    
    @Override
    public void onPlayerReady(NetPlayerDAO player) {
        if(!player.isAdmin() && "Player2".equals(player)){
        	startGame();
        }
    }
    
}
