/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.EntityManager;
import com.entity.core.items.BaseService;
import com.jme3.math.Quaternion;
import com.returnfire.GameContext;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.elementos.BulletModel.BALAS;
import com.returnfire.models.elementos.bullets.NormalBulletModel;
import com.returnfire.msg.MsgOnDisparar;

/**
 *
 * @author Edu
 */
public class BalasFactory extends BaseService{
	
	public BulletModel crearBala(MundoModel world, String from, BALAS tipo)throws Exception{
		return crearBala(world, from, tipo, null);
	}
	
	public BulletModel crearBala(MundoModel world, String from, BALAS tipo, MsgOnDisparar msg)throws Exception{
		JugadorModel player=world.getPlayers().get(from);
		if(player==null && GameContext.isMe(from)){
			player=GameContext.getJugador();
		}
                
		if(player!=null){
			if(player.hasVehicle()){
				BulletModel bala=null;
				
				if(tipo==BALAS.NORMAL){
					bala=world.getBalasFactory().crearBalaNormal(null);
				}
				
				if(msg==null){
					bala.setFrom(player.getVehiculo());
					world.addBala(bala);
				}else{
					bala.setFrom(msg.id, msg.position, new Quaternion().fromAngles(msg.rotation), player.getVehiculo());
				}
				
				return bala;
			}else{
				log.severe("El jugador "+from+" no tiene vehiculo para disparar");	
			}
		}else{
			log.severe("No se ha encontrado el jugador "+from+" al disparar");
		}
		return null;
	}
	
	
    @Instance(attachTo = "")
    public NormalBulletModel crearBalaNormal(NormalBulletModel bala){
    	return bala;
    } 
    
}
