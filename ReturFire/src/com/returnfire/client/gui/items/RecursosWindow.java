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
    
    public void addRow(RecursoDAO rEdificio, int max, int contenedoresVehiculo){
        RecursosRow row=(RecursosRow)EntityManager.instanceGeneric(RecursosRow.class);
        row.instance(rows.size(),contenedoresVehiculo,  rEdificio, max, !(edificio instanceof ConstruyendoModel));
        super.addRow(rEdificio.tipo.name(), row);
    }
    
    public void onAdd(boolean all, RecursosRow row){    	
    	RecursoDAO rEdificio=row.getRecursoEdificio();
    	
    	int added=GameContext.getClientService().addRecursoTo(edificio.getAlmacenCelda().getDao(), edificio.getAlmacenDAO(), (VehiculoTransporteDAO)vehiculo.getDao(), rEdificio.tipo, all);
    	
        if(added>0){
        	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
        	row.setText(vt.getCantidadContenedorByTipoRecurso(rEdificio.tipo)-added, rEdificio.getCantidad()+added);
        }
    }
    
    public void actualizar(){
    	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
    	for(Entry<String,RecursosRow> row:rows.entrySet()){    
    		RecursoDAO rEdificio=row.getValue().getRecursoEdificio();
        	row.getValue().setText(vt.getCantidadContenedorByTipoRecurso(rEdificio.tipo), rEdificio.getCantidad());
    	}

    }
}
