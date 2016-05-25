/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos.environment;

import java.util.Random;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
/**
 *
 * @author Edu
 */
@Serializable
public abstract class RecursoNaturalDAO extends EstaticoDAO {
    public abstract RECURSOS getTipoRecurso();
    
    public static <T extends RecursoNaturalDAO> T getNew(Class<T> tipo, Vector3f posicion, float ang) {
    	T res=null;
    	try{
    		res=tipo.newInstance();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        res.setPos(posicion.clone());
        res.setAng(ang);
        return res;
    }

	@Override
	public int getVidaInicial() {
		return INDESTRUCTIBLE;
	}

	
}
