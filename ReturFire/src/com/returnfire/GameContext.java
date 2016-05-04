package com.returnfire;

import com.entity.core.EntityManager;
import com.entity.network.core.items.IWorldInGameScene;
import com.entity.network.core.service.NetWorldService;
import com.returnfire.client.scenes.InGame;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.service.ClientMundoService;
import com.returnfire.service.ServerMundoService;

public class GameContext {
	public static boolean isServer(){
		return EntityManager.getGame().getNet().isNetServerGame();
	}
	
	public static ClientMundoService getClientService(){
		return (ClientMundoService)((IWorldInGameScene)EntityManager.getCurrentScene()).getService();
	}
	
	public static ServerMundoService getServerService(){
		return (ServerMundoService)((IWorldInGameScene)EntityManager.getCurrentScene()).getService();
	}
	
    
    public static MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
    /**
     * Devuelve el jugador o null si es en el server
     * @return
     */
    public static JugadorModel getJugador(){
    	if(!isServer())
    		return getClientService().getPlayer();
        return null;
    }
    
    
    public static boolean isMe(String playerId){
        return (!isServer() && getJugador().dao.getId().equals(playerId));            
    }
    
    public static NetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO> getService(){
    	return ((IWorldInGameScene)EntityManager.getCurrentScene()).getService();
    }
}
