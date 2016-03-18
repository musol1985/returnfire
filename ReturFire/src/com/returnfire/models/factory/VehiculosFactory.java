/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.elementos.vehicles.HammerModel;
import com.returnfire.models.elementos.vehicles.HammerModelRemote;

/**
 *
 * @author Edu
 */
public class VehiculosFactory extends BaseService{

 
    public VehiculoModel crearHammer(JugadorModel jugador){
        if(!jugador.isRemote()){
        	return crearHammerLocal(null, jugador);
        }else{
        	return crearHammerRemoto(null, jugador);
        }
    } 
    
    @Instance(attachTo = "")
    public HammerModel crearHammerLocal(HammerModel hammer,JugadorModel jugador ){
        return hammer;
    } 
    
    @Instance(attachTo = "")
    public HammerModelRemote crearHammerRemoto(HammerModelRemote hammer,JugadorModel jugador){
        return hammer;
    } 
}
