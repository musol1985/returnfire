/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.returnfire;

import com.entity.anot.components.model.MaterialComponent;
import com.entity.anot.components.terrain.CustomHeightTerrain;
import com.entity.anot.components.terrain.TerrainComponent;
import com.entity.anot.entities.ModelEntity;
import com.entity.anot.modificators.ApplyToComponent;
import com.entity.core.items.Model;
import com.jme3.math.Vector2f;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.returnfire.models.MundoModel;

/**
 *
 * @author Edu
 */
@ModelEntity
public class TestTerrain extends Model {
      @TerrainComponent(LOD = false, realSize = MundoModel.CELL_SIZE, chunkSize = MundoModel.CELL_SIZE/4)
    @MaterialComponent(asset="Materials/Terrain.j3m")
    private TerrainQuad terrain;
      private int OFFSET=MundoModel.CELL_SIZE-50;
      
      public TerrainQuad getT(){
          return terrain;
      }
      
      
      
      @CustomHeightTerrain
	@ApplyToComponent(component="terrain")
	public AbstractHeightMap generateHeight(Terrain t){
		return new AbstractHeightMap() {			
			@Override
			public boolean load() {
                 
                                heightData=new float[(MundoModel.CELL_SIZE+1)*(MundoModel.CELL_SIZE+1)];
                                size=MundoModel.CELL_SIZE+1;
                                int offset=50;
                                
                            
                            for(int z=0;z<MundoModel.CELL_SIZE/2;z++){
                                for(int x=0;x<MundoModel.CELL_SIZE/2;x++){
                                    Vector2f v=new Vector2f(x,z);
                                    if(v.length()<offset){
                                        setHeightAtPoint(39f, x, z);
                                    }else{
                                        float y=39-(v.length()-offset);
                                        if(y>0){
                                            setHeightAtPoint(y, x, z);
                                        }else{
                                            setHeightAtPoint(0, x, z);
                                        }
                                    }
                                    
                                }
       
                            }
               
                            
                            // TODO Auto-generated method stub
                            return true;
			}
		};
	}
}
