/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.dao.elementos;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;

/**
 *
 * @author Edu
 */
@Serializable
public abstract class EstaticoDAO<T extends EstaticoDAO> implements java.io.Serializable{
	public enum ELEMENTOS_ESTATICOS{ARBOL,ROCA, EDIFICIO, CONSTRUYENDO}
	
	public static final int INDESTRUCTIBLE=-9999;
	
    protected Vector3f pos;
    protected float ang;
    protected int vida;

    public EstaticoDAO() {
        vida=getVidaInicial();
    }
    
    

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
        }else if(this instanceof EdificioDAO){
            return ELEMENTOS_ESTATICOS.EDIFICIO;
    	}else if(this instanceof ConstruyendoDAO){
    		return ELEMENTOS_ESTATICOS.CONSTRUYENDO;
    	}else{
    		return ELEMENTOS_ESTATICOS.ROCA;
    	}
    }
    
    public boolean isDestructible(){
    	return vida!=INDESTRUCTIBLE;
    }
    
    /**
     * Retorna true si el elemento ha sido destruido 
     * @param danyo
     * @return
     */
    public boolean addDanyo(int danyo){
        System.out.println("----------------------------------->DANYO "+vida);
    	vida-=danyo;
        System.out.println("----------------------------------->DANYO post"+vida);
    	return isDestruido();
    }
    
    public boolean isDestruido(){
    	return vida<=0;
    }
    
    public abstract Vector2 getSize();
}
