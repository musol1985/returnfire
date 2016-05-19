/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.vehiculos.impl.HammerDAO;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.elementos.vehicles.impl.HammerModel;
import com.returnfire.models.elementos.vehicles.impl.HammerModelRemote;

/**
 *
 * @author Edu
 */
public class VehiculosFactory extends BaseService{

	public VehiculoModel crearVehiculo(JugadorModel jugador, VehiculoDAO dao){
		if(dao instanceof HammerDAO){
			return crearHammer(jugador, dao);
		}
		return null;
	}
 
    public VehiculoModel crearHammer(JugadorModel jugador, VehiculoDAO dao){
    	if(jugador!=null){
	        if(!jugador.isRemote()){
	        	return crearHammerLocal(null, jugador, dao);
	        }else{
	        	return crearHammerRemoto(null, jugador, dao);
	        }
    	}else{
    		return crearHammerParking(null, dao);
    	}
    } 
    
    @Instance(attachTo = "")
    public HammerModel crearHammerLocal(HammerModel hammer,JugadorModel jugador, VehiculoDAO dao ){
        return hammer;
    } 
    
    @Instance(attachTo = "")
    public HammerModelRemote crearHammerRemoto(HammerModelRemote hammer,JugadorModel jugador, VehiculoDAO dao){
        return hammer;
    } 
    
    @Instance(attachTo = "")
    public HammerModelRemote crearHammerParking(HammerModelRemote hammer, VehiculoDAO dao){
        return hammer;
    } 
}
