/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui;

import com.entity.anot.Entity;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Screen;
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
public class BuildGUI extends Screen{
	
	@SpriteGUI(texture="", name="btnBTP", onLeftClick="clickBaseTierra")
	public Button btnBaseTierra;
	
	@Entity
	public BuildModel buildDrag;

	public void build(Class<? extends BuildNode> node)throws Exception{
		buildDrag.setEdificio(BaseTierraPequeNode.class);
		drag(buildDrag, CeldaModel.class, GameContext.getMundo());
	}		
	
	public boolean clickBaseTierra(Button btn, ClickEvent event)throws Exception{		
		if(event.value)
			build(BaseTierraPequeNode.class);

		return true;
	}
}
