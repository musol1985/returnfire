package com.returnfire.client.listeners;

import com.entity.network.core.listeners.InGameClientMessageListener;
import com.returnfire.client.scenes.InGame;
import com.returnfire.msg.MsgOnDisparar;
import com.returnfire.msg.sync.Posicion;


public class InGameClientListener extends InGameClientMessageListener<InGame> {

	public void onDisparar(MsgOnDisparar msg)throws Exception{
		getEntity().getService().onDisparar(msg);
	}
}
