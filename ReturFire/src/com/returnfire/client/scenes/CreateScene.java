/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.network.core.bean.NetPlayer;
import com.entity.network.core.bean.NetWorld;
import com.entity.network.core.items.WorldsScene;
import com.entity.network.core.listeners.WorldsMessageListener;
import com.returnfire.Client;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edu
 */
public class CreateScene extends  WorldsScene<WorldsMessageListener, NetWorld, NetPlayer, Client>{

    @Override
    public void showLobby() {
        try {
            getApp().showScene(getApp().lobby);
                    } catch (Exception ex) {
            Logger.getLogger(CreateScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
