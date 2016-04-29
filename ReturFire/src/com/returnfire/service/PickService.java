/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.service;

import com.entity.anot.RayPick;
import com.entity.core.items.BaseService;
import com.jme3.math.Ray;
import com.returnfire.models.elementos.EdificioModel;

/**
 *
 * @author Edu
 */
public class PickService extends BaseService{
	
	public void pickEdificio(){
		pickEdificio(null, null);
	}

	@RayPick
    public void pickEdificio(Ray r, EdificioModel edificio){
    	System.out.println("----------->PICK "+edificio);
    }
}
