/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire;

import com.entity.anot.Physics;
import com.entity.anot.entities.SceneEntity;
import com.entity.anot.network.Network;
import com.entity.core.EntityGame;
import com.entity.core.EntityManager;
import com.returnfire.client.scenes.CreateScene;
import com.returnfire.client.scenes.InGame;
import com.returnfire.client.scenes.LobbyScene;
import com.returnfire.client.scenes.MainScene;
import com.returnfire.service.ClientMundoService;



/**
 *
 * @author Edu
 */
@Network(messagesPackage={"com.returnfire.dao","com.returnfire.dao.elementos","com.returnfire.dao.elementos.estaticos"},
        gameName="ReturnFire", version=1, worldService = ClientMundoService.class)
@Physics(debug = false)
public class Client  extends EntityGame{
    
    public static void main(String[] args)throws Exception{
        EntityManager.startGame(Client.class);                
    }
    
    @SceneEntity(preLoad=false, singleton=false, first = true)
    public MainScene main;

	@SceneEntity(preLoad=false, singleton=false, first = false)
    public CreateScene create;

    @SceneEntity(preLoad=false, singleton=false, first = false)
    public LobbyScene lobby;
       
    @SceneEntity(preLoad=false, singleton=false, first = false)
    public InGame inGame;
    

  
    
    @Override
	public String getPersistPath() {
		return "persist/client";
	}
}
