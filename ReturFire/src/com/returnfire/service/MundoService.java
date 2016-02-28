package com.returnfire.service;

import java.util.Random;

import com.entity.network.core.service.NetWorldService;
import com.entity.utils.Utils;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;

public class MundoService extends NetWorldService<MundoModel, JugadorModel, CeldaDAO, MundoDAO, JugadorDAO>{
	

	@Override
	public boolean isCellInLimits(Vector2 celldId) {
		return super.isCellInLimits(celldId);
	}


	/**
	 * Solo se llama en el server. El cliente NO puede crear una nueva celda
	 */
	@Override	
	public CeldaDAO createNewCell(Vector2 cellId) {
		return null;
	}



	@Override
	public JugadorDAO createNewPlayerDAO(String name) {
            JugadorDAO jugador=new JugadorDAO();
            jugador.setId(name);
            jugador.setPosition(new Vector3f());
            return jugador;
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
    public MundoModel createTempNetWorld() {
        return new MundoModel();
    }


	
}
