package com.returnfire.models.elementos.environment;

import com.entity.adapters.ParticleCache;
import com.entity.anot.Entity;
import com.entity.anot.components.model.ParticleComponent;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.elementos.EstaticoModel;

@ModelEntity(asset = "Models/palm_03.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO>{
 
    @ParticleComponent(asset = "Models/fx/debris.j3o")
    public ParticleCache debris;
   
    @Entity(name="coco")
    public CocoModel coco;
    
    @Override
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(0.3f,8,0.3f));
    }

    @Override
    public void onEliminar() {
        //getCelda().getMundo().addParticle(hojas, getWorldTranslation().add(0, 5, 0));        
    }
    
         @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params);
        
        coco.move(0.5f, 9, 0.5f);
        coco.rotate(-0.9f, 0, -0.9f);
             //GameContext.getMundo().addParticle(hojas, getWorldTranslation().add(0, 5, 0));        
    }

    @Override
    public boolean onImpacto(BulletModel bala) throws Exception {
        getCelda().getMundo().addParticle(debris, getWorldTranslation().add(0, 5, 0));    
        //debris.emitAll();
        return super.onImpacto(bala); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     
    public void tirarCoco(BulletModel bala)throws Exception{
        if(dao.getVida()-bala.getDanyo()<50 && coco!=null){
            coco.caer();
            coco=null;
        }
    }
}
