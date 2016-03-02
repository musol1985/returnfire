package com.returnfire.models;

import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.models.NetWorldCell;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.returnfire.dao.CeldaDAO;

public class CeldaModel extends NetWorldCell<CeldaDAO>{
	@TerrainComponent(LOD = false, realSize = MundoModel.CELL_SIZE)
	@MaterialComponent(asset="Materials/Terrain.j3m")
	private TerrainQuad terrain;

    @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
       terrain.move(MundoModel.CELL_SIZE/2, 0, MundoModel.CELL_SIZE/2);
        addBox(ColorRGBA.Green, new Vector3f(5,0,5));
        addBox(ColorRGBA.Blue, new Vector3f(MundoModel.CELL_SIZE-5,0,5));
        addBox(ColorRGBA.Red, new Vector3f(5,0,MundoModel.CELL_SIZE-5));
        addBox(ColorRGBA.Yellow, new Vector3f(MundoModel.CELL_SIZE-5,0,MundoModel.CELL_SIZE-5));
    }
        
    private void addBox(ColorRGBA color, Vector3f pos){
         Box b = new Box(5, 5, 5);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(EntityManager.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);         
        
        attachChild(geom);
        geom.move(pos);
    }  
}
