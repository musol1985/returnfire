package com.returnfire.models;

import com.entity.adapters.DirectionalLightShadow;
import com.entity.anot.Entity;
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
import com.returnfire.models.batchs.BalasBatch;
import com.returnfire.models.batchs.DinamicosBatch;
import com.returnfire.models.batchs.VehiculosBatch;
import com.returnfire.models.elementos.BulletModel;
import com.returnfire.models.factory.BalasFactory;
import com.returnfire.service.HeightService;


public class MundoModel extends NetWorld<MundoDAO, CeldaModel, JugadorModel>{
        public static final int CELL_SIZE=256;   
        
        @BloomEffect(mode=BloomFilter.GlowMode.Objects, bloomIntensity = 2f, downSamplingFactor = 1, blurScale = 1.5f, exposureCutOff = 0f, exposurePower = 1.3f)
        public BloomFilter bloom;


        @WaterEffect(waterHeight = 10f)
        private WaterFilter agua;
        
        @AmbientLightComponent(color = {1,1,1,1}, mult = 1f)
        private AmbientLight ambient;

        @DirectionalLightComponent(color = {1,1,1,0.2f},direction = {1,-1,-0.5f})
        @DirectionalShadowComponent
        private DirectionalLightShadow sol;
        
        @Service
        private HeightService heightService;
        
        @Entity
        private VehiculosBatch vehiculos;
        
        @Entity
        private BalasBatch balas;
        
        @Entity
        private DinamicosBatch dinamicos;
        
        @Service
        private BalasFactory balasFactory;

        
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

    public HeightService getHeightService() {
        return heightService;
    }

    public VehiculosBatch getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(VehiculosBatch vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void cargarJugadores()throws Exception{
        for(JugadorModel j:players.values()){
            j.seleccionarVehiculo();
            //j.getVehiculo().attachToParent(vehiculos);  
        }               
    }

	public BalasBatch getBalas() {
		return balas;
	}
	
	

	public DinamicosBatch getDinamicos() {
		return dinamicos;
	}

	public BalasFactory getBalasFactory() {
		return balasFactory;
	}

    public void addBala(BulletModel bullet){
		balas.nueva(bullet);	
    }
}
