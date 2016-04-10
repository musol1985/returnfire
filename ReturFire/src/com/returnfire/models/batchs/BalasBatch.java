/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.batchs;

import java.util.HashMap;

import com.entity.anot.entities.BatchModelEntity;
import com.entity.core.items.BatchModel;
import com.returnfire.models.elementos.BulletModel;

/**
 *
 * @author Edu
 */
@BatchModelEntity(name="balas",autoBatch = true)
public class BalasBatch extends BatchModel{
	private HashMap<String, BulletModel> balas=new HashMap<String, BulletModel>();
	
	public void nueva(BulletModel bala){ 
		attachEntity(bala);
		balas.put(bala.idBala, bala);
	}
	
	public BulletModel eliminar(String balaId){
		BulletModel bala=balas.remove(balaId);
		if(bala!=null)
			dettachEntity(bala);
                return bala;
	}
	
	public void eliminar(BulletModel bala){
		eliminar(bala.idBala);
	}
}
