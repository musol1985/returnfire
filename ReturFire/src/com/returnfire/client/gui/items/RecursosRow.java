/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import com.entity.core.EntityManager;
import com.entity.modules.gui.anot.ButtonGUI;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Button;
import com.entity.modules.gui.items.Sprite;
import com.returnfire.client.gui.controls.Row;
import com.returnfire.dao.elementos.ContenedorDAO.RECURSO;
import com.returnfire.models.elementos.buildings.nodos.BaseTierraPequeNode;

/**
 *
 * @author Edu
 */
public class RecursosRow extends Row<RecursosWindow>{    
    @SpriteGUI(name="rowIco", position={10,0}, align = SpriteGUI.ALIGN.CENTER_Y)
    public Sprite recursoIco;
    
    @ButtonGUI(sprite=@SpriteGUI(name="btnAdd", position={154,10}, onLeftClick = "onBtnAdd", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/add.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAdd;
    @ButtonGUI(sprite=@SpriteGUI(name="btnAddAll", position={80,10}, onLeftClick = "onBtnAddAll", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/addAll.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddAll;    
    
    
    private RECURSO recurso;

    public void instance(int index, RECURSO recurso, int size) {
        super.instance(index); 
        this.recurso=recurso;

        recursoIco.setImage("Interface/icons/"+recurso.name()+".png", true);
    }

    
    public boolean onBtnAdd(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAdd(recurso, false);

        return true;
    }
    
    public boolean onBtnAddAll(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAdd(recurso, true);

        return true;
    }
        
}
