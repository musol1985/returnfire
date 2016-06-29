package com.returnfire.client.gui.items;

import com.entity.adapters.listeners.ICameraUpdate;
import com.entity.adapters.listeners.IModifierOnFinish;
import com.entity.adapters.modifiers.ModifierDelay;
import com.entity.core.EntityManager;
import com.entity.core.items.Model;
import com.entity.modules.gui.anot.SpriteGUI.ALIGN;
import com.entity.modules.gui.items.Sprite;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.returnfire.client.scenes.InGame;

public class Icono extends Sprite implements ICameraUpdate, IModifierOnFinish<ModifierDelay<Icono>, Icono>{
	private Model m;
	
	public void instance(String texture) {
		super.instance("Ico"+texture, texture, ALIGN.NONE);
	}
	
	@Override
	public void onPositionUpdate(Camera c) {
		Vector3f pos=c.getScreenCoordinates(m.getWorldTranslation());            
        setPosition(pos.x, pos.y);
	}
	
	public void show(Model m){
		show(m, 0f);
	}
	
	public void show(Model m, float ms){
		try{
			attachToParent(EntityManager.getGame().getGUI().getScreen());
			((InGame)EntityManager.getCurrentScene()).addCamUpdate(this);
			if(ms>0f){
				addControl(new ModifierDelay<Icono>(ms, this));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void hide(){
		try {
			((InGame)EntityManager.getCurrentScene()).removeCamUpdate(this);    
			super.dettach();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onFinish(ModifierDelay<Icono> m, Icono model) {
		hide();
	}

}
