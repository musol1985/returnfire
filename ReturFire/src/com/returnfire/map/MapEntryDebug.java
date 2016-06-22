package com.returnfire.map;

import com.entity.core.EntityManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.returnfire.models.CeldaModel;

public class MapEntryDebug extends MapEntry{
	
	public static Material AMARILLO;
	public static Material ROJO;
	public static Material VERDE;
	public static Material AZUL;

	
	static{
		AMARILLO = getMaterial(ColorRGBA.Yellow);
		ROJO = getMaterial(ColorRGBA.Yellow);
		VERDE = getMaterial(ColorRGBA.Yellow);
		AZUL = getMaterial(ColorRGBA.Yellow);		
	}
	
	private static Material getMaterial(ColorRGBA color){
		Material m= new Material(EntityManager.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
		m.setColor("Color", color);
		return m;
	}

	
	
	public Geometry caja;
	public int x;
	public int y;
	
	public void crear(int x, int y){
		this.x=x;
		this.y=y;
		
		caja = new Geometry("Box", new Box(1, 1, 1));
		caja.setMaterial(VERDE);         
		caja.move(x,0,y);

	}
}
