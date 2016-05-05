/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui;

import com.entity.anot.Entity;
import com.entity.modules.gui.anot.ScreenGUI;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Screen;
import com.entity.modules.gui.items.Sprite;
import com.returnfire.GameContext;
import com.returnfire.client.gui.buttons.Button;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.buildings.BuildModel;
import com.returnfire.models.elementos.buildings.nodos.BaseTierraPequeNode;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

/**
 *
 * @author Edu
 */
@ScreenGUI
public class BuildGUI extends Screen{
	@SpriteGUI(name="btnBTP", onLeftClick="clickBaseTierra", texture = "Interface/toolRelleno.png", position = {100,100})
	public Button btnBaseTierra;
	


	public void build(Class<? extends BuildNode> node)throws Exception{
		GameContext.getMundo().buildDrag.setEdificio(BaseTierraPequeNode.class);
		drag(GameContext.getMundo().buildDrag, CeldaModel.class, GameContext.getMundo());
	}		
	
	public boolean clickBaseTierra(Sprite btn, ClickEvent event)throws Exception{		
		if(event.value)
			build(BaseTierraPequeNode.class);

		return true;
	}
}
