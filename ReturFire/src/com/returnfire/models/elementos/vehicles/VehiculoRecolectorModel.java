/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.vehicles;

import com.entity.anot.OnCollision;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.environment.RecursoNaturalDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoRecolectorDAO;
import com.returnfire.models.elementos.environment.RecursoNaturalModel;
import com.returnfire.msg.MsgVehiculoRecolectaRecurso;

/**
 *
 * @author Edu
 */
public abstract class VehiculoRecolectorModel<T extends PhysicsRigidBody, D extends VehiculoRecolectorDAO> extends VehiculoTransporteModel<T, D>{	
	private long tRecoleccion=0l;
	private RecursoNaturalModel<RecursoNaturalDAO, GhostControl> recursoNatural;
	
    @OnCollision(includeSubClass=true)
    public void onColisionConRecursoNatural(RecursoNaturalModel recursoNatural)throws Exception{
     	if(this.recursoNatural!=recursoNatural){
     		this.recursoNatural=recursoNatural;
     	}
    }
	
	@Override
	public void onAccion(boolean valor) {
		if(valor){
			onIniRecolectar();
		}else{
			onFinRecolectar();
		}
	}
	
	public boolean tieneSitio(){
        return dao.getContenedoresSize()<dao.getMaxSlots();
    }
	
	public void onIniRecolectar(){
		if(recursoNatural!=null && recursoNatural.isVehiculoEncima(this) && dao.puedeRecolectar(recursoNatural.getDAO().getTipoRecurso())){
			if(tieneSitio()){
				tRecoleccion=System.currentTimeMillis();
			}else{
				//TODO mostrar icono
			}
		}
	}
	
	public void onFinRecolectar(){
		tRecoleccion=0;
		recursoNatural=null;
	}
	
	public void onRecolectar(){
            new MsgVehiculoRecolectaRecurso(recursoNatural.getCelda().dao.getId(), dao.getIdLong(), recursoNatural.getDAO().getTipoRecurso()).send();
            onIniRecolectar();
	}
	
	  public void onUpdate(float tpf)throws Exception{
		   if(this.recursoNatural!=null && tRecoleccion>0l){                       
			   if(recursoNatural.isVehiculoEncima(this)){
				   if(System.currentTimeMillis()-tRecoleccion>dao.getVelocidadRecoleccion(recursoNatural.getDAO().getTipoRecurso())*1000){
					   onRecolectar();
				   }				   
			   }else{
				   onFinRecolectar();
			   }
		   }      
	   }
        
    @Override
    public boolean puedeMoverse(){
        System.out.println(tRecoleccion);
        return tRecoleccion==0l;
    }
}
