/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui;

import com.entity.modules.gui.anot.ButtonGUI;
import com.entity.modules.gui.anot.ScreenEntity;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Button;
import com.entity.modules.gui.items.Screen;
import com.entity.modules.gui.items.Sprite;
import com.returnfire.GameContext;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.buildings.nodos.BaseTierraPequeNode;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;
import com.returnfire.models.elementos.buildings.nodos.MolinoEolicoNode;

/**
 *
 * @author Edu
 */
@ScreenEntity
public class BuildGUI extends Screen{
	@ButtonGUI(sprite=@SpriteGUI(name="btnRecursos", onLeftClick="clickRecursos", position={100,300}),
			icon="Interface/icons/recursos.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
	public Button btnRecursos;
	
	
	@ButtonGUI(sprite=@SpriteGUI(name="btnBT", onLeftClick="clickBaseTierra", position={168,300}, attach=false),
			icon="Interface/icons/baseTierra.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
	public Button btnBaseTierra;
	
	@ButtonGUI(sprite=@SpriteGUI(name="btnBT", onLeftClick="clickMolinoEolico", position={236,300}, attach=false),
			icon="Interface/icons/molinoEolico.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
	public Button btnMolinoEolico;

	public void build(Class<? extends BuildNode> node)throws Exception{
		GameContext.getMundo().buildDrag.setEdificio(node);
		drag(GameContext.getMundo().buildDrag, CeldaModel.class, GameContext.getMundo());
		
		btnBaseTierra.dettach();
		btnMolinoEolico.dettach();
	}		
	
	public boolean clickBaseTierra(Sprite btn, ClickEvent event)throws Exception{		
		if(event.value)
			build(BaseTierraPequeNode.class);

		return true;
	}
	
	public boolean clickMolinoEolico(Sprite btn, ClickEvent event)throws Exception{		
		if(event.value)
			build(MolinoEolicoNode.class);

		return true;
	}
	
	public boolean clickRecursos(Sprite btn, ClickEvent event)throws Exception{
		if(event.value){
			btnBaseTierra.attachToParent(this);
			btnMolinoEolico.attachToParent(this);
		}
			
		return true;
	}
}
