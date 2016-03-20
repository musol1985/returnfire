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

        EntityManager.getGame().getNet().setIp("localhost");
        EntityManager.getGame().getNet().setPort(4260);
        
        if(isCreateGame()){
        	getApp().setPath(getApp().getPath()+"/owner");
        	getApp().showScene(getApp().create);
        }else{
        	getApp().setPath(getApp().getPath()+"/joined");
        	getApp().showScene(getApp().lobby);
        }
    }

  
    public boolean isCreateGame(){
        return true;
        //return showDialog("Create game?");	    
    }
    
    private boolean showDialog(String msg){
	return JOptionPane.showConfirmDialog (null, msg,"Warning",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
    }
    
}
