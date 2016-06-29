package com.returnfire.dao.elementos.buildings;

import java.util.ArrayList;
import java.util.List;

import com.entity.utils.Vector2;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

@Serializable
public class ConstruyendoDAO extends EdificioAlmacenDAO{
	public String tipoEdificio;
	public Vector2 size;
	public transient EdificioDAO edificio;
    
    public ConstruyendoDAO(){
        
    }
    
    public EdificioDAO getEdificio(){
    	if(edificio==null){
	        try{
	            Class<? extends EdificioDAO> cls=(Class<? extends EdificioDAO>)Class.forName(tipoEdificio);
	            edificio= (EdificioDAO) cls.newInstance();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
    	}
        return edificio;
    }
	
	public ConstruyendoDAO(JugadorDAO j, String tipoEdificio, Vector3f pos){
		super(j);		
		this.tipoEdificio=tipoEdificio;
		
        EdificioDAO dao=getEdificio();
        size=dao.getSize();       
        this.pos=pos;
	}
	
	@Override
	public List<RecursoDAO> getRecursosNecesarios() {
		return getEdificio().getRecursosNecesarios();
	}    
        
    public EdificioDAO getNewEdificioConstruido(){
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
        
/*
    @Override
    public boolean puedeCogerMas(RecursoDAO r){
    	return getNecesarioByRecurso(r)>0;        
    }

    @Override
    public boolean addRecurso(RecursoDAO r) {
        if(puedeCogerMas(r)){
            r.setCantidad(r.getCantidad()-1);
        	
            return true;
        }
        return false;
    }*/
        
    public boolean isConstruido(){
    	for(RecursoDAO r: recursos){
    		int necesarios=getCantidadMaximaQuePuedeAlmacenar(r.tipo);
    		if(necesarios>0 && necesarios>r.cantidad)
    			return false;
    	}
    	return true;
    }


	@Override
	public boolean puedeAlmacenarMas(RECURSOS tipo) {
		int cantidadTiene=getCantidadRecursoByTipo(tipo);
		int cantidadNecesaria=getCantidadMaximaQuePuedeAlmacenar(tipo);
		
		return cantidadTiene<cantidadNecesaria;
	}

	/*@Override
	public List<RecursoDAO> getCantidadesQuePuedeAlmacenar() {
		List<RecursoDAO> res=new ArrayList<RecursoDAO>();
		
		List<RecursoDAO> necesarios=getEdificio().getRecursosNecesarios();
		for(RecursoDAO rNecesario:necesarios){
			int rTiene=getCantidadRecursoByTipo(rNecesario.tipo);
			int cantidadDisponible=rNecesario.cantidad-rTiene;
			
			if(cantidadDisponible>0)
				res.add(new RecursoDAO(rNecesario.tipo, cantidadDisponible));
		}
		
		return res;
	}*/

	@Override
	public int getCantidadQuePuedeAlmacenar(RECURSOS tipo) {
		int necesario=getCantidadMaximaQuePuedeAlmacenar(tipo);
		int rTiene=getCantidadRecursoByTipo(tipo);
		return necesario-rTiene;
	}

	@Override
	public int getCantidadMaximaQuePuedeAlmacenar(RECURSOS tipo) {
		return getEdificio().getNecesarioByTipo(tipo);
	}

	@Override
	public String getNombre() {
		return getEdificio().getNombre();
	}

	@Override
	public String getICO() {
		return getEdificio().getICO();
	}
}
