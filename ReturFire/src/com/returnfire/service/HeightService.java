/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.service;

import com.entity.core.items.BaseService;
import com.jme3.math.Vector2f;
import com.jme3.terrain.Terrain;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.MundoDAO;
import com.returnfire.models.CeldaModel;

/**
 *
 * @author Edu
 */
public class HeightService extends BaseService{
    public static final int OFFSET=50;
    public static final float HEIGHT_FACTOR=0.5f;
    public static final float MAX_HEIGHT=(CeldaModel.CELL_SIZE/2-OFFSET)*HEIGHT_FACTOR;
    
    public AbstractHeightMap generateHeight(Terrain t, CeldaDAO dao, MundoDAO mundo){

        if(mundo.isLowerRightCorner(dao.getId())){
            return cornerHeight;
        }else if(mundo.isSide(dao.getId())){
            return sideHeight;
        }
        return flat;
    }
    
    private AbstractHeightMap flat=new AbstractHeightMap() {			
            @Override
            public boolean load() {
                heightData=new float[(CeldaModel.CELL_SIZE+1)*(CeldaModel.CELL_SIZE+1)];
                size=CeldaModel.CELL_SIZE+1;
                
                for(int z=0;z<CeldaModel.CELL_SIZE+1;z++){
                    for(int x=0;x<CeldaModel.CELL_SIZE+1;x++){
                        setHeightAtPoint(MAX_HEIGHT, x, z);
                    }
                }
                setHeightAtPoint(MAX_HEIGHT+0.1f, 50, 50);
                return true;
            }
    };
    
    
    private AbstractHeightMap sideHeight=new AbstractHeightMap() {			
            @Override
            public boolean load() {
                heightData=new float[(CeldaModel.CELL_SIZE+1)*(CeldaModel.CELL_SIZE+1)];
                size=CeldaModel.CELL_SIZE+1;
                int offset=CeldaModel.CELL_SIZE-OFFSET;

                float y=0f;
                for(int z=CeldaModel.CELL_SIZE/2;z<CeldaModel.CELL_SIZE+1;z++){
                    for(int x=0;x<CeldaModel.CELL_SIZE+1;x++){
                        setHeightAtPoint(y, x, z);
                    }
                    if(z<offset)
                        y+=HEIGHT_FACTOR;
                }
                return true;
            }
    };	
    
    private AbstractHeightMap cornerHeight= new AbstractHeightMap() {			
            @Override
            public boolean load() {
                heightData=new float[(CeldaModel.CELL_SIZE+1)*(CeldaModel.CELL_SIZE+1)];
                size=CeldaModel.CELL_SIZE+1;

                

                for(int z=0;z<CeldaModel.CELL_SIZE/2;z++){
                    for(int x=0;x<CeldaModel.CELL_SIZE/2;x++){
                        Vector2f v=new Vector2f(x,z);
                        if(v.length()<OFFSET){
                            setHeightAtPoint(MAX_HEIGHT, x, z);
                        }else{
                            float y=(MAX_HEIGHT-((v.length()-OFFSET)*HEIGHT_FACTOR));
                            if(y>0){
                                setHeightAtPoint(y, x, z);
                            }else{
                                setHeightAtPoint(0, x, z);
                            }
                        }

                    }

                }
                return true;
            }
    };
}
