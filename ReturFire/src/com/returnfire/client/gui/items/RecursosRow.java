/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import java.util.List;

import com.entity.modules.gui.anot.ButtonGUI;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.anot.TextGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Button;
import com.entity.modules.gui.items.Sprite;
import com.entity.modules.gui.items.Text;
import com.returnfire.client.gui.controls.Row;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.RecursoDAO;

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
    @ButtonGUI(sprite=@SpriteGUI(name="btnAddAll", position={225,10}, onLeftClick = "onBtnAddAll", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/addAll.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddAll;    
    
    @TextGUI(position={80,10 }, font="Interface/fonts/Texto34.fnt", name="txtRecVehiculo", align = SpriteGUI.ALIGN.CENTER_Y)
    public Text txtRecVehiculo;    
    @TextGUI(position={300,10 }, font="Interface/fonts/Texto34.fnt", name="txtRecEdificio", align = SpriteGUI.ALIGN.CENTER_Y)
    public Text txtRecEdificio;    
    

    private RecursoDAO rEdificio;
    private String recursosNecesariosTotales;

    public void instance(int index, int contenedoresVehiculo, RecursoDAO rEdificio, int maxTotales) {
        super.instance(index); 
        this.rEdificio=rEdificio;

        recursoIco.setImage("Interface/icons/"+rEdificio.tipo.name()+".png", true);
        recursosNecesariosTotales="/"+maxTotales;
        
        setText(contenedoresVehiculo, rEdificio.cantidad);
    }

    
    public boolean onBtnAdd(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAdd(false, this);

        return true;
    }
    
    public boolean onBtnAddAll(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAdd(true, this);

        return true;
    }


    public void setText(int contenedoresVehiculo, int recursosEdificio){
    	setText(String.valueOf(contenedoresVehiculo), String.valueOf(recursosEdificio));
    }
    
    public RecursoDAO getRecursoEdificio(){
    	return rEdificio;
    }

 
    public void setText(String contenedoresVehiculo, String recursosEdificio){
    	txtRecVehiculo.setText(contenedoresVehiculo);
    	txtRecEdificio.setText(recursosEdificio+recursosNecesariosTotales);
    }
}
