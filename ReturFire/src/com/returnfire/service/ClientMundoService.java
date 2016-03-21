package com.returnfire.service;

import com.entity.anot.RunOnGLThread;
import com.entity.network.core.service.impl.ClientNetWorldService;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.msg.MsgOnDisparar;

public class ClientMundoService extends ClientNetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO>{
    @Override
    public Class<CeldaModel> getCellClass() {
        return CeldaModel.class;
    }

	@Override
	public Class<MundoModel> getWorldClass() {
		return MundoModel.class;
	}


	@Override
	public Class<JugadorDAO> getPlayerDAOClass() {
		return JugadorDAO.class;
	}


	@Override
	public void onNewPlayerDAO(JugadorDAO player) {
		player.setPosition(new Vector3f());
	}

    @Override
    public Class<JugadorModel> getPlayerClass() {
        return JugadorModel.class;
    }

	@Override
	public int getCellCacheSize() {
		return 20;
	}

        @RunOnGLThread
	public void onDisparar(MsgOnDisparar onDisparar)throws Exception{
		//Crea la bala, pero a diferencia dle server, no la attacha
		BulletModel bala=getWorld().getBalasFactory().crearBala(getWorld(), onDisparar.from, onDisparar.tipo, onDisparar);
		//Solo attach si est� en rango de vision
		if(bala!=null){
			if(bala.getWorldTranslation().distance(player.getVehiculo().getWorldTranslation())<100f){
				getWorld().addBala(bala);
			}
		}
	}
	
}
