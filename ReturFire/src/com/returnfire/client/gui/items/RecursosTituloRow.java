/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.items;

import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.anot.TextGUI;
import com.entity.modules.gui.items.Sprite;
import com.entity.modules.gui.items.Text;
import com.returnfire.client.gui.controls.Row;
import com.returnfire.dao.elementos.VehiculoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;

/**
 *
 * @author Edu
 */
public class RecursosTituloRow extends Row<RecursosWindow>{    
    @SpriteGUI(name="vehiculoIco", position={10,0}, align = SpriteGUI.ALIGN.CENTER_Y)
    public Sprite vehiculoIco;
    
    @SpriteGUI(name="edificioIco", position={RecursosWindow.WIDTH-58,0}, align = SpriteGUI.ALIGN.CENTER_Y)
    public Sprite edificioIco;
    
    @TextGUI(position={60,10 }, font="Interface/fonts/Texto34.fnt", name="txtTitulo", align = SpriteGUI.ALIGN.CENTER_XY)
    public Text txtTitulo;    


    public void instance(int index, VehiculoDAO vehiculo, EdificioDAO edificio) {
        super.instance(index); 


        edificioIco.setImage("Interface/icons/"+edificio.getICO(), true);
        vehiculoIco.setImage("Interface/icons/camion.png", true);

        txtTitulo.setText(edificio.getNombre());
        txtTitulo.centerInParent();
    }
    
}
