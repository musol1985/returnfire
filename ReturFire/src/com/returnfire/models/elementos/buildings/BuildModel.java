package com.returnfire.models.elementos.buildings;

import java.util.Map;

import com.entity.anot.collections.MapEntity;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.model.PhysicsBodyComponent.PhysicsBodyType;
import com.entity.anot.entities.ModelEntity;
import com.entity.core.items.Model;
import com.entity.core.items.ModelBase;
import com.entity.modules.gui.events.IDraggable;
import com.entity.modules.gui.items.SpriteBase.BUTTON;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.control.GhostControl;
import com.returnfire.GameContext;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.msg.MsgBuild;

@ModelEntity()
public class BuildModel extends Model implements IDraggable{
	@MapEntity(entries={}, packageItems="com.returnfire.models.elementos.buildings.nodos", packageFilter = ModelEntity.class)
	public Map<String, BuildNode> edificios;
	
	@PhysicsBodyComponent(type=PhysicsBodyType.GHOST_BODY, attachWorld = false)
	public GhostControl ghost;
	
	private BuildNode edificio;
	
	public void setEdificio(Class<? extends BuildNode> nuevoEdificio)throws Exception{
		if(edificio!=null){
			edificio.dettach();			
		}
		
		edificio=edificios.get(nuevoEdificio.getName());		


                ghost.setCollisionShape(edificio.getCollisionShape());
                edificio.addControl(ghost);
		
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
		
		return false;
	}

	@Override
	public void onDragging(ModelBase over) throws Exception {
		//TODO comprobar colisiones
		if(isColision()){
			System.out.println("No puedes poner ahi el edificio!!!!");
		}else{
			System.out.println("->Dragging");
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
