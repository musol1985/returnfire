package com.returnfire.dao.elementos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public class VehiculoDAO implements java.io.Serializable{
	public enum VEHICULOS{NINGUNO, HAMMER, HELICOPTERO}
	
	protected VEHICULOS tipo;
	protected String id;
	protected int vida;
	protected int combustible;
	protected int municionA;
	protected int municionB;
	
    protected Vector3f pos;
    protected float ang;
    
	public VEHICULOS getTipo() {
		return tipo;
	}
	public String getId() {
		return id;
	}
	public int getVida() {
		return vida;
	}
	public int getCombustible() {
		return combustible;
	}
	public int getMunicionA() {
		return municionA;
	}
	public int getMunicionB() {
		return municionB;
	}
	public Vector3f getPos() {
		return pos;
	}
	public float getAng() {
		return ang;
	}
    
    
	public static VehiculoDAO getHammer(Vector3f posicion, float ang){
		VehiculoDAO v=new VehiculoDAO();
		v.pos=posicion.clone();
		v.ang=ang;
		
		v.combustible=100;
		v.municionA=300;
		v.municionB=5;
		v.tipo=VEHICULOS.HAMMER;
		v.vida=1000;
		
		return v;
	}
	
	public static VehiculoDAO getVacio(){
		VehiculoDAO v=new VehiculoDAO();
		v.tipo=VEHICULOS.NINGUNO;
		return v;
	}
}
