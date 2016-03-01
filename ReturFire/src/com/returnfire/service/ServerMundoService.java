package com.returnfire.service;

import java.util.Random;

import com.entity.core.EntityManager;
import com.entity.network.core.beans.CellId;
import com.entity.network.core.service.impl.ServerNetWorldService;
import com.entity.utils.Utils;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;

public class ServerMundoService extends ServerNetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO>{
	
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
	public void preload() {
		world.getDao().setSeed(System.currentTimeMillis());
		
		Random rnd=new Random(world.getDao().getSeed());
		
		int offset=50;
		
		float x=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()-offset);
		float z=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()-offset);
		Vector3f posInicial=new Vector3f(x, 0f, z);
		//We put the players near
		for(JugadorDAO j:world.getDao().getPlayers().values()){
			j.setPosition(posInicial.clone());
			posInicial.addLocal(10,0,10);
		}
	}

	@Override
	public void onNewPlayerDAO(JugadorDAO player) {
		player.setPosition(new Vector3f());
	}

	@Override
	public CeldaDAO onNewCellDAO(Vector2 cellId) {
		CeldaDAO celda=new CeldaDAO();
		celda.setId(new CellId(cellId, System.currentTimeMillis()));
		
		//Aplicar la logica de colocar arboles, terreno, etc.
		
		return celda;
	}




	
}
