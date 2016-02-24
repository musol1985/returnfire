/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire;

import com.entity.anot.entities.SceneEntity;
import com.entity.anot.network.Network;
import com.entity.core.EntityGame;
import com.entity.core.EntityManager;
import com.returnfire.client.scenes.CreateScene;
import com.returnfire.client.scenes.LobbyScene;
import com.returnfire.client.scenes.MainScene;



/**
 *
 * @author Edu
 */
@Network(messagesPackage={"com.entity.network.core.msg","com.entity.network.core.bean"}, gameName="ReturnFire", version=1)
public class Client  extends EntityGame{
    
    public static void main(String[] args)throws Exception{
        EntityManager.startGame(Client.class, true);                
    }
    
    @SceneEntity(preLoad=false, singleton=false, first = true)
    public MainScene main;

  
    
    @Override
	public String getPersistPath() {
		return "/persist/client";
	}

	@SceneEntity(preLoad=false, singleton=false, first = false)
    public CreateScene create;

    @SceneEntity(preLoad=false, singleton=false, first = false)
    public LobbyScene lobby;
       

}
