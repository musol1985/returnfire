/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.environment.ArbolDAO;

/**
 *
 * @author Edu
 */
@Serializable
public abstract class EstaticoDAO<T extends EstaticoDAO>{
	public enum ELEMENTOS_ESTATICOS{ARBOL,ROCA}
	
	public static final int INDESTRUCTIBLE=-1;
	
    protected Vector3f pos;
    protected float ang;
    protected int vida;

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public float getAng() {
        return ang;
    }

    public void setAng(float ang) {
        this.ang = ang;
    }
    

    public abstract int getVidaInicial();
    
    public String getId(){
    	return getTipo().name()+"#"+pos;
    }
    
    
    public ELEMENTOS_ESTATICOS getTipo(){
    	if(this instanceof ArbolDAO){
    		return ELEMENTOS_ESTATICOS.ARBOL;
    	}else{
    		return ELEMENTOS_ESTATICOS.ROCA;
    	}
    }
}
