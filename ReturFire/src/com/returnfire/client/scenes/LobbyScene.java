/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.network.core.bean.NetPlayer;
import com.entity.network.core.bean.NetWorld;
import com.entity.network.core.items.LobbyClientScene;
import com.entity.network.core.listeners.LobbyClientMessageListener;

/**
 *
 * @author Edu
 */
public class LobbyScene extends LobbyClientScene<LobbyClientMessageListener, NetWorld, NetPlayer>{

    @Override
    public void onPlayerReady(NetPlayer player) {
        
    }
    
}
