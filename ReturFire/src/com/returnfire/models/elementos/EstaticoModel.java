/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.bullets.BulletModel;

/**
 *
 * @author Edu
 */
public abstract class EstaticoModel<T extends EstaticoDAO, P extends PhysicsCollisionObject> extends ElementoModel<T, EstaticosBatch, P>{
    
     
     /**
      * Retorna true si destruye el elemento
      * @param bala
      * @return
      */
     public boolean onImpacto(BulletModel bala)throws Exception{
		 getCelda().dao.getId().update();
		 if(dao.addDanyo(bala.getDanyo())){
                        eliminar(bala.getBody().getLinearVelocity());
			 return true;
		 }
    	 
    	 return false;
     }
     
     public boolean isDestructible(){
    	 return dao.isDestructible();
     }
     
     
     public void eliminar(Vector3f vel){
         CeldaModel celda=getCelda();
         if(GameContext.isServer() || onEliminar(vel))
            getParentModel().eliminar(this);
    	 
    	 celda.dao.getEstaticos().remove(dao);
    	 celda.save();
     }

}
