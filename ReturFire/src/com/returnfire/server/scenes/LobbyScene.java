/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.server.scenes;

import com.entity.core.items.Scene;
import com.entity.network.core.bean.NetPlayer;
import com.entity.network.core.bean.NetWorld;
import com.entity.network.core.items.LobbyServerScene;
import com.entity.network.core.listeners.LobbyServerMessageListener;
import com.returnfire.Server;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyServerScene<LobbyServerMessageListener, NetWorld, NetPlayer>{



    @Override
    public void onPlayerJoined(NetPlayer player) {
       
    }
    
}
