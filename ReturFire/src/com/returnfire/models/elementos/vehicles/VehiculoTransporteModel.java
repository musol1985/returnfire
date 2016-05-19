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
import com.entity.anot.components.model.SubModelComponent;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.scene.Node;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;

/**
 *
 * @author Edu
 */
public abstract class VehiculoTransporteModel<T extends PhysicsRigidBody> extends VehiculoModel<T, VehiculoTransporteDAO>{	
    
    private List<ConstruyendoModel> construcciones=new ArrayList<ConstruyendoModel>();
    
	@SubModelComponent(name="contenedoresReference")
	public Node contenedoresReference;
	
    /**
     * Metodo que se encarga de colocar el contenedor en el model
     * Solo sera llamado desde el server!!
     * @param c
     * @throws Exception
     */
    public abstract void colocarContenedor(ContenedorModel c, int size)throws Exception;
    
    @OnCollision
    public void onColisionContenedor(ContenedorModel<ContenedorDAO> contenedor)throws Exception{
    	/*int size=dao.getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
    		contenedor.dettach();
    		contenedor.attachToParent(this);
    		colocarContenedor(contenedor, size);
    	}*/
    	contenedor.dettach();
    	new MsgOnVehiculoCogeContenedor(contenedor.getCelda().dao.getId(), dao.getIdLong(), contenedor.getDAO().getIdLong()).send();
    }
    
    public void cogeContenedor(ContenedorModel<ContenedorDAO> contenedor)throws Exception{
    	int size=getDao().getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
    		contenedor.dettach();
    		contenedor.attachToParent(this);
    		colocarContenedor(contenedor, size);
    	}else{
    		throw new Exception("No se ha podido añadir el contenedor "+contenedor.getDAO().getId());
    	}
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
