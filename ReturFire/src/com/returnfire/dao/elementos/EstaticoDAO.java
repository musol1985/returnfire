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
import com.returnfire.dao.elementos.environment.RecursoNaturalDAO;
import com.returnfire.dao.elementos.environment.impl.ArbolDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;

/**
 *
 * @author Edu
 */
@Serializable
public abstract class EstaticoDAO extends ElementoDAO{
	public enum ELEMENTOS_ESTATICOS{ARBOL,ROCA, RECURSO_PETROLEO, RECURSO_HIERRO, EDIFICIO, CONSTRUYENDO}
	
	public static final int INDESTRUCTIBLE=-9999;
	
    protected float ang;
    protected int vida;

    public EstaticoDAO() {
        vida=getVidaInicial();
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
    	return getTipo().name()+"#"+getPos();
    }
    
    
    public ELEMENTOS_ESTATICOS getTipo(){
    	if(this instanceof ArbolDAO){
    		return ELEMENTOS_ESTATICOS.ARBOL;
        }else if(this instanceof EdificioDAO){
            return ELEMENTOS_ESTATICOS.EDIFICIO;
    	}else if(this instanceof ConstruyendoDAO){
    		return ELEMENTOS_ESTATICOS.CONSTRUYENDO;
    	}else if(this instanceof RecursoNaturalDAO){
    		return ELEMENTOS_ESTATICOS.RECURSO_PETROLEO;
    	}else if(this instanceof RecursoPetroleoDAO){
    		return ELEMENTOS_ESTATICOS.RECURSO_PETROLEO;
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
