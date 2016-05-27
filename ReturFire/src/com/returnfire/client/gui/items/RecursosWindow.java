/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import java.util.Map.Entry;

import com.entity.adapters.listeners.ICameraUpdate;
import com.entity.core.EntityManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Spatial;
import com.returnfire.GameContext;
import com.returnfire.client.gui.controls.Window;
import com.returnfire.client.scenes.InGame;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.elementos.buildings.IAlmacenable;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

/**
 *
 * @author Edu
 */
public class RecursosWindow extends Window<RecursosRow> implements ICameraUpdate{
    private IAlmacenable edificio;
    private VehiculoTransporteModel vehiculo;
    
    public static RecursosWindow getNewWindow(Vector3f pos, IAlmacenable edificio, VehiculoTransporteModel v){
        RecursosWindow window=(RecursosWindow)EntityManager.instanceGeneric(RecursosWindow.class);
        window.instance("recursosWindow", pos);        
        window.edificio=edificio;
        window.vehiculo=v;
        ((InGame)EntityManager.getCurrentScene()).addCamUpdate(window);        
        return window;
    }
    
    public void remove(){
        try{
            ((InGame)EntityManager.getCurrentScene()).removeCamUpdate(this);
            dettach();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPositionUpdate(Camera c) {
        Vector3f pos=c.getScreenCoordinates(((Spatial)edificio).getWorldTranslation());            
        setPosition(pos.x, pos.y);
    }
    
    public void addRow(RECURSOS tipo, int contenedoresVehiculo, int cantidadEdificio, int max, boolean vehiculoPuedeAlmacenar, boolean edificioPuedeAlmacenar){
        RecursosRow row=(RecursosRow)EntityManager.instanceGeneric(RecursosRow.class);        
        
        row.instance(rows.size(),tipo, contenedoresVehiculo, cantidadEdificio, max);
        
        row.puedeAlmacenar(vehiculoPuedeAlmacenar, edificioPuedeAlmacenar);
        
        super.addRow(tipo.name(), row);
    }
    
    public void onAddToEdificio(boolean all, RecursosRow row){    	
    	RECURSOS tipoRecurso=row.getTipoRecurso();
    	    	
    	int added=GameContext.getClientService().addRecursoToEdificio(edificio.getAlmacenCelda().getDao(), edificio.getAlmacenDAO(), (VehiculoTransporteDAO)vehiculo.getDao(), tipoRecurso, all);
    	
        if(added>0){
        	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
        	row.setText(vt.getCantidadContenedorByTipoRecurso(tipoRecurso)-added, edificio.getAlmacenDAO().getCantidadRecursoByTipo(tipoRecurso)+added);
        }
    }
    
    public void onAddToVehiculo(boolean all, RecursosRow row){    	
    	RECURSOS tipoRecurso=row.getTipoRecurso();
    	
    	int added=GameContext.getClientService().addRecursoToVehiculo(edificio.getAlmacenCelda().getDao(), edificio.getAlmacenDAO(), (VehiculoTransporteDAO)vehiculo.getDao(), tipoRecurso, all);
    	
    	if(added>0){
        	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
        	row.setText(vt.getCantidadContenedorByTipoRecurso(tipoRecurso)+added, edificio.getAlmacenDAO().getCantidadRecursoByTipo(tipoRecurso)-added);
        }
    }
    
    public void actualizar(){
    	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
    	EdificioAlmacenDAO ea=(EdificioAlmacenDAO)edificio.getAlmacenDAO();
    	
    	for(Entry<String,RecursosRow> row:rows.entrySet()){    
    		RECURSOS tipoRecurso=row.getValue().getTipoRecurso();
        	row.getValue().setText(vt.getCantidadContenedorByTipoRecurso(tipoRecurso), ea.getCantidadRecursoByTipo(tipoRecurso));
    	}

    }
}
