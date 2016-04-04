package com.returnfire.models.elementos.environment;

import com.entity.adapters.ParticleCache;
import com.entity.anot.Entity;
import com.entity.anot.components.model.ParticleComponent;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/palm_03.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO>{
 
    @ParticleComponent(asset = "Models/fx/hojas.j3o")
    public ParticleCache hojas;
   
    @Entity(name="coco")
    public CocoModel coco;
    
    @Override
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(1,5,1));
    }

    @Override
    public void onEliminar() {
        getCelda().getMundo().addParticle(hojas, getWorldTranslation().add(0, 5, 0));        
    }
    
         @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params);
        
             //GameContext.getMundo().addParticle(hojas, getWorldTranslation().add(0, 5, 0));        
    }
         
     public boolean onImpacto(BulletModel bala)throws Exception{
    	 boolean res=super.onImpacto(bala);
    	 if(dao.getVida()<50 && coco!=null){
    		 coco.caer();
    		 coco=null;
    	 }
    	 
    	 return res;
     }
}
