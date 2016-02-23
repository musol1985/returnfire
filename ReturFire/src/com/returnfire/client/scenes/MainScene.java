/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.core.EntityManager;
import com.entity.core.items.Scene;
import com.returnfire.Client;
import javax.swing.JOptionPane;

/**
 *
 * @author Edu
 */
public class MainScene extends Scene<Client>{

    @Override
    public void onLoadScene() throws Exception {
            
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog (null, "Create game?","Warning",dialogButton);
        
        EntityManager.getGame().getNet().setIp("localhost");
        EntityManager.getGame().getNet().setPort(4260);

        if(dialogButton == JOptionPane.YES_OPTION){ 
            getApp().showScene(getApp().create);
        }else{
            getApp().showScene(getApp().lobby);
        }
        
    }

  
    
}
