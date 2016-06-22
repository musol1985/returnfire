package com.returnfire.models;

import java.util.ArrayList;
import java.util.List;

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
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.PhysicsControl;
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
import com.returnfire.dao.elementos.buildings.impl.ExtractorHierroDAO;
import com.returnfire.dao.elementos.buildings.impl.ExtractorPetroleoDAO;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;
import com.returnfire.dao.elementos.environment.impl.ArbolDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoHierroDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;
import com.returnfire.dao.elementos.environment.impl.RockDAO;
import com.returnfire.map.CeldaMap;
import com.returnfire.map.MapEntry;
import com.returnfire.models.batchs.EstaticosBatch;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.EdificioModel;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.contenedores.ContenedorModel;

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
    
    @Service
    private CeldaMap mapa;


    @Override
    public int getCELL_SIZE() {
        return CELL_SIZE;
    }
    
    
    
    @Override
    public void onInstance(IBuilder builder, Object[] params)throws Exception {
        super.onInstance(builder, params); //To change body of generated methods, choose Tools | Templates.

       terrainBody.setPhysicsLocation(localToWorld(new Vector3f(CELL_SIZE/2,-10,CELL_SIZE/2)));

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
        
        mapa.iniciar(this);
        /*terrainBody.setCollisionGroup(PhysicsCollisionObject.COLLISION_GROUP_02);
        terrainBody.setCollideWithGroups(PhysicsCollisionObject.COLLISION_GROUP_02);*/
    }
    
    public CeldaMap getMapa(){
    	return mapa;
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

    public EstaticoModel<? extends EstaticoDAO, ? extends PhysicsControl> getEstatico(String estaticoId){
        return (EstaticoModel<? extends EstaticoDAO, ? extends PhysicsControl>)estaticos.getEntity(estaticoId);
    }
    
    public ContenedorModel<? extends ContenedorDAO> getContenedor(long id){
        return (ContenedorModel<? extends ContenedorDAO>)estaticos.getEntity(String.valueOf(id));
    }
    
    public EdificioModel<? extends EdificioDAO, ? extends PhysicsControl> getEdificio(String id){
        return (EdificioModel<? extends EdificioDAO, ? extends PhysicsControl>)edificios.getEntity(id);
    }
    
    /**
     * Crea el contenedorModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public ContenedorModel addContenedor(ContenedorDAO dao, boolean addDAO, boolean isNew)throws Exception{
        if(addDAO)
            this.dao.addContenedor(dao);
        
        ContenedorModel model=getMundo().getFactory().modelFactory.crearContenedor(dao);
        model.setLocation(dao.getPos(), this);
        
        estaticos.attachEntity(model);
        
        if(isNew){
        	
            estaticos.batch();
        }
        
        //addToMap(model);
        
        return model;
    }
    
    public void removeContenedor(ContenedorModel contenedor, boolean rebatch)throws Exception{
        estaticos.dettachEntity(contenedor);
        if(rebatch)
            estaticos.batch();
    }
    
    /**
     * Crea el edificioModel a partir del dao y lo anyade al dao de la celda
     * @param dao
     * @return
     */
    public EdificioModel addEdificio(EdificioDAO dao, boolean addDAO, boolean isNew)throws Exception{
        if(addDAO)
            this.dao.getEdificios().add(dao);
        
        Vector3f position=dao.getPos();
        EdificioModel model=null;
        if(dao instanceof BaseTierraDAO){
            model=getMundo().getFactory().modelFactory.crearBaseTierra(null, (EdificioVehiculosDAO)dao);   
        }else if(dao instanceof MolinoEolicoDAO){
            model=getMundo().getFactory().modelFactory.crearMolinoEolico(null, (MolinoEolicoDAO)dao);  
        }else if(dao instanceof ExtractorPetroleoDAO){
            model=getMundo().getFactory().modelFactory.crearExtractorPetroleo(null, (ExtractorPetroleoDAO)dao);  
        }else if(dao instanceof ExtractorHierroDAO){
            model=getMundo().getFactory().modelFactory.crearExtractorHierro(null, (ExtractorHierroDAO)dao);  
        }
        model.setLocation(position, this);
        
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
        model.setLocation(dao.getPos(), this);
        
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
    	
    	Vector3f position=estaticoDAO.getPos();
        if(estaticoDAO instanceof RockDAO){                    
            model=getMundo().getFactory().modelFactory.crearRoca((RockDAO)estaticoDAO);                    
        }else if(estaticoDAO instanceof ArbolDAO){
            model=getMundo().getFactory().modelFactory.crearArbol(null, (ArbolDAO)estaticoDAO);   
            position=position.add(0, 5, 0);
        }else if(estaticoDAO instanceof RecursoPetroleoDAO){
            model=getMundo().getFactory().modelFactory.crearRecursoPetroleo(null, (RecursoPetroleoDAO)estaticoDAO);   
        }else if(estaticoDAO instanceof RecursoHierroDAO){
            model=getMundo().getFactory().modelFactory.crearRecursoHierro(null, (RecursoHierroDAO)estaticoDAO);   
        }
        
        model.setLocation(position, this);
        
        estaticos.attachEntity(model);
        
        estaticos.getNode().setShadowMode(shadowMode.Cast);
        
        if(batch)
        	estaticos.batch();
        
        //addToMap(model);
        
        return  model;
    }
    
    public EdificioDAO onEdificioConstruido(ConstruyendoModel construyendo)throws Exception{
        return onEdificioConstruido(construyendo, construyendo.getDAO().getNewEdificioConstruido());
    }
    
    public EdificioDAO onEdificioConstruido(ConstruyendoModel construyendo, EdificioDAO nuevoEdificio)throws Exception{
        edificios.dettachEntity(construyendo);
        EdificioModel m=addEdificio(nuevoEdificio, true, true);
        
        return nuevoEdificio;
    }
}
