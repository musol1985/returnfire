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
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.returnfire.GameContext;
import com.returnfire.map.MapEntry;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
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
                if(over instanceof CeldaModel){
                    CeldaModel celda=(CeldaModel)over;
                    float y=getWorldTranslation().y;
                    Vector2f p=celda.getMapa().world2Map(getWorldTranslation()).mult(MapEntry.MAP_ENTRY_SIZE);
                    Vector3f pos=celda.localToWorld(p);
                    //setLocalTranslation(pos.add(0,y,0));
                    //edificio.setLocalTranslation(celda.dao.getId().id.x*CeldaModel.CELL_SIZE+p.x, edificio.getWorldTranslation().y, celda.dao.getId().id.z*CeldaModel.CELL_SIZE+p.y);                    
                }
	}
	
	private boolean puedeConstruirse(){
		if(ghost.getOverlappingCount()>0){
			for(PhysicsCollisionObject obj:ghost.getOverlappingObjects()){
				if(obj.getUserObject() instanceof EstaticoModel){
					return edificio.puedeConstruirseAqui((EstaticoModel)obj.getUserObject());
				}
			}
                }else{
                    return edificio.puedeConstruirseAqui(null);
                }
                return false;
	}

	public BuildNode getEdificio() {
		return edificio;
	}

	
}
