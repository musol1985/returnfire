package com.returnfire.server.listeners;

import com.entity.network.core.listeners.InGameServerMessageListener;
import com.jme3.network.HostedConnection;
import com.returnfire.msg.MsgBuild;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.MsgErrOnBuilt;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;
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

	public void onBuild(MsgBuild msg, HostedConnection cnn)throws Exception{
            MsgErrOnBuilt err=getEntity().getService().build(msg);			
            if(err!=null)
                cnn.send(err);
	}

	public void onVehiculoCogeContenedor(MsgOnVehiculoCogeContenedor msg, HostedConnection cnn)throws Exception{
        MsgErrOnBuilt err=getEntity().getService().vehiculoCogeContenedor(msg);			
        if(err!=null)
            cnn.send(err);
	}
}
