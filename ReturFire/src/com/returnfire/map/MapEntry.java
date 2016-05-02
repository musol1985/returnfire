package com.returnfire.map;

public class MapEntry {
	private ILugar elemento;
	
	public void setOcupadoPor(ILugar elemento){
		this.elemento=elemento;
	}
	
	public boolean isOcupado(){
		return elemento!=null;
	}
	
	public ILugar getElemento(){
		return elemento;
	}
}
