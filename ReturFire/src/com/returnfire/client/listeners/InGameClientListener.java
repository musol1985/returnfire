package com.returnfire.client.listeners;

import com.entity.network.core.listeners.InGameClientMessageListener;
import com.returnfire.client.scenes.InGame;
import com.returnfire.msg.MsgOnBalaEstatico;
import com.returnfire.msg.MsgOnBuilt;
import com.returnfire.msg.MsgOnDisparar;


public class InGameClientListener extends InGameClientMessageListener<InGame> {

	public void onDisparar(MsgOnDisparar msg)throws Exception{
		getEntity().getService().onDisparar(msg);
	}
	
	public void onImpactoBala(MsgOnBalaEstatico msg)throws Exception{
		getEntity().getService().onImpactoBalaEstatico(msg);
	}
	
	public void onEdificioConstruido(MsgOnBuilt msg)throws Exception{
		
	}
}
