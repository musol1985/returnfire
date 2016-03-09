package com.returnfire.models;

import com.entity.anot.Entity;
import com.entity.anot.Service;
import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.terrain.CustomHeightTerrain;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.anot.modificators.ApplyToComponent;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.items.IWorldInGameScene;
import com.entity.network.core.models.NetWorldCell;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;
import com.returnfire.models.batchs.ArbolesBatch;
import com.returnfire.models.batchs.ModelFactory;
import com.returnfire.models.elementos.ArbolModel;

public class CeldaModel extends NetWorldCell<CeldaDAO>{
    @TerrainComponent(LOD = true, realSize = MundoModel.CELL_SIZE, chunkSize = MundoModel.CELL_SIZE/4)
    @MaterialComponent(asset="Materials/Terrain.j3m")
    private TerrainQuad terrain;
    
    @Entity
    private ArbolesBatch arboles;
    
    @Service
    private ModelFactory factory;
    
    @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
       terrain.move(MundoModel.CELL_SIZE/2, -10, MundoModel.CELL_SIZE/2);
       
       if(getMundo().dao.isLowerRightCorner(dao.getId())){
           terrain.rotate(0,FastMath.PI,0);
       }else if(getMundo().dao.isLowerZSide(dao.getId())){
           terrain.rotate(0, FastMath.HALF_PI, 0);
       }
       
       
        addBox(ColorRGBA.Green, new Vector3f(5,0,5));
        addBox(ColorRGBA.Blue, new Vector3f(MundoModel.CELL_SIZE-5,0,5));
        addBox(ColorRGBA.Red, new Vector3f(5,0,MundoModel.CELL_SIZE-5));
        addBox(ColorRGBA.Yellow, new Vector3f(MundoModel.CELL_SIZE-5,0,MundoModel.CELL_SIZE-5));
        
        terrain.setShadowMode(RenderQueue.ShadowMode.Receive);
        
        for(ArbolDAO arbolDAO:dao.getArboles()){
           arbolDAO.getPos().y= terrain.getHeight(new Vector2f(arbolDAO.getPos().x, arbolDAO.getPos().z))-15;
           ArbolModel arbol= factory.crearArbol(null, arbolDAO);
           arboles.attachEntity(arbol);
           log.info("Arbol creado!!!->"+arbol);
        }
        arboles.getNode().setShadowMode(shadowMode.Cast);
        arboles.batch();
    }
    
    @CustomHeightTerrain
    @ApplyToComponent(component="terrain")
    private AbstractHeightMap generateHeight(Terrain t){
        return getMundo().getHeightService().generateHeight(t, dao, getMundo().dao);
        
    }
        
    private void addBox(ColorRGBA color, Vector3f pos){
         Box b = new Box(5, 5, 5);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(EntityManager.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        geom.setMaterial(mat);         
        geom.setShadowMode(RenderQueue.ShadowMode.Cast);
        attachChild(geom);
        geom.move(pos);
    }  
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
}
