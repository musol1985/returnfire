package com.returnfire.map;

import com.jme3.scene.BatchNode;
import com.returnfire.models.CeldaModel;


public class CeldaMapDebug extends CeldaMap<MapEntryDebug>{
	private BatchNode batch=new BatchNode();
	
	public void iniciar(CeldaModel celda){
		super.iniciar(celda);
		
		batch.batch();
		celda.attachChild(batch);
	}
	
	@Override
	protected MapEntryDebug getNewMapEntry(int x, int y) {
		MapEntryDebug entry= new MapEntryDebug();
		entry.crear(x, y);
		batch.attachChild(entry.caja);
		return entry;
	}

}
