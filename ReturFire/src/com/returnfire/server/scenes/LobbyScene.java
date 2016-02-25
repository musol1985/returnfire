/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

import com.entity.core.items.Scene;
import com.entity.network.core.dao.NetPlayerDAO;
import com.entity.network.core.dao.NetWorldDAO;
import com.entity.network.core.items.LobbyServerScene;
import com.entity.network.core.listeners.LobbyServerMessageListener;
import com.returnfire.Server;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyServerScene<LobbyServerMessageListener, NetWorldDAO, NetPlayerDAO>{



    @Override
    public void onPlayerJoined(NetPlayerDAO player) {
       
    }
    
}
