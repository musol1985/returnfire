package com.returnfire.dao.elementos.buildings;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.ContenedorDAO;

@Serializable
public class ConstruyendoDAO extends EdificioDAO{
	public String tipoEdificio;
	public Vector2 size;

	public int piezas;
	public int petroleo;
    
    public ConstruyendoDAO(){
        
    }
    
    public EdificioDAO getEdificio(){
        try{
            Class<? extends EdificioDAO> cls=(Class<? extends EdificioDAO>)Class.forName(tipoEdificio);
            return (EdificioDAO) cls.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
	
	public ConstruyendoDAO(JugadorDAO j, String tipoEdificio, Vector3f pos){
		super(j);		
		this.tipoEdificio=tipoEdificio;
		
                EdificioDAO dao=getEdificio();
                size=dao.getSize();
                petroleo=dao.getPetroleoNecesario();
                piezas=dao.getPiezasNecesarias();
                this.pos=pos;
	}
        
        public EdificioDAO getEdificioConstruido(){
            return super.getFromConstruyendoDAO(this);
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

    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

    public void setPetroleo(int petroleo) {
        this.petroleo = petroleo;
    }
        
        

        @Override
        public boolean puedeCogerMas(ContenedorDAO.RECURSO r){
            if(r==ContenedorDAO.RECURSO.PETROLEO){
                return getPetroleoNecesario()>0;
            }else{
                return getPiezasNecesarias()>0;
            }
        }

    @Override
    public boolean addRecurso(ContenedorDAO.RECURSO r) {
        if(puedeCogerMas(r)){
            if(r==ContenedorDAO.RECURSO.PETROLEO){
                petroleo--;
            }else{
                piezas--;
            }
            return true;
        }
        return false;
    }
        
    public boolean isConstruido(){
        return petroleo==0 && piezas==0;
    }    
}
