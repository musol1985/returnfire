package com.returnfire.service;

import java.util.Random;

import com.entity.network.core.service.NetWorldService;
import com.entity.utils.Utils;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.bean.CeldaDAO;
import com.returnfire.bean.JugadorDAO;
import com.returnfire.bean.MundoDAO;

public class MundoService extends NetWorldService<MundoDAO, JugadorDAO, CeldaDAO>{

	public MundoService(MundoDAO world) {
		super(world);
	}



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
	public JugadorDAO createNewPlayer(String name) {
            JugadorDAO jugador=new JugadorDAO();
            jugador.setId(name);
            return jugador;
	}



	@Override
	public void preload() {
		world.setSeed(System.currentTimeMillis());
		
		Random rnd=new Random(world.getSeed());
		
		int offset=50;
		
		float x=Utils.getRandomBetween(rnd, offset , world.getMaxRealSize()-offset);
		float z=Utils.getRandomBetween(rnd, offset , world.getMaxRealSize()-offset);
		Vector3f posInicial=new Vector3f(x, 0f, z);
		//We put the players near
		for(JugadorDAO j:world.getPlayers().values()){
			j.setPosition(posInicial.clone());
			posInicial.addLocal(10,0,10);
		}
	}

	
}
