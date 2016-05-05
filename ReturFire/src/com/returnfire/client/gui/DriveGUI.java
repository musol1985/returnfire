/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui;

import com.entity.modules.gui.anot.ScreenGUI;
import com.entity.modules.gui.anot.SpriteGUI;
import com.entity.modules.gui.items.Screen;
import com.entity.modules.gui.items.Sprite;

/**
 *
 * @author Edu
 */
@ScreenGUI
public class DriveGUI extends Screen{
    @SpriteGUI(name = "a", texture = "Interface/toolRelleno.png", position = {100,100})
    public Sprite a;
}
