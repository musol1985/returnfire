/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos.estaticos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;

/**
 *
 * @author Edu
 */
@Serializable
public class ArbolDAO extends EstaticoDAO {
    public static final int VIDA_INICIAL=100;
    
    public static ArbolDAO getNew(Vector3f posicion) {
        ArbolDAO arbol=new ArbolDAO();
        arbol.setVida(VIDA_INICIAL);
        arbol.setPos(posicion.clone());
        return arbol;
    }


}
