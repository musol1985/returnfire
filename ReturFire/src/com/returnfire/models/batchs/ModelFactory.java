/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.batchs;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.jme3.math.Vector2f;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;
import com.returnfire.models.MundoModel;
import com.returnfire.models.elementos.ArbolModel;

/**
 *
 * @author Edu
 */
public class ModelFactory extends BaseService{
    @Instance(attachTo = "")
    public ArbolModel crearArbol(ArbolModel arbol, ArbolDAO dao, CeldaDAO celda){
        Vector2f realPos=celda.getId().id.mult(MundoModel.CELL_SIZE);
        arbol.move(dao.getPos().add(realPos.x,0,realPos.y));
        return arbol;
    }  
}
