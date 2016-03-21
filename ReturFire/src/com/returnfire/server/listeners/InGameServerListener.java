package com.returnfire.server.listeners;

import com.entity.network.core.listeners.InGameServerMessageListener;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.sync.Posicion;
import com.returnfire.server.scenes.InGame;

public class InGameServerListener extends InGameServerMessageListener<InGame> {

	public void onPosicion(Posicion msg, String modelId) throws Exception{
		/*NetPlayer player=(NetPlayer)getEntity().getService().getWorld().players.get(modelId);
		player.dao.setPosition(msg.pos);*/
	}
	
	

	public void onDisparar(MsgDisparar msg)throws Exception{
		getEntity().getService().disparar(msg.from, msg.tipo);
	}
}
