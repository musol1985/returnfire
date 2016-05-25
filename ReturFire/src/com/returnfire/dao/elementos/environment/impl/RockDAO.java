/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos.environment.impl;

import java.util.Random;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;
/**
 *
 * @author Edu
 */
@Serializable
public class RockDAO extends EstaticoDAO {
    public enum ROCK_TYPE{BROWN_1,BROWN_2,BROWN_3,GREY_1, GREY_2, GREY_3};
    
    protected ROCK_TYPE subTipo;
    
    public static RockDAO getNew(Vector3f posicion, float ang, Random rnd) {
        return getNew(posicion, ang,  ROCK_TYPE.values()[rnd.nextInt(ROCK_TYPE.values().length)]);
    }
    
    public static RockDAO getNew(Vector3f posicion, float ang, ROCK_TYPE tipo) {
        RockDAO rock=new RockDAO();
        rock.setPos(posicion.clone());
        rock.ang=ang;
        return rock;
    }

    public ROCK_TYPE getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(ROCK_TYPE subTipo) {
        this.subTipo = subTipo;
    }

	@Override
	public int getVidaInicial() {
		return INDESTRUCTIBLE;
	}

	@Override
	public Vector2 getSize() {
		return new Vector2(1,1);
	}
}
