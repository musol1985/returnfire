package com.returnfire.models.elementos.buildings;

import java.util.Map;

import com.entity.anot.collections.MapEntity;
import com.entity.core.items.Model;
import com.entity.core.items.ModelBase;
import com.entity.modules.gui.events.IDraggable;
import com.entity.modules.gui.items.SpriteBase.BUTTON;
import com.returnfire.models.elementos.IEstaticoNode;

public class BuildModel extends Model implements IDraggable{
	@MapEntity(entries={}, packageItems="com.returnfire.models.elementos.buildings.nodos")
	private Map<String, Model> edificios;
	
	private Model edificio;
	
	public void setEdificio(Class<? extends IEstaticoNode> nuevoEdificio)throws Exception{
		if(edificio!=null){
			edificio.dettach();
		}
		
		edificio=edificios.get(nuevoEdificio);
		edificio.attachToParent(this);
	}

	@Override
	public void onDrag(ModelBase over) throws Exception {
		System.out.println("On drag!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public void onDrop(ModelBase over, BUTTON button) throws Exception {
		System.out.println("On drop!!!!!!!!!!!!!!!!!!!!");
	}

	@Override
	public void onDragging(ModelBase over) throws Exception {
		//TODO comprobar colisiones
	}
}
