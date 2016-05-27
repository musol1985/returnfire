/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import com.entity.modules.gui.anot.ButtonGUI;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.anot.TextGUI;
import com.entity.modules.gui.events.ClickEvent;
import com.entity.modules.gui.items.Button;
import com.entity.modules.gui.items.Sprite;
import com.entity.modules.gui.items.Text;
import com.returnfire.client.gui.controls.Row;
import com.returnfire.dao.elementos.RecursoDAO;
import com.returnfire.dao.elementos.RecursoDAO.RECURSOS;

/**
 *
 * @author Edu
 */
public class RecursosRow extends Row<RecursosWindow>{    
    @SpriteGUI(name="rowIco", position={10,0}, align = SpriteGUI.ALIGN.CENTER_Y)
    public Sprite recursoIco;
    
    @ButtonGUI(sprite=@SpriteGUI(name="btnAdd", position={288,10}, onLeftClick = "onBtnAddEdificio", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/add.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddEdificio;
    
    @ButtonGUI(sprite=@SpriteGUI(name="btnAddAll", position={357,10}, onLeftClick = "onBtnAddAllEdificio", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/addAll.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddAllEdificio; 
    
    @ButtonGUI(sprite=@SpriteGUI(name="btnRemove", position={219,10}, onLeftClick = "onBtnAddVehiculo", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/remove.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddVehiculo;
    
    @ButtonGUI(sprite=@SpriteGUI(name="btnRemoveAll", position={150,10}, onLeftClick = "onBtnAddAllVehiculo", align = SpriteGUI.ALIGN.CENTER_Y),
			icon="Interface/removeAll.png", imgBack="Interface/btnBack.png", imgHover="Interface/btnHover.png", imgDisabled="Interface/btnDisabled.png")
    public Button btnAddAllVehiculo;    
    
    @TextGUI(position={80,10 }, font="Interface/fonts/Texto34.fnt", name="txtRecVehiculo", align = SpriteGUI.ALIGN.CENTER_Y)
    public Text txtRecVehiculo;    
    @TextGUI(position={426,10 }, font="Interface/fonts/Texto34.fnt", name="txtRecEdificio", align = SpriteGUI.ALIGN.CENTER_Y)
    public Text txtRecEdificio;    
    

    private RECURSOS tipoRecurso;
    private String recursosMaximos;

    public void instance(int index, RECURSOS tipoRecurso, int contenedoresVehiculo, int cantidadEdificio, int maxTotales) {
        super.instance(index); 
        this.tipoRecurso=tipoRecurso;

        recursoIco.setImage("Interface/icons/"+tipoRecurso.name()+".png", true);
        recursosMaximos="/"+maxTotales;

        
        
        setText(contenedoresVehiculo, cantidadEdificio);
    }
    
    public void puedeAlmacenar(boolean vehiculoPuedeAlmacenar, boolean edificioPuedeAlmacenar){
    	btnAddAllVehiculo.setEnabled(vehiculoPuedeAlmacenar);
        btnAddVehiculo.setEnabled(vehiculoPuedeAlmacenar);
        btnAddEdificio.setEnabled(edificioPuedeAlmacenar);
        btnAddAllEdificio.setEnabled(edificioPuedeAlmacenar);
    }

    
    public boolean onBtnAddEdificio(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAddToEdificio(false, this);

        return true;
    }
    
    public boolean onBtnAddAllEdificio(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAddToEdificio(true, this);

        return true;
    }
    
    public boolean onBtnAddVehiculo(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAddToVehiculo(false, this);

        return true;
    }
    
    public boolean onBtnAddAllVehiculo(Sprite btn, ClickEvent event)throws Exception{		
        if(event.value)
            getParentModel().onAddToVehiculo(true, this);

        return true;
    }


    public void setText(int contenedoresVehiculo, int recursosEdificio){
    	setText(String.valueOf(contenedoresVehiculo), String.valueOf(recursosEdificio));
    }

 
    public void setText(String contenedoresVehiculo, String recursosEdificio){
    	txtRecVehiculo.setText(contenedoresVehiculo);
    	txtRecEdificio.setText(recursosEdificio+recursosMaximos);
    }


	public RECURSOS getTipoRecurso() {
		return tipoRecurso;
	}
    
    
}
