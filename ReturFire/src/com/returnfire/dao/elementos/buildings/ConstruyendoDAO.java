package com.returnfire.dao.elementos.buildings;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;

@Serializable
public class ConstruyendoDAO extends EdificioDAO{
	public String tipoEdificio;
	public Vector2 size;
	public int maxPiezas;
	public int maxPetroleo;
	public int piezas;
	public int petroleo;
    
    public ConstruyendoDAO(){
        
    }
	
	public ConstruyendoDAO(JugadorDAO j, String tipoEdificio, Vector3f pos){
		super(j);		
		this.tipoEdificio=tipoEdificio;
		
		try{
			Class<? extends EdificioDAO> cls=(Class<? extends EdificioDAO>)Class.forName(tipoEdificio);
			EdificioDAO dao=(EdificioDAO) cls.newInstance();
			size=dao.getSize();
			maxPetroleo=dao.getPetroleoNecesario();
			maxPiezas=dao.getPiezasNecesarias();
                        this.pos=pos;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public int getVidaInicial() {
		return 100;
	}


	public String getTipoEdificio() {
		return tipoEdificio;
	}

	@Override
	public Vector2 getSize() {		
		return size;
	}

	@Override
	public int getPetroleoNecesario() {
		return petroleo;
	}

	@Override
	public int getPiezasNecesarias() {
		return piezas;
	}
	
	

}
