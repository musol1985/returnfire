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
        
        private boolean puede;
	
	public void setEdificio(Class<? extends BuildNode> nuevoEdificio)throws Exception{
		if(edificio!=null){
			edificio.dettach();			
		}
		
		edificio=edificios.get(nuevoEdificio.getName());	
                edificioMat=edificio.getMaterials();
                
                puede=true;
                for(Entry<Geometry, Material> g:edificioMat.entrySet()){
                    g.getKey().setMaterial(dragMat);
                }

                ghost.setCollisionShape(edificio.getCollisionShape());
                edificio.addControl(ghost);
                EntityManager.getGame().getPhysics().add(ghost);
		
		edificio.attachToParent(this);
	}

	@Override
	public void onDrag() throws Exception {
		System.out.println("On drag!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public boolean onDrop(BUTTON button) throws Exception {
		System.out.println("On drop!!!!!!!!!!!!!!!!!!!!");
		if(button==BUTTON.LEFT){
			if(isColision()){
				return true;
			}else{
				new MsgBuild(edificio.getClass(), getWorldTranslation(), 0, GameContext.getJugador().dao.getId()).send();
			}
		}
                for(Entry<Geometry, Material> g:edificioMat.entrySet()){
                    g.getKey().setMaterial(g.getValue());
                }
		EntityManager.getGame().getPhysics().remove(ghost);
		return false;
	}

	@Override
	public void onDragging(ModelBase over) throws Exception {
		//TODO comprobar colisiones
		if(isColision()){
                        dragMat.setColor("Color", ColorRGBA.Red);
		}else{
                        dragMat.setColor("Color", ColorRGBA.Green);
		}
	}
	
	private boolean isColision(){
		if(ghost.getOverlappingCount()>0)
			for(PhysicsCollisionObject obj:ghost.getOverlappingObjects()){
				if(obj.getUserObject() instanceof EstaticoModel){
					return true;
				}
			}
		return false;
	}

}
