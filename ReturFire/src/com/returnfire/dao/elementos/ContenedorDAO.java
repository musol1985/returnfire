package com.returnfire.dao.elementos;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public abstract class ContenedorDAO extends ElementoIdDAO{
	public abstract RECURSOS getTipo();
	
	public static <T extends ContenedorDAO> T getNew(Class<T> contenedor){
		return getNew(contenedor, new Vector3f());
	}
	
	public static <T extends ContenedorDAO> T getNew(Class<T> contenedor, Vector3f pos){
		try{
			T c=contenedor.newInstance();
			c.setNewID();
			c.pos=pos;
			return c;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("Error getNew nuevo recurso para el contenedor: "+contenedor);
		}
	}	
	
}
