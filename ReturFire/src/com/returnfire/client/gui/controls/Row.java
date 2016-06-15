/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.controls;

import com.entity.modules.gui.anot.SpriteGUI.ALIGN;
import com.entity.modules.gui.items.Sprite2Patch;

/**
 *
 * @author Edu
 */
public class Row<W extends Window> extends Sprite2Patch<W>{
 
    public void instance(int index){
        super.instance("windowRow"+index, "Interface/winRow.png", ALIGN.NONE, 10);
    }
    
}
