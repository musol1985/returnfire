package com.returnfire.server.listeners;

import com.entity.anot.network.Broadcast;
import com.entity.network.core.listeners.InGameServerMessageListener;
import com.entity.network.core.models.NetPlayer;
import com.returnfire.msg.sync.Posicion;

public class InGameServerListener extends InGameServerMessageListener {

	public void onPosicion(Posicion msg, String modelId) throws Exception{
		NetPlayer player=(NetPlayer)getEntity().getService().getWorld().players.get(modelId);
		player.dao.setPosition(msg.pos);
	}
}
