package com.returnfire.models.elementos.buildings;

import java.util.Map;

import com.entity.anot.collections.MapEntity;
import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.EntityManager;
import com.entity.core.items.Model;
import com.entity.core.items.ModelBase;
import com.entity.modules.gui.events.IDraggable;
import com.entity.modules.gui.items.SpriteBase.BUTTON;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.returnfire.GameContext;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.msg.MsgBuild;
import java.util.HashMap;
import java.util.Map.Entry;

@ModelEntity()
public class BuildModel extends Model implements IDraggable{
	@MapEntity(entries={}, packageItems="com.returnfire.models.elementos.buildings.nodos", packageFilter = ModelEntity.class)
	public Map<String, BuildNode> edificios;
	
	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY, attachWorld = false)
	public GhostControl ghost;
	
	private BuildNode edificio;
        
        @MaterialComponent(asset = "Materials/buildings/drag.j3m")
        private Material dragMat;

        private HashMap<Geometry, Material> edificioMat;

	
	public void setEdificio(Class<? extends BuildNode> nuevoEdificio)throws Exception{
		if(edificio!=null){
			edificio.dettach();			
		}
		
		if(nuevoEdificio!=null){
			edificio=edificios.get(nuevoEdificio.getName());	
            edificioMat=edificio.getMaterials();
            
            setEdificioMaterial(dragMat);
            
            ghost.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
            ghost.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);

            ghost.setCollisionShape(edificio.getCollisionShape());
            edificio.addControl(ghost);
            EntityManager.getGame().getPhysics().add(ghost);
			
			edificio.attachToParent(this);
		}
	}

	
	private void setEdificioMaterial(Material m){
		 for(Entry<Geometry, Material> g:edificioMat.entrySet()){
			 if(m!=null){
				 g.getKey().setMaterial(dragMat);
			 }else{
				 g.getKey().setMaterial(g.getValue());
			 }
         }
	}

	@Override
	public void onDrag() throws Exception {
		System.out.println("On drag!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public boolean onDrop(BUTTON button) throws Exception {
		if(button==BUTTON.LEFT){
			if(puedeConstruirse()){				
				GameContext.getClientService().construirEdificio(this);
				setEdificioMaterial(null);
				edificio.dettach();	
				EntityManager.getGame().getPhysics().remove(ghost);				
			}else{
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void onDragging(ModelBase over) throws Exception {
		if(!puedeConstruirse()){
			dragMat.setColor("Color", ColorRGBA.Red);
		}else{
			dragMat.setColor("Color", ColorRGBA.Green);
		}
	}
	
	private boolean puedeConstruirse(){
		if(ghost.getOverlappingCount()>0)
			for(PhysicsCollisionObject obj:ghost.getOverlappingObjects()){
				if(obj.getUserObject() instanceof EstaticoModel){
					return edificio.puedeConstruirseAqui((EstaticoModel)obj.getUserObject());
				}
			}
		return false;
	}

	public BuildNode getEdificio() {
		return edificio;
	}

	
}
