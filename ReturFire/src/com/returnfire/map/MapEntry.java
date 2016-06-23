package com.returnfire.map;

import com.returnfire.models.elementos.EstaticoModel;

public class MapEntry {
        public static final int MAP_ENTRY_SIZE=4;
        
	protected EstaticoModel elemento;
	
	protected MapEntry vNorte;
	protected MapEntry vSur;
	protected MapEntry vEste;
	protected MapEntry vOeste;
	
	public void setOcupadoPor(EstaticoModel elemento){
		this.elemento=elemento;
	}
	
	public boolean isOcupado(){
		return elemento!=null;
	}
	
	public EstaticoModel getElemento(){
		return elemento;
	}

	public MapEntry getNorte() {
		return vNorte;
	}

	public void setNorte(MapEntry vNorte) {
		this.vNorte = vNorte;
	}

	public MapEntry getSur() {
		return vSur;
	}

	public void setSur(MapEntry vSur) {
		this.vSur = vSur;
		vSur.setNorte(this);
	}

	public MapEntry getEste() {
		return vEste;
	}

	public void setEste(MapEntry vEste) {
		this.vEste = vEste;
		vEste.setOeste(this);
	}

	public MapEntry getOeste() {
		return vOeste;
	}

	public void setOeste(MapEntry vOeste) {
		this.vOeste = vOeste;
	}
	
	
}
