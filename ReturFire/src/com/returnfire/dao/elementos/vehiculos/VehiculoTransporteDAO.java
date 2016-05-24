package com.returnfire.dao.elementos.vehiculos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.VehiculoDAO;

@Serializable
public abstract class VehiculoTransporteDAO extends VehiculoDAO{
	protected HashMap<RECURSOS, List<ContenedorDAO>> contenedores;
	public abstract int getMaxSlots();
	
	@Override
	public void onNew() {
		contenedores=new HashMap<RECURSOS, List<ContenedorDAO>>();
	}
	
	/**
	 * Retorna true si se ha anyadido, false sino
	 * @param contenedor
	 * @return
	 */
	public boolean addContenedor(ContenedorDAO contenedor){
		if(getContenedoresSize()<getMaxSlots()){
			List<ContenedorDAO> contenedores=getContenedoresByTipoRecurso(contenedor.getTipo());
			if(contenedores==null){
				contenedores=new ArrayList<ContenedorDAO>();
				this.contenedores.put(contenedor.getTipo(), contenedores);
			}
			contenedores.add(contenedor);
            return true;
		}
		return false;
	}
	
	public int getContenedoresSize(){
		int size=0;
		for(Entry<RECURSOS, List<ContenedorDAO>> c:contenedores.entrySet()){
			size+=c.getValue().size();
		}
		
		return size;
	}

	public HashMap<RECURSOS, List<ContenedorDAO>> getContenedores() {
		return contenedores;
	}
	
	/**
	 * @param recurso
	 * @return
	 */
	public int getCantidadContenedorByTipoRecurso(RECURSOS recurso){
		List<ContenedorDAO> contenedores=getContenedoresByTipoRecurso(recurso);
		if(contenedores!=null)
			return contenedores.size();
		return 0;
	}
	
        /**
         * 
         */
	public List<ContenedorDAO> getContenedoresByTipoRecurso(RECURSOS recurso){
		List<ContenedorDAO> res=contenedores.get(recurso);
		if(res==null){			
                    res=new ArrayList<ContenedorDAO>();
                    contenedores.put(recurso, res);
                }
                return res;
	}
}
