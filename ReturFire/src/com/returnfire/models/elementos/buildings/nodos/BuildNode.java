package com.returnfire.models.elementos.buildings.nodos;

import com.entity.core.items.Model;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.returnfire.dao.JugadorDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.models.elementos.IEstaticoNode;

import java.util.HashMap;




public abstract class BuildNode extends Model implements IEstaticoNode{
	public abstract CollisionShape getCollisionShape(); 
	public abstract Class<? extends EdificioDAO> getDAO();
       
        
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
}
