/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.batchs;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;
import com.returnfire.models.elementos.ArbolModel;

/**
 *
 * @author Edu
 */
public class ModelFactory extends BaseService{
    @Instance(attachTo = "")
    public ArbolModel crearArbol(ArbolModel arbol, ArbolDAO dao){
        arbol.move(dao.getPos());
        return arbol;
    }  
}
