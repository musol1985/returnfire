package com.returnfire.models.elementos.buildings.nodos;

import com.entity.anot.entities.ModelEntity;
import com.entity.core.items.Model;
import com.entity.utils.Vector2;
import com.returnfire.models.elementos.IEstaticoNode;

@ModelEntity(asset = "Models/buildings/base_tierra_peque.j3o")
public class BaseTierraPequeNode extends Model implements IEstaticoNode{

	@Override
	public Vector2 getSize() {
		return new Vector2(2,2);
	}

}
