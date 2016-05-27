package com.returnfire.models.elementos.buildings;

import java.util.List;
import java.util.Map.Entry;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.components.model.collision.CustomCollisionShape;
import com.entity.core.IBuilder;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.client.gui.items.RecursosWindow;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;
import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.models.elementos.vehicles.VehiculoModel;
import com.returnfire.models.elementos.vehicles.VehiculoTransporteModel;

public abstract class EdificioAlmacenModel<T extends EdificioAlmacenDAO, N extends BuildNode> extends EdificioConstruibleModel<T, N> implements IAlmacenable{

	private RecursosWindow window;
	
	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY)
    @CustomCollisionShape(methodName = "getZonaShape")
    public GhostControl zona;

	
	public abstract CollisionShape getZonaShape();
	
	
	private VehiculoTransporteModel vehiculoInZona;
	
	public GhostControl getZona(){
		return zona;
	}

	public VehiculoTransporteModel getVehiculoInZona() {
		return vehiculoInZona;
	}

	public void setVehiculoInZona(VehiculoTransporteModel vehiculoInZona) {
		this.vehiculoInZona = vehiculoInZona;
	}

	public boolean tieneVehiculoEnZona(){
		return vehiculoInZona!=null;
	}
	
	@Override
    public void onInstance(IBuilder builder, Object[] params) throws Exception {
        super.onInstance(builder, params); 
        zona.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);
        zona.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
    }

	@Override
    public boolean isVehiculoEnZona(VehiculoModel vehiculo){
        for(PhysicsCollisionObject obj:zona.getOverlappingObjects()){
            if(obj.getUserObject()==vehiculo)
                return true;
        }
        return false;
    }

	@Override
    public void onVehiculoEnZona(VehiculoModel v){
        if(window==null && v.isTransporte()){
            setVehiculoInZona((VehiculoTransporteModel) v);
            window=RecursosWindow.getNewWindow(getWorldTranslation(), this, (VehiculoTransporteModel)v);
            VehiculoTransporteDAO vDao=(VehiculoTransporteDAO)v.getDao();
            

            for(RecursoDAO rEdificio:getDAO().getRecursosAlmacenados()){
            	RECURSOS tipo=rEdificio.getTipo();
            	window.addRow(tipo, vDao.getCantidadContenedorByTipoRecurso(tipo), getDAO().getCantidadRecursoByTipo(tipo), getDAO().getCantidadMaximaQuePuedeAlmacenar(tipo), true, true);
            }
            
            for(Entry<RECURSOS, List<ContenedorDAO>> cVehiculo:vDao.getContenedores().entrySet()){
            	RECURSOS tipo=cVehiculo.getKey();
            	
            	if(!getDAO().hasRecurso(tipo)){
            		window.addRow(tipo, cVehiculo.getValue().size(), 0, getDAO().getCantidadMaximaQuePuedeAlmacenar(tipo), true, getDAO().puedeAlmacenar(tipo));
            	}            	
            }
        }
    }

	@Override
    public void onVehiculoFueraZona(VehiculoModel v){
        if(window!=null){
        	setVehiculoInZona(null);
            window.remove();
            window=null;
        }
    }

	public RecursosWindow getWindow() {
		return window;
	}

    @Override
    public EdificioAlmacenDAO getAlmacenDAO() {
        return (EdificioAlmacenDAO)getDAO();
    }

    @Override
    public CeldaModel getAlmacenCelda() {
        return getCelda();
    }
	
	

}
