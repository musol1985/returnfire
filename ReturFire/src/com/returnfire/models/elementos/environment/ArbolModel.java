package com.returnfire.models.elementos.environment;

import com.entity.adapters.EffectParticleAdapter;
import com.entity.anot.Entity;
import com.entity.anot.effects.EffectParticle;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.controllers.DynamicController;
import com.returnfire.dao.elementos.environment.impl.ArbolDAO;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.bullets.BulletModel;

@ModelEntity(asset = "Models/environment/palm_03.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO, RigidBodyControl>{
 
    @EffectParticle(asset = "Models/fx/hojas.j3o")
    public EffectParticleAdapter hojas;
   
    @Entity(name="coco")
    public CocoModel coco;
    
    @Override
    public CollisionShape getColisionShape(){
        return new BoxCollisionShape(new Vector3f(0.3f,8,0.3f));
        // return new CylinderCollisionShape(new Vector3f(0.3f,8,0.3f), 1);
    }
    

    @Override
    public boolean onEliminar(Vector3f vel) {   
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        CapsuleCollisionShape box = new CapsuleCollisionShape(0.5f, 8, 1);//new Vector3f(0.3f,5,0.3f), 1);
        compoundShape.addChildShape(box, new Vector3f(0, 0, 0));
        
        removeControl(body);
        EntityManager.getGame().getPhysics().remove(body);
        body=new RigidBodyControl(compoundShape, 50f);
        //body.setCollisionShape(compoundShape);

        body.setLinearVelocity(vel.normalize().add(0, 0.5f, 0));
        addControl(body);
        EntityManager.getGame().getPhysics().add(body);
        
        addControl(new DynamicController(body, this));
        
        //body.setCollisionShape(new HullCollisionShape(((Geometry)getChild("geoArbol")).getMesh()));
        return false;
    }
    
         @Override
    public void onInstance(IBuilder builder, Object[] params)  throws Exception{
        super.onInstance(builder, params);
        
        coco.move(0.5f, 4, 0.5f);
        coco.rotate(-0.9f, 0, -0.9f);
             //GameContext.getMundo().addParticle(hojas, getWorldTranslation().add(0, 5, 0));        
    }
    
    
     
    public void onImpactoLocal(BulletModel bala)throws Exception{   
    	hojas.attach(GameContext.getMundo(), getWorldTranslation().add(0, 5, 0));

        if(dao.getVida()-bala.getDanyo()<50 && coco!=null){
            coco.caer();
            coco=null;
        }
    }
    
        
    @Override
    public Class getBodyType(){
        return RigidBodyControl.class;
    }
}
