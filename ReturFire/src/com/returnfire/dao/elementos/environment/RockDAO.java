/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos.environment;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;
import java.util.Random;
/**
 *
 * @author Edu
 */
@Serializable
public class RockDAO extends EstaticoDAO {
    public static final int VIDA_INICIAL=100;
    
    public enum ROCK_TYPE{BROWN_1,BROWN_2,BROWN_3,GREY_1, GREY_2, GREY_3};
    
    protected ROCK_TYPE tipo;
    
    public static RockDAO getNew(Vector3f posicion, float ang, Random rnd) {
        return getNew(posicion, ang,  ROCK_TYPE.values()[rnd.nextInt(ROCK_TYPE.values().length)]);
    }
    
    public static RockDAO getNew(Vector3f posicion, float ang, ROCK_TYPE tipo) {
        RockDAO rock=new RockDAO();
        rock.setVida(VIDA_INICIAL);
        rock.setPos(posicion.clone());
        rock.ang=ang;

        return rock;
    }

    public ROCK_TYPE getTipo() {
        return tipo;
    }

    public void setTipo(ROCK_TYPE tipo) {
        this.tipo = tipo;
    }


}
