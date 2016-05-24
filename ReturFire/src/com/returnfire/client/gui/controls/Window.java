/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui.controls;

import java.util.HashMap;

import com.entity.core.EntityManager;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.anot.SpriteGUI.ALIGN;
import com.entity.modules.gui.items.Sprite;
import com.jme3.math.Vector3f;

/**
 *
 * @author Edu
 */
public class Window<R extends Row> extends Sprite{
    @SpriteGUI(name="windowHead", position = {0,0}, texture = "Interface/windowHead.png")
    public Sprite head;
        
    public HashMap<String, R> rows=new HashMap<String, R>();
    
    public void instance(String name, Vector3f pos){
        pos=EntityManager.getGame().getCamera().getScreenCoordinates(pos);
        instance(name,(int)pos.x,(int)pos.y);
    }
    
    public void instance(String name, int x, int y){
        super.instance(name, "Interface/windowFoot.png", ALIGN.NONE);
        try{
            attachToParent(EntityManager.getGame().getGUI().getScreen());
            setPosition(x, y);
            head.setPosition(0, getHeight());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void addRow(String rowId, R row){
        try{
            row.attachToParent(this);
            row.setPosition(0, head.getHeight()+row.getHeight()*rows.size());
            rows.put(rowId, row);
            head.setPosition(0, head.getHeight()+row.getHeight()*rows.size());            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public R getRowById(String id){
    	return (R)rows.get(id);
    }
}
