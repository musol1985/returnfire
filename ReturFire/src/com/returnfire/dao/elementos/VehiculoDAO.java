package com.returnfire.dao.elementos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.vehiculos.impl.VacioDAO;

@Serializable
public abstract class VehiculoDAO extends ElementoIdDAO{
	protected int vida;
	protected int combustible;
	

    protected float ang;


	public int getVida() {
		return vida;
	}
	public int getCombustible() {
		return combustible;
	}

	public Vector3f getPos() {
		return pos;
	}
	public float getAng() {
		return ang;
	}
	
	public abstract void onNew();
	public abstract int getMaxVida();
	
	public static <T extends VehiculoDAO> T getNew(Class<T> vehiculo, Vector3f posicion, float ang){
		try{
			T v=vehiculo.newInstance();
			v.setNewID();
			v.pos=posicion;
			v.ang=ang;
			
			v.onNew();
			return v;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("No se puede instanciar el DAO "+vehiculo);
		}
	}
	
	public static VacioDAO getVacio(){
		VacioDAO v= new VacioDAO();
		v.setNewID();
		v.pos=new Vector3f();
		return v;
	}
	
	public boolean isVacio(){
		return this instanceof VacioDAO;
	}
	
	public boolean equalsTipo(VehiculoDAO other){
		return getClass().equals(other.getClass());
	}
}
