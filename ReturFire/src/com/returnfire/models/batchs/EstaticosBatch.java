/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.batchs;

import com.entity.anot.entities.BatchModelEntity;
import com.entity.core.items.BatchModel;
import com.returnfire.models.CeldaModel;

/**
 *
 * @author Edu
 */
@BatchModelEntity(name="estaticos",autoBatch = false)
public class EstaticosBatch extends BatchModel{
	public CeldaModel getCelda(){
		return (CeldaModel)getParent();
	}
}
