package com.returnfire.map;

import com.jme3.scene.BatchNode;
import com.returnfire.models.CeldaModel;


public class CeldaMapDebug extends CeldaMap<MapEntryDebug>{
	private BatchNode batch=new BatchNode();
	
	public void iniciar(CeldaModel celda){
		super.iniciar(celda);
		System.out.println("batchingggggg.............................."+batch.getChildren().size());
		batch.batch();
                System.out.println("batchingggggg..............................!!!!!!!!!");
		celda.attachChild(batch);
                
                System.out.println("-+++++.............................."+batch.getChildren().size());
	}
        
        public void setOcuparEntry(MapEntryDebug e){
           batch.attachChild(e.caja);
           batch.batch(); 
        }
        
        public void ocupar(int x, int z){
            MapEntryDebug e=getMapEntry(x, z);
            
            e.ocupar();
            batch.attachChild(e.caja);
            batch.batch();
        }
	
	@Override
	protected MapEntryDebug getNewMapEntry(int x, int y) {
		MapEntryDebug entry= new MapEntryDebug();
		entry.crear(x, y);
                //entry.ocupar();
                if(entry.isOcupado())
                    batch.attachChild(entry.caja);
		return entry;
	}

}
