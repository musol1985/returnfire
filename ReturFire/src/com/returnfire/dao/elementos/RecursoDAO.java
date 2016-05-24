package com.returnfire.dao.elementos;

import com.jme3.network.serializing.Serializable;

@Serializable
public class RecursoDAO implements java.io.Serializable{
	public enum RECURSOS{PETROLEO, PIEZAS};

	public int cantidad;
	public RECURSOS tipo;

        public RecursoDAO() {
            
        }
	
	public RecursoDAO( RECURSOS tipo, int cantidad) {
		this.cantidad = cantidad;
		this.tipo = tipo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public RECURSOS getTipo() {
		return tipo;
	}
	public void setTipo(RECURSOS tipo) {
		this.tipo = tipo;
	}
	
	
}
