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
import com.returnfire.server.scenes.LobbyScene;


/**
 *
 * @author Edu
 */
@Network(messagesPackage={"com.returnfire.bean"}, gameName="ReturnFire", version=1)
public class Server  extends EntityGame{
    
    public static void main(String[] args)throws Exception{
        EntityManager.startGame(Server.class, true);
    }
    
    @SceneEntity(preLoad=false, singleton=false, first = true)
    public LobbyScene lobby;


    @Override
	public String getPersistPath() {
		return "persist/server";
	}
}
