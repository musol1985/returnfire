package com.returnfire.models;

import com.entity.anot.Entity;
import com.entity.anot.Service;
import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.terrain.CustomHeightTerrain;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.anot.modificators.ApplyToComponent;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.items.IWorldInGameScene;
import com.entity.network.core.models.NetWorldCell;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.factory.ModelFactory;
import com.returnfire.service.HeightService;

public class CeldaModel extends NetWorldCell<CeldaDAO>{
    @TerrainComponent(LOD = true, realSize = MundoModel.CELL_SIZE, chunkSize = MundoModel.CELL_SIZE/4)
    @MaterialComponent(asset="Materials/Terrain.j3m")
    private TerrainQuad terrain;
    
    @PhysicsBodyComponent
    @ApplyToComponent(component = "terrain")
    private RigidBodyControl terrainBody;
    
    @Entity
    private EstaticosBatch estaticos;
    
    @Entity
    private EstaticosBatch edificios;
    
    @Service
    private ModelFactory factory;
    
    @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
       terrain.move(MundoModel.CELL_SIZE/2, -10, MundoModel.CELL_SIZE/2);
       //terrainBody.setPhysicsLocation(new Vector3f(terrain.getWorldTranslation().x+MundoModel.CELL_SIZE/2, -10, terrain.getWorldTranslation().z+MundoModel.CELL_SIZE/2));
       
       if(getMundo().dao.isLowerRightCorner(dao.getId())){
           terrain.rotate(0,FastMath.PI,0);
       }else if(getMundo().dao.isLowerZSide(dao.getId())){
           terrain.rotate(0, FastMath.HALF_PI, 0);
       }
       
       
        /*addBox(ColorRGBA.Green, new Vector3f(5,0,5));
        addBox(ColorRGBA.Blue, new Vector3f(MundoModel.CELL_SIZE-5,0,5));
        addBox(ColorRGBA.Red, new Vector3f(5,0,MundoModel.CELL_SIZE-5));
        addBox(ColorRGBA.Yellow, new Vector3f(MundoModel.CELL_SIZE-5,0,MundoModel.CELL_SIZE-5));*/
        
        terrain.setShadowMode(RenderQueue.ShadowMode.Receive);
        
        if(dao.hasEstaticos()){
            for(EstaticoDAO estaticoDAO:dao.getEstaticos()){
            	EstaticoModel model=null;
            	estaticoDAO.getPos().y=HeightService.MAX_HEIGHT-10;
            	
                if(estaticoDAO instanceof RockDAO){                    
                    model=factory.crearRoca((RockDAO)estaticoDAO, dao);                    
                }else if(estaticoDAO instanceof ArbolDAO){
                    model=factory.crearArbol(null, (ArbolDAO)estaticoDAO, dao);   
                }
                
                estaticos.attachEntity(model);
            }
            estaticos.getNode().setShadowMode(shadowMode.Cast);
            estaticos.batch();
        }
        
        if(dao.hasEdificios()){
            for(EdificioDAO edificio:dao.getEdificios()){
                edificio.getPos().y=HeightService.MAX_HEIGHT-10;
                EdificioModel model=addEdificio(edificio, false, false);            
            }

            edificios.getNode().setShadowMode(shadowMode.Cast);
            edificios.batch();
        }
        
        /*terrainBody.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
        terrainBody.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);*/
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
        
        RigidBodyControl bo=new RigidBodyControl(new BoxCollisionShape(new Vector3f(2.5f,2.5f,2.5f)),0f);
        geom.addControl(bo);
        EntityManager.getGame().getPhysics().add(bo);
    }  
    

    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
    
    public void eliminarEstatico(String estaticoId){
        estaticos.dettachEntity(estaticoId);
        estaticos.batch();
    }

    public EstaticoModel getEstatico(String estaticoId){
        return (EstaticoModel)estaticos.getEntity(estaticoId);
    }
    
    /**
     * Crea el edificioModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public EdificioModel addEdificio(EdificioDAO dao, boolean addDAO, boolean batch){
        if(addDAO)
            this.dao.getEdificios().add(dao);
        
        EdificioModel model=null;
        if(dao.getTipoEdificio()==EdificioDAO.EDIFICIOS.BASE_TIERRA_PEQUE){
            model=factory.crearBaseTierra(null, (EdificioVehiculosDAO)dao, factory);   
        }
        
        edificios.attachEntity(model);
        
        if(batch)
            edificios.batch();
        
        return model;
    }
}
