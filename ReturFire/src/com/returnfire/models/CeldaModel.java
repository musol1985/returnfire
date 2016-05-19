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
import com.jme3.scene.shape.Box;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.returnfire.GameContext;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.buildings.impl.BaseTierraDAO;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;
import com.returnfire.dao.elementos.contenedores.BarrilDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.map.MapEntry;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
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
        
       //initMap();
        
       terrain.move(CELL_SIZE/2, -10, CELL_SIZE/2);

       if(getMundo().dao.isLowerRightCorner(dao.getId())){
           terrain.rotate(0,FastMath.PI,0);
       }else if(getMundo().dao.isLowerZSide(dao.getId())){
           terrain.rotate(0, FastMath.HALF_PI, 0);
       }
        
        terrain.setShadowMode(RenderQueue.ShadowMode.Receive);
        
        if(dao.hasEstaticos() || dao.hasContenedores()){
            for(EstaticoDAO estaticoDAO:dao.getEstaticos()){
            	addEstatico(estaticoDAO, false);
            }
            for(ContenedorDAO contenedor:dao.getContenedores()){
            	addContenedor(contenedor, false, false);
            }
            estaticos.getNode().setShadowMode(shadowMode.Cast);
            estaticos.batch();
        }
        
        if(dao.hasEdificios()){
            for(EdificioDAO edificio:dao.getEdificios()){                
                addEdificio(edificio, false, false);            
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
         		MapEntry me=new MapEntry();
         		
         		if(y>0 && x>0){
         			me.setSur(getMapEntry(x,y-1));
                                me.setOeste(getMapEntry(x-1,y));
                        }

         		
         		mY.add(me);
         	}
         	map.add(mY);
         }
         
         //Obtenemos las celdas de alrededor
         CeldaModel cNorte=GameContext.getService().getCellFromCache(dao.getId().id.add(0, +1));
         CeldaModel cSur=GameContext.getService().getCellFromCache(dao.getId().id.add(0, -1));
         CeldaModel cEste=GameContext.getService().getCellFromCache(dao.getId().id.add(-1, 0));
         CeldaModel cOeste=GameContext.getService().getCellFromCache(dao.getId().id.add(+1, 0));
         
         for(int i=0;i<CELL_SIZE;i++){
        	 if(cNorte!=null)
        		 cNorte.getMapEntry(i, 0).setSur(getMapEntry(i, CELL_SIZE-1));
        	 if(cSur!=null)
        		 getMapEntry(i, 0).setSur(cSur.getMapEntry(i, CELL_SIZE-1));
        	 if(cEste!=null)
        		 cEste.getMapEntry(CELL_SIZE-1, i).setOeste(getMapEntry(0 ,i));
        	 if(cOeste!=null)
        		 getMapEntry(CELL_SIZE-1, i).setOeste(cOeste.getMapEntry(0 ,i));
         }
    }
    
    public boolean isZonaOcupada(Vector3f pReal, Vector2 size)throws Exception{
        return false;/*
    	Vector2 pos=real2Map(pReal);
    	
    	MapEntry meY=getMapEntry(pos.x, pos.z);
		MapEntry meX=meY;
		
    	for(int x=0;x<size.x;x++){
    		
    		for(int z=0;z<size.z;z++){
    			if(meY.isOcupado())
    				return true;    				
    			
    			meY=meY.getNorte();
    		}
    		
    		meX=meX.getOeste();
    		meY=meX;
    	}
    	return false;*/
    }
    

    private void addToMap(EstaticoModel<? extends EstaticoDAO> e) throws Exception{
    	Vector2 pos=real2Map(e.getLocalTranslation());
    	
    	MapEntry meY=getMapEntry(pos.x, pos.z);
		MapEntry meX=meY;
		
    	for(int x=0;x<e.getDAO().getSize().x;x++){
    		
    		for(int z=0;z<e.getDAO().getSize().z;z++){
    			if(meY.isOcupado())
    				throw new Exception("Error, imposible ocupar con "+e+" la posicion ("+pos.x+"+"+x+","+pos.z+"+"+z+") de la celda "+dao.getId()+". Est� ocupado por "+meY.getElemento());
    			meY.setOcupadoPor(e);
    			
    			meY=meY.getNorte();
    			
    		}
    		
    		meX=meX.getOeste();
    		meY=meX;
    	}
    }
    
    public MapEntry getMapEntry(int x, int z){
    	return map.get(x).get(z);
    }
    
    public Vector2 real2Map(Vector3f pos){
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

    public EstaticoModel<? extends EstaticoDAO> getEstatico(String estaticoId){
        return (EstaticoModel<? extends EstaticoDAO>)estaticos.getEntity(estaticoId);
    }
    
    public ContenedorModel<? extends ContenedorDAO> getContenedor(long id){
        return (ContenedorModel<? extends ContenedorDAO>)estaticos.getEntity(String.valueOf(id));
    }
    
    /**
     * Crea el contenedorModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public ContenedorModel addContenedor(ContenedorDAO dao, boolean addDAO, boolean isNew)throws Exception{
        if(addDAO)
            this.dao.addContenedor(dao);
        
        ContenedorModel model=null;
        if(dao instanceof BarrilDAO){
            model=getMundo().getFactory().modelFactory.crearBarril(null, (BarrilDAO)dao);   
        }
        
        estaticos.attachEntity(model);
        
        if(isNew){
        	
            estaticos.batch();
        }
        
        //addToMap(model);
        
        return model;
    }
    
    /**
     * Crea el edificioModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public EdificioModel addEdificio(EdificioDAO dao, boolean addDAO, boolean isNew)throws Exception{
        if(addDAO)
            this.dao.getEdificios().add(dao);
        
        EdificioModel model=null;
        if(dao instanceof BaseTierraDAO){
            model=getMundo().getFactory().modelFactory.crearBaseTierra(null, (EdificioVehiculosDAO)dao);   
        }else if(dao instanceof MolinoEolicoDAO){
            model=getMundo().getFactory().modelFactory.crearMolinoEolico(null, (MolinoEolicoDAO)dao);  
        }
        
        edificios.attachEntity(model);
        
        if(isNew){
        	
            edificios.batch();
        }
        
        //addToMap(model);
        
        return model;
    }
    
    /**
     * Crea el construyendo edificio
     * @param dao
     * @return
     */
    public EdificioModel addConstruyendoEdificio(ConstruyendoDAO dao, boolean addDAO, boolean isNew)throws Exception{
        if(addDAO)
            this.dao.getEdificios().add(dao);
        
        ConstruyendoModel model=getMundo().getFactory().modelFactory.crearConstruyendo(null, dao);
        
        edificios.attachEntity(model);
        
        if(isNew){        	
            edificios.batch();
        }
        
        //addToMap(model);
        
        return model;
    }
    
    /**
     * Crea el edificioModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public EstaticoModel addEstatico(EstaticoDAO estaticoDAO, boolean batch)throws Exception{
    	EstaticoModel model=null;
    	
    	
        if(estaticoDAO instanceof RockDAO){                    
            model=getMundo().getFactory().modelFactory.crearRoca((RockDAO)estaticoDAO, dao);                    
        }else if(estaticoDAO instanceof ArbolDAO){
            model=getMundo().getFactory().modelFactory.crearArbol(null, (ArbolDAO)estaticoDAO, dao);   
        }
        
        estaticos.attachEntity(model);
        
        estaticos.getNode().setShadowMode(shadowMode.Cast);
        
        if(batch)
        	estaticos.batch();
        
        //addToMap(model);
        
        return  model;
    }
}
