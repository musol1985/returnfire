/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos.environment.impl;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;

/**
 *
 * @author Edu
 */
@Serializable
public class ArbolDAO extends EstaticoDAO {
	public static final String TEMPLATE="arbol";
    
    public static ArbolDAO getNew(Vector3f posicion, float ang) {
        ArbolDAO arbol=new ArbolDAO();
        arbol.setPos(posicion.clone());
        arbol.setAng(ang);
        return arbol;
    }

	@Override
	public int getVidaInicial() {
		return 100;
	}

	@Override
	public Vector2 getSize() {
		return new Vector2(1,1);
	}

}
