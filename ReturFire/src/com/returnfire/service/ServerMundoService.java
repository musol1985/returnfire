package com.returnfire.service;

import java.util.Random;

import com.entity.anot.RunOnGLThread;
import com.entity.network.core.beans.CellId;
import com.entity.network.core.service.impl.ServerNetWorldService;
import com.entity.utils.Utils;
import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.elementos.BulletModel.BALAS;
import com.returnfire.msg.MsgOnDisparar;

public class ServerMundoService extends ServerNetWorldService<MundoModel, JugadorModel, CeldaModel, MundoDAO, JugadorDAO, CeldaDAO>{
        private Random rnd;
        
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
	public void preload() throws Exception{
		world.getDao().setSeed(System.currentTimeMillis());
		
		rnd=new Random(world.getDao().getSeed());
		
		int offset=10;
		
		float x=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()*MundoModel.CELL_SIZE-offset);
		float z=Utils.getRandomBetween(rnd, offset , world.getDao().getMaxRealSize()*MundoModel.CELL_SIZE-offset);
                
                x=600;
                z=600;
		Vector3f posInicial=new Vector3f(x, 0f, z);
		//We put the players near
		for(JugadorDAO j:world.getDao().getPlayers().values()){
			j.setPosition(posInicial.clone());
			VehiculoDAO vehiculoInicial=VehiculoDAO.getHammer(posInicial.add(0, 30, 0), 0);
                                                
			EdificioVehiculosDAO base=new EdificioVehiculosDAO(j, vehiculoInicial, EdificioDAO.EDIFICIOS.BASE_PEQUE);
            
                        Vector2 pos=getCellPosByReal(posInicial);

                        CeldaModel celda=createNewCell(pos, null, false);
                        
                        Vector3f cellLocalPosition=celda.worldToLocal(posInicial, null);
                        base.setPos(cellLocalPosition);

                        celda.addEdificio(base, true, false);
			j.setVehiculo(vehiculoInicial);
			posInicial.addLocal(20,0,20);
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
                if(rnd==null)
                    rnd=new Random(world.getDao().getSeed());
                
                celda.generar(rnd, getWorld());
		
		return celda;
	}


    @Override
    public Class<JugadorModel> getPlayerClass() {
        return JugadorModel.class;
    }


	@Override
	public int getCellCacheSize() {
		return 50;
	}
	
	@RunOnGLThread
	public void disparar(String from, BALAS tipo)throws Exception{
		//Crea la bala y la attach
		BulletModel bala=getWorld().getBalasFactory().crearBala(getWorld(), from, tipo);
		if(bala!=null){
			MsgOnDisparar msg=new MsgOnDisparar();
			msg.id=bala.idBala;
			msg.from=from;
			msg.tipo=tipo;
			msg.position=bala.getWorldTranslation();
			msg.rotation=bala.getWorldRotation().toAngles(new float[3]);
			
			msg.send();
		}
	}        
}
