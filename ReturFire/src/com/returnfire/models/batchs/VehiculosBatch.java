/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.batchs;

import com.entity.anot.entities.BatchModelEntity;
import com.entity.core.items.BatchModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;

/**
 *
 * @author Edu
 */
@BatchModelEntity(name="vehiculos",autoBatch = false)
public class VehiculosBatch extends BatchModel{
	public VehiculoModel getVehiculo(String id){
		return (VehiculoModel)getChild(id);
	}
	
	public VehiculoModel getVehiculo(long id){
		return getVehiculo(id+"#");
	}
}
