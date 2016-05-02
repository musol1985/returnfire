package com.returnfire.models;

import java.util.ArrayList;
import java.util.List;

import com.entity.anot.Entity;
import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.anot.components.terrain.CustomHeightTerrain;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.anot.modificators.ApplyToComponent;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.items.IWorldInGameScene;
import com.entity.network.core.models.NetWorldCell;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.buildings.impl.BaseTierraDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.map.MapEntry;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.EdificioModel;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.service.HeightService;

public class CeldaModel extends NetWorldCell<CeldaDAO>{
	public static final int CELL_SIZE=256;   
	
    @TerrainComponent(LOD = true, realSize = CELL_SIZE, chunkSize = CELL_SIZE/4)
    @MaterialComponent(asset="Materials/Terrain.j3m")
    private TerrainQuad terrain;
    
    @PhysicsBodyComponent
    @ApplyToComponent(component = "terrain")
    private RigidBodyControl terrainBody;
    
    @Entity
    private EstaticosBatch estaticos;
    
    @Entity
    private EstaticosBatch edificios;
    
    
    private List<List<MapEntry>> map;
    
    @Override
    public void onInstance(IBuilder builder, Object[] params)throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.
        
       initMap();
        
       terrain.move(CELL_SIZE/2, -10, CELL_SIZE/2);

       if(getMundo().dao.isLowerRightCorner(dao.getId())){
           terrain.rotate(0,FastMath.PI,0);
       }else if(getMundo().dao.isLowerZSide(dao.getId())){
           terrain.rotate(0, FastMath.HALF_PI, 0);
       }
        
        terrain.setShadowMode(RenderQueue.ShadowMode.Receive);
        
        if(dao.hasEstaticos()){
            for(EstaticoDAO estaticoDAO:dao.getEstaticos()){
            	addEstatico(estaticoDAO, false);
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
    
    private void initMap(){
    	 map=new ArrayList<List<MapEntry>>(CELL_SIZE);
         for(int x=0;x<CELL_SIZE;x++){
         	List<MapEntry> mY=new ArrayList<MapEntry>(CELL_SIZE);
         	for(int y=0;y<CELL_SIZE;y++){
         		mY.add(new MapEntry());
         	}
         	map.add(mY);
         }
    }
    
    private void addToMap(EstaticoModel e) throws Exception{
    	Vector2 pos=real2Map(e.getLocalTranslation());
    	for(int x=0;x<e.getSize().x;x++){
    		for(int z=0;z<e.getSize().z;z++){
    			MapEntry me=map.get(x).get(z);
    			if(me.isOcupado())
    				throw new Exception("Error, imposible ocupar con "+e+" la posicion ("+x+","+z+") de la celda "+dao.getId()+". Está ocupado por "+me.getElemento());
    			me.setOcupadoPor(e);
    		}
    	}
    }
    
    private Vector2 real2Map(Vector3f pos){
    	return new Vector2((int)pos.x/CELL_SIZE,(int)pos.z/CELL_SIZE);
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
    public EdificioModel addEdificio(EdificioDAO dao, boolean addDAO, boolean batch)throws Exception{
        if(addDAO)
            this.dao.getEdificios().add(dao);
        
        EdificioModel model=null;
        if(dao instanceof BaseTierraDAO){
            model=getMundo().getFactory().modelFactory.crearBaseTierra(null, (EdificioVehiculosDAO)dao);   
        }
        
        edificios.attachEntity(model);
        
        if(batch)
            edificios.batch();
        
        addToMap(model);
        
        return model;
    }
    
    /**
     * Crea el edificioModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public EstaticoModel addEstatico(EstaticoDAO estaticoDAO, boolean batch)throws Exception{
    	EstaticoModel model=null;
    	estaticoDAO.getPos().y=HeightService.MAX_HEIGHT-10;
    	
        if(estaticoDAO instanceof RockDAO){                    
            model=getMundo().getFactory().modelFactory.crearRoca((RockDAO)estaticoDAO, dao);                    
        }else if(estaticoDAO instanceof ArbolDAO){
            model=getMundo().getFactory().modelFactory.crearArbol(null, (ArbolDAO)estaticoDAO, dao);   
        }
        
        estaticos.attachEntity(model);
        
        estaticos.getNode().setShadowMode(shadowMode.Cast);
        
        if(batch)
        	estaticos.batch();
        
        addToMap(model);
        
        return  model;
    }
}
