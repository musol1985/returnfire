package com.returnfire.dao.elementos.buildings;

import java.util.ArrayList;
import java.util.List;

import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.JugadorDAO;

@Serializable
public abstract class EdificioExtensibleDAO extends EdificioDAO{
    private List<ExtensionDAO> extensiones;
    
    public EdificioExtensibleDAO(){
        
    }
	
	public EdificioExtensibleDAO(JugadorDAO j){
		super(j);
        extensiones=new ArrayList<ExtensionDAO>();
	}

	public List<ExtensionDAO> getExtensiones() {
		return extensiones;
	}

	public void setExtensiones(List<ExtensionDAO> extensiones) {
		this.extensiones = extensiones;
	}
	
	public void addExtension(ExtensionDAO extension){
		extensiones.add(extension);
	}	
	
	public ExtensionDAO getExtensionByZona(String zona){
		for(ExtensionDAO ext:extensiones){
			if(ext.getZona().equals(zona))
				return ext;
		}
		return null;
	}
}
