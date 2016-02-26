/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.entity.network.core.items.WorldsScene;
import com.entity.network.core.listeners.WorldsMessageListener;
import com.returnfire.Client;
import com.returnfire.dao.MundoDAO;
import com.returnfire.service.MundoService;

/**
 *
 * @author Edu
 */
public class CreateScene extends  WorldsScene<WorldsMessageListener, MundoService, MundoDAO, Client>{

    @Override
    public void showLobby() {
        try {
            getApp().showScene(getApp().lobby);
                    } catch (Exception ex) {
            Logger.getLogger(CreateScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public MundoDAO createWorld() {
        return new MundoDAO("MundoPruebas");                
    }
    
}
