package com.returnfire.service;

import com.entity.anot.OnUpdate;
import com.entity.anot.RunOnGLThread;
import com.entity.network.core.service.impl.ClientNetWorldService;
import com.jme3.math.Vector3f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.buildings.BuildModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.models.elementos.bullets.BulletModel;
import com.returnfire.msg.MsgBuild;
import com.returnfire.msg.MsgOnBalaEstatico;
import com.returnfire.msg.MsgOnBuilding;
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
		BulletModel bala=getWorld().getFactory().balasFactory.crearBala(getWorld(), onDisparar.from, onDisparar.tipo, onDisparar);
		//Solo attach si esta en rango de vision
		if(bala!=null){
			if(bala.getWorldTranslation().distance(player.getVehiculo().getWorldTranslation())<100f){
				getWorld().addBala(bala);
			}
		}
	}
	
    @RunOnGLThread
    public void onImpactoBala(String bala)throws Exception{
    	getWorld().getBalas().eliminar(bala);
    }
    
    @RunOnGLThread
    public void onImpactoBalaEstatico(MsgOnBalaEstatico msg)throws Exception{
    	getWorld().getBalas().eliminar(msg.idBala);
        
        CeldaModel cell=getCellById(msg.cellId.id);
        if(cell!=null){
            if(msg.vida>0){
                cell.getEstatico(msg.idEstatico).getDAO().setVida(msg.vida);
            }else{
                //cell.eliminarEstatico(msg.idEstatico);
                cell.getEstatico(msg.idEstatico).eliminar(msg.v);
            }
            //Si el timestamp actual es igual al de antes de eliminar el estatico en el server
            //quiere decir que estamos sincronizados, eliminamos y actualizamos el timestamp.
            if(cell.dao.getId().timestamp==msg.celdaTimestampOld){
            	cell.dao.getId().timestamp=msg.cellId.timestamp;
            	cell.save();
            }
        }
    }
    
    public void construirEdificio(BuildModel model)throws Exception{    	
    	new MsgBuild(model.getEdificio().getDAO(), model.getEdificio().getClass(), model.getWorldTranslation(), 0, getPlayer().dao.getId()).send();
    }
    
    public void errorConstruir(String edificio)throws Exception{
    	log.warning("Error al constuir un edificio: "+edificio);
    	//Volvemos a hacer drag de ese tipo de edificio ya que seguramente en el server se haya puesto otro edificio en esa misma pos
    	getWorld().buildDrag.setEdificio((Class<? extends BuildNode>) Class.forName(edificio));
    }
    
    @RunOnGLThread
    public void onConstruyendoEdificio(MsgOnBuilding msg)throws Exception{
    	CeldaModel celda=getCellById(msg.cellId.id);
    	celda.addConstruyendoEdificio(msg.edificio, true, true);
    }
    
    

}
