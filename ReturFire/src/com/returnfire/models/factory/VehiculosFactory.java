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

/**
 *
 * @author Edu
 */
public class VehiculosFactory extends BaseService{

    
    @Instance(attachTo = "")
    public VehiculoModel crearHammer(VehiculoModel hammer, JugadorModel jugador){
        return hammer;
    } 
    

}
