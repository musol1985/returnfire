package com.returnfire.models;

import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.network.core.models.NetWorldCell;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.returnfire.dao.CeldaDAO;

public class CeldaModel extends NetWorldCell<CeldaDAO>{
	@TerrainComponent(LOD = false, realSize = MundoModel.CELL_SIZE)
	@MaterialComponent(asset="Materials/Terrain.j3m")
	private TerrainQuad terrain;
}
