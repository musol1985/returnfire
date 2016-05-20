package com.returnfire.client.listeners;

import com.entity.network.core.listeners.InGameClientMessageListener;
import com.jme3.network.HostedConnection;
import com.returnfire.client.scenes.InGame;
import com.returnfire.msg.MsgErrOnBuilt;
import com.returnfire.msg.MsgOnBalaEstatico;
import com.returnfire.msg.MsgOnBuilding;
import com.returnfire.msg.MsgOnDisparar;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;


public class InGameClientListener extends InGameClientMessageListener<InGame> {

	public void onDisparar(MsgOnDisparar msg)throws Exception{
		getEntity().getService().onDisparar(msg);
	}
	
	public void onImpactoBala(MsgOnBalaEstatico msg)throws Exception{
		getEntity().getService().onImpactoBalaEstatico(msg);
	}
	
	public void onConstruyendoEdificio(MsgOnBuilding msg)throws Exception{
		getEntity().getService().onConstruyendoEdificio(msg);
	}
	
	public void onErrEdificioConstruido(MsgErrOnBuilt msg)throws Exception{
		getEntity().getService().errorConstruir(msg.tipoEdificio);
	}
	public void onVehiculoCogeContenedor(MsgOnVehiculoCogeContenedor msg, HostedConnection cnn)throws Exception{
		getEntity().getService().onVehiculoCogeContenedor(msg);			
	}
}
