package com.returnfire.models.elementos.buildings.nodos;

import java.util.HashMap;

import com.entity.core.items.Model;
import com.entity.utils.Vector2;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.dao.elementos.buildings.EdificioDAO;




public abstract class BuildNode extends Model{
	public abstract CollisionShape getCollisionShape(); 
	public abstract Class<? extends EdificioDAO> getDAO();
       
	private Vector2 size;
        
    public HashMap<Geometry, Material> getMaterials(){
        return rellenarMaterials(new HashMap<Geometry, Material>(), getNode()); 
    }
    
    
    private HashMap<Geometry, Material> rellenarMaterials(HashMap<Geometry, Material> res, Spatial s){
        if(s instanceof Geometry){
            res.put((Geometry)s, ((Geometry)s).getMaterial());
        }else if( s instanceof Node){
            for(Spatial child:((Node)s).getChildren()){
                rellenarMaterials(res, child);
            }            
        }
        return res;
    }
    
    
    public Vector2 getSize(){
    	if(size==null){
	    	try{
	    		size=getDAO().newInstance().getSize();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		return null;
	    	}	    	
    	}
    	return size;
    }
}
