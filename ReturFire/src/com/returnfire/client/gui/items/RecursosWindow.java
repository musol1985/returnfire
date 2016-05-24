/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import com.entity.adapters.listeners.ICameraUpdate;
import com.entity.core.EntityManager;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.returnfire.GameContext;
import com.returnfire.client.gui.controls.Window;
import com.returnfire.client.scenes.InGame;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

/**
 *
 * @author Edu
 */
public class RecursosWindow extends Window<RecursosRow> implements ICameraUpdate{
    private ConstruyendoModel model;
    private VehiculoTransporteModel vehiculo;
    
    public static RecursosWindow getNewWindow(Vector3f pos, ConstruyendoModel model, VehiculoTransporteModel v){
        RecursosWindow window=(RecursosWindow)EntityManager.instanceGeneric(RecursosWindow.class);
        window.instance("recursosWindow", pos);        
        window.model=model;
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
        Vector3f pos=c.getScreenCoordinates(model.getWorldTranslation());            
        setPosition(pos.x, pos.y);
    }
    
    public void addRow(RecursoDAO rEdificio, int max, int contenedoresVehiculo){
        RecursosRow row=(RecursosRow)EntityManager.instanceGeneric(RecursosRow.class);
        row.instance(rows.size(),contenedoresVehiculo,  rEdificio, max);
        super.addRow(rEdificio.tipo.name(), row);
    }
    
    public void onAdd(boolean all, RecursosRow row){    	
    	RecursoDAO rEdificio=row.getRecursoEdificio();
    	
    	int added=GameContext.getClientService().addRecursoTo(model.getCelda().getDao(), model.getDAO(), (VehiculoTransporteDAO)vehiculo.getDao(), rEdificio.tipo, all);
    	
        if(added>0){
        	VehiculoTransporteDAO vt=(VehiculoTransporteDAO)vehiculo.getDao();
        	row.setText(vt.getCantidadContenedorByTipoRecurso(rEdificio.tipo)-added, rEdificio.getCantidad()+added);
        }
    }
}
