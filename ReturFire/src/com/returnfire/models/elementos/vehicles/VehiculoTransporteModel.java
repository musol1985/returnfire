/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.vehicles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.entity.anot.OnCollision;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.msg.MsgDisparar;

/**
 *
 * @author Edu
 */
public abstract class VehiculoTransporteModel<T extends PhysicsRigidBody> extends VehiculoModel<T>{	
    
    private List<ConstruyendoModel> construcciones=new ArrayList<ConstruyendoModel>();
    
    public abstract void addContenedor(ContenedorModel c)throws Exception;
    
    @OnCollision
    public void onColisionContenedor(ContenedorModel contenedor)throws Exception{
    	addContenedor(contenedor);
    }
    
    @OnCollision
   public void onColisionVehiculo(ConstruyendoModel construccion)throws Exception{
       if(!construcciones.contains(construccion)){
           construcciones.add(construccion);
       }
   }
   
   public void onUpdate(float tpf)throws Exception{
       Iterator<ConstruyendoModel> it=construcciones.iterator();
       while(it.hasNext()){
           if(!it.next().isVehiculoEnZona(this)){
               System.out.println("------------------------>remove!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
               it.remove();
           }
       }
   }
   
   @Override
	public void onAccion() {
		new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
	}
}
