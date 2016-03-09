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
import com.returnfire.server.scenes.InGame;
import com.returnfire.server.scenes.LobbyScene;
import com.returnfire.service.ServerMundoService;


/**
 *
 * @author Edu
 */
@Network(messagesPackage={"com.returnfire.dao","com.returnfire.dao.elementos","com.returnfire.dao.elementos.estaticos"}
        , gameName="ReturnFire", version=1,
worldService = ServerMundoService.class)
public class Server  extends EntityGame{
    
    public static void main(String[] args)throws Exception{
        EntityManager.startGame(Server.class, true, true);
    }
    
    @SceneEntity(preLoad=false, singleton=false, first = true)
    public LobbyScene lobby;
    @SceneEntity(preLoad=false, singleton=false, first = false)
    public InGame inGame;


    @Override
	public String getPersistPath() {
		return "persist/server";
	}
}
