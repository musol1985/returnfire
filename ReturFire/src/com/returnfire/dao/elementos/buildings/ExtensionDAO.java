package com.returnfire.dao.elementos.buildings;

import com.jme3.network.serializing.Serializable;

@Serializable
public class ExtensionDAO implements java.io.Serializable{
	public enum EXTENSIONES{PETROLEO, PIEZAS};
	
	private String zona;
	private int level;
	private int valor;
	private EXTENSIONES tipo;
	
	public ExtensionDAO(){
		
	}
        
        public ExtensionDAO(String zona, EXTENSIONES tipo){
         this(zona, tipo, 0,0);   
        }
	
    public ExtensionDAO(String zona, EXTENSIONES tipo, int valor, int level){
    	this.zona=zona;
    	this.tipo=tipo;
        this.valor=valor;
        this.level=level;
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public EXTENSIONES getTipo() {
		return tipo;
	}

	public void setTipo(EXTENSIONES tipo) {
		this.tipo = tipo;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}


        

}
