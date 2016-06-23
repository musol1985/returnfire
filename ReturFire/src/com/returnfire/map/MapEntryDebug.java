package com.returnfire.map;

import com.entity.core.EntityManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.returnfire.models.elementos.EstaticoModel;

public class MapEntryDebug extends MapEntry{
	
	public static Material AMARILLO;
	public static Material ROJO;
	public static Material VERDE;
	public static Material AZUL;
    public static Geometry geo;
	
	static{
		AMARILLO = getMaterial(ColorRGBA.Yellow);
		ROJO = getMaterial(ColorRGBA.Yellow);
		VERDE = getMaterial(ColorRGBA.Yellow);
		AZUL = getMaterial(ColorRGBA.Yellow);		
                geo=new Geometry("Box", new Box(MAP_ENTRY_SIZE/2-0.5f, 1, MAP_ENTRY_SIZE/2-0.5f));
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
	}
	
	public void setOcupadoPor(EstaticoModel elemento){
		super.setOcupadoPor(elemento);
		ocupar();
	}
        
    public void ocupar(){
        if(caja==null)
            crearCaja();
        caja.setMaterial(ROJO);
    }
    
    private void crearCaja(){
    	caja = geo.clone();
    	caja.setMaterial(VERDE);         
    	caja.move(x*(MAP_ENTRY_SIZE)+MAP_ENTRY_SIZE/2,30,y*(MAP_ENTRY_SIZE)+MAP_ENTRY_SIZE/2);
    }
}
