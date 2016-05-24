/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings.impl;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.Vector3f;
import com.returnfire.client.gui.items.RecursosWindow;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

/**
 *
 * @author Edu
 */

@ModelEntity(asset = "Models/buildings/construyendo.j3o")
public class ConstruyendoModel extends EdificioModel<ConstruyendoDAO> {
    private RecursosWindow window;
    
    @PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getZona")
    public GhostControl zona;
	
    @Override
    public CollisionShape getColisionShape() {
            // TODO Auto-generated method stub
            return new BoxCollisionShape(new Vector3f(dao.getSize().x,0.1f,dao.getSize().z));
    }
    
    
    public CollisionShape getZona() {
            // TODO Auto-generated method stub
            return new SphereCollisionShape(dao.getSize().x*2);
    }

    @Override
    public boolean onEliminar(Vector3f vel) {
            // TODO Auto-generated method stub
            return false;
    }



    @Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); 
        zona.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);
        zona.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
        //TODO add suelo y add de las vallas
    }

    public boolean isVehiculoEnZona(VehiculoModel vehiculo){
        for(PhysicsCollisionObject obj:zona.getOverlappingObjects()){
            if(obj.getUserObject()==vehiculo)
                return true;
        }
        return false;
    }
    
    public void onVehiculoEnZona(VehiculoModel v){
        if(window==null && v.isTransporte()){
            window=RecursosWindow.getNewWindow(getWorldTranslation(), this, (VehiculoTransporteModel)v);
            VehiculoTransporteDAO vDao=(VehiculoTransporteDAO)v.getDao();
            
            for(RecursoDAO rEdificio:getDAO().getRecursosNecesarios()){
            	window.addRow(rEdificio, dao.getEdificio().getNecesarioByTipo(rEdificio.tipo), vDao.getCantidadContenedorByTipoRecurso(rEdificio.tipo));
            }
        }
    }
    
    public void onVehiculoFueraZona(VehiculoModel v){
        if(window!=null){
            window.remove();
            window=null;
        }
    }

  
}
