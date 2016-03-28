/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.gui;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.post.Filter;
import com.returnfire.Client;
import com.returnfire.GameContext;

import com.simsilica.lemur.Checkbox;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.RollupPanel;
import com.simsilica.lemur.TabbedPanel;
import com.simsilica.lemur.props.PropertyPanel;
import com.simsilica.lemur.style.BaseStyles;
import com.simsilica.lemur.style.ElementId;



/**
 *
 * @author Edu
 */
public class DebugGUI  extends AbstractAppState{
    public Client app;
    
    private Container window;

  
    @Override
    public void initialize(AppStateManager stateManager, Application a) {
        this.app=(Client)a;
        BaseStyles.loadGlassStyle();
        window = new Container("glass");
        window.addChild(new Label("Debug", new ElementId("title"), "glass"));
        
        crearBloom(GameContext.getMundo().bloom);

        window.setLocalTranslation(5, 600, 0);
        app.getGuiNode().attachChild(window);
    }
    

    private void crearBloom(Filter f){
        TabbedPanel waterTabs = new TabbedPanel("glass");
        RollupPanel rollup = window.addChild(new RollupPanel("Bloom", waterTabs, "glass"));
        rollup.setOpen(true);
        PropertyPanel properties = waterTabs.addTab("Visualization", new PropertyPanel("glass"));
        properties.setEnabledProperty(f, "enabled");        
        rollup.getTitleContainer().addChild(new Checkbox("", properties.getEnabledModel(), "glass"));
        properties.addFloatProperty("DownSamplingFactor", f, "downSamplingFactor", 0, 2, 0.001f);
        properties.addFloatProperty("blurScale", f, "blurScale", 0.0f, 10, 0.01f);
        properties.addFloatProperty("exposurePower", f, "exposurePower", 0.0f, 10, 0.01f);
        properties.addFloatProperty("ExposureCutOff", f, "exposureCutOff", 0.0f, 10, 0.01f);
        properties.addFloatProperty("BloomIntensity", f, "bloomIntensity", 0.0f, 10, 0.01f);    
    }
    
}
