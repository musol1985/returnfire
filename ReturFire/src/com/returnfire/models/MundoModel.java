package com.returnfire.models;

import com.entity.adapters.DirectionalLightShadow;
import com.entity.anot.components.lights.AmbientLightComponent;
import com.entity.anot.components.lights.DirectionalLightComponent;
import com.entity.anot.components.shadows.DirectionalShadowComponent;
import com.entity.anot.effects.WaterEffect;
import com.entity.network.core.models.NetWorld;
import com.jme3.light.AmbientLight;
import com.jme3.water.WaterFilter;
import com.returnfire.dao.MundoDAO;


public class MundoModel extends NetWorld<MundoDAO, CeldaModel>{
        public static final int CELL_SIZE=256;        
        
        @WaterEffect(waterHeight = -1.5f)
        private WaterFilter agua;
        
        @AmbientLightComponent(color = {1,1,1,1}, mult = 1.2f)
        private AmbientLight ambient;

        @DirectionalLightComponent(color = {1,1,1,1},direction = {1,1,1})
        @DirectionalShadowComponent
        private DirectionalLightShadow directional;

        
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

        
}
