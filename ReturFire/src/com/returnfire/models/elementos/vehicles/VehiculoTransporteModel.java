/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.vehicles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.entity.anot.OnCollision;
import com.entity.anot.components.model.SubModelComponent;
import com.jme3.bullet.objects.PhysicsRigidBody;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.vehiculos.VehiculoTransporteDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.bullets.BulletModel.BALAS;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.msg.MsgDisparar;
import com.returnfire.msg.MsgOnContenedorEdificio;
import com.returnfire.msg.MsgOnVehiculoCogeContenedor;

/**
 *
 * @author Edu
 */
public abstract class VehiculoTransporteModel<T extends PhysicsRigidBody> extends VehiculoModel<T, VehiculoTransporteDAO>{	
    
    private List<ConstruyendoModel> construcciones=new ArrayList<ConstruyendoModel>();
    
	@SubModelComponent(name="contenedoresReference")
	public Node contenedoresReference;
	
    /**
     * Metodo que se encarga de colocar el contenedor en el model
     * Solo sera llamado desde el server!!
     * @param c
     * @throws Exception
     */
    public abstract void colocarContenedor(ContenedorModel c, int size)throws Exception;
    
    @OnCollision(includeSubClass = true)
    public void onColisionContenedor(ContenedorModel<ContenedorDAO> contenedor)throws Exception{
    	/*int size=dao.getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
    		contenedor.dettach();
    		contenedor.attachToParent(this);
    		colocarContenedor(contenedor, size);
    	}*/   
        if(tieneSitio()){
            contenedor.body.setEnabled(false);
            new MsgOnVehiculoCogeContenedor(contenedor.getCelda().dao.getId(), dao.getIdLong(), contenedor.getDAO().getIdLong()).send();
        }
    }
    
    public boolean tieneSitio(){
        return dao.getContenedoresSize()<dao.getMaxSlots();
    }
    
    public void cogeContenedor(ContenedorModel<ContenedorDAO> contenedor, CeldaModel celda)throws Exception{
    	int size=getDao().getContenedoresSize();
    	if(dao.addContenedor(contenedor.getDAO())){
            celda.removeContenedor(contenedor, true);    		
            contenedor.attachToParent(this);
            colocarContenedor(contenedor, size);
    	}else{
    		throw new Exception("No se ha podido a�adir el contenedor "+contenedor.getDAO().getId());
    	}
    }
    
    @OnCollision
   public void onColisionVehiculo(ConstruyendoModel construccion)throws Exception{
       if(!construcciones.contains(construccion)){
           construcciones.add(construccion);
           construccion.onVehiculoEnZona(this);
       }
   }
   
   public void onUpdate(float tpf)throws Exception{
       Iterator<ConstruyendoModel> it=construcciones.iterator();
       while(it.hasNext()){
           ConstruyendoModel edificio=it.next();
           if(!edificio.isVehiculoEnZona(this)){
               System.out.println("------------------------>remove!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
               edificio.onVehiculoFueraZona(this);
               it.remove();               
           }
       }
   }
   
   @Override
	public void onAccion() {
		new MsgDisparar(BALAS.NORMAL, player.getDao().getId()).send();
	}
        
        
    public void addRecursoTo(EdificioModel<EdificioDAO> edificio, ContenedorDAO.RECURSO recurso, boolean all){
        List<Long> contenedores=new ArrayList<Long>();
        
        boolean seguir=true;
        
        int oldValor=0;
        if(recurso==ContenedorDAO.RECURSO.PETROLEO){
            oldValor=edificio.getDAO().getPetroleoNecesario();
        }else{
            oldValor=edificio.getDAO().getPiezasNecesarias();
        }
        
        Iterator<ContenedorDAO> it=dao.getContenedores().iterator();
        while(seguir && it.hasNext()){
            ContenedorDAO c=it.next();
            if(edificio.getDAO().addRecurso(recurso)){
                contenedores.add(c.getIdLong());
                //it.remove();
                if(!all)
                    seguir=false;
            }
        }
        
        if(recurso==ContenedorDAO.RECURSO.PETROLEO){
            ((ConstruyendoDAO)edificio.getDAO()).setPetroleo(oldValor);
        }else{
            ((ConstruyendoDAO)edificio.getDAO()).setPiezas(oldValor);
        }

       new MsgOnContenedorEdificio(edificio.getCelda().dao.getId(), dao.getIdLong(), edificio.getDAO().getId(), contenedores).send();
    }
    
    public ContenedorModel<ContenedorDAO> getContenedorById(long id){
        for(Spatial s:getChildren()){
            if(s instanceof ContenedorModel){
                ContenedorModel<ContenedorDAO> c=(ContenedorModel<ContenedorDAO>)s;
                if(c.getDAO().getIdLong()==id)
                    return c;
            }
        }
        return null;
    }
    
    public ContenedorModel quitaContenedor(long id){
        return quitaContenedor(getContenedorById(id));
    }
    
    public ContenedorModel quitaContenedor(ContenedorModel c){
        try{
            dao.getContenedores().remove((ContenedorDAO)c.getDAO());
            c.dettach();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return c;
    }

}
