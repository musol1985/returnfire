package com.returnfire.models;

import com.entity.adapters.DirectionalLightShadow;
import com.entity.adapters.FollowCameraAdapter;
import com.entity.adapters.listeners.IFollowCameraListener;
import com.entity.anot.Service;
import com.entity.anot.components.lights.AmbientLightComponent;
import com.entity.anot.components.lights.DirectionalLightComponent;
import com.entity.anot.components.shadows.DirectionalShadowComponent;
import com.entity.anot.effects.BloomEffect;
import com.entity.anot.effects.WaterEffect;
import com.entity.network.core.models.NetWorld;
import com.jme3.light.AmbientLight;
import com.jme3.post.filters.BloomFilter;
import com.jme3.water.WaterFilter;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.factory.ModelFactory;
import com.returnfire.service.HeightService;


public class MundoModel extends NetWorld<MundoDAO, CeldaModel>{
        public static final int CELL_SIZE=256;   
        
        //@BloomEffect(bloomIntensity = 2.45f, downSamplingFactor = 2, blurScale = 1.37f, exposureCutOff = 0.2f, exposurePower = 3.30f)
        private BloomFilter bloom;

        @WaterEffect(waterHeight = 10f)
        private WaterFilter agua;
        
        @AmbientLightComponent(color = {1,1,1,1}, mult = 1f)
        private AmbientLight ambient;

        @DirectionalLightComponent(color = {1,1,1,0.2f},direction = {1,-1,-0.5f})
        @DirectionalShadowComponent
        private DirectionalLightShadow sol;
        
        @Service
        private HeightService heightService;

        
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

    public HeightService getHeightService() {
        return heightService;
    }

}
