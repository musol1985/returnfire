/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Service;
import com.entity.core.items.BaseService;

/**
 *
 * @author Edu
 */
public class Factory extends BaseService{
	
	@Service
	public BalasFactory balasFactory;
	@Service
	public ModelFactory modelFactory;
	@Service
	public VehiculosFactory vehiculosFactory;
    
}
