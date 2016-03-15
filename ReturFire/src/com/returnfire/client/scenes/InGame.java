/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.adapters.FollowCameraAdapter;
import com.entity.adapters.listeners.IFollowCameraListener;
import com.entity.anot.Entity;
import com.entity.anot.FollowCameraNode;
import com.entity.anot.Sky;
import com.entity.anot.components.input.ComposedKeyInput;
import com.entity.anot.components.input.Input;
import com.entity.anot.components.input.KeyInputMapping;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.network.core.items.InGameClientScene;
import com.entity.network.core.listeners.InGameClientMessageListener;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.KeyInput;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.MundoModel;
import com.returnfire.service.ClientMundoService;

/**
 *
 * @author Edu
 */

@ComposedKeyInput(inputs = {
    @KeyInputMapping(action = "up", keys = {KeyInput.KEY_W}),
    @KeyInputMapping(action = "down", keys = {KeyInput.KEY_S}),
    @KeyInputMapping(action = "left", keys = {KeyInput.KEY_A}),
    @KeyInputMapping(action = "right", keys = {KeyInput.KEY_D}),
    @KeyInputMapping(action = "space", keys = {KeyInput.KEY_SPACE})
})
public class InGame extends InGameClientScene<InGameClientMessageListener, MundoModel, JugadorModel,  ClientMundoService> implements IFollowCameraListener{
    @Entity
    private MundoModel world;
    
    @Entity(attach=false)
    private JugadorModel player;
    
    @Sky(texture = "Textures/sky.jpg")
    private Spatial sky;
    
    @WorldService
    private ClientMundoService service;
    
    @MessageListener
    private InGameClientMessageListener listener;
    
            
    @FollowCameraNode( listenerField="world", debug = false)
    private FollowCameraAdapter camera;

    
    @Override
    public MundoModel getWorld() {
        return world;
    }

    @Override
    public JugadorModel getPlayer() {
        return player;
    }

    @Override
    public ClientMundoService getService() {
        return service;
    }

    @Override
    public InGameClientMessageListener getListener() {
        return listener;
    }

    @Override
    public void onLoadScene() throws Exception {
        super.onLoadScene(); //To change body of generated methods, choose Tools | Templates.

        player.seleccionarVehiculo();
        camera.followTo(player.getVehiculo());
    }
    

	@Override
	public void onUpdate(FollowCameraAdapter adapter) {
		getService().updatePlayerLocation(player.getPosicion());
	}

    @Input(action = "space")
    public void space(boolean value, float tpf){
        System.out.println("UP!!!"+value);
        if(value){
          Geometry bulletg = new Geometry("bullet", new Sphere(30, 30, 1));
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        bulletg.setMaterial(mat);
        bulletg.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        bulletg.setLocalTranslation(app.getCamera().getLocation());
        SphereCollisionShape bulletCollisionShape = new SphereCollisionShape(1f);
        RigidBodyControl bulletNode = new RigidBodyControl(bulletCollisionShape, 1);
        bulletg.addControl(bulletNode);
        bulletNode.setLinearVelocity(app.getCamera().getDirection().mult(25));
        app.getRootNode().attachChild(bulletg);
        app.getPhysics().add(bulletNode);
        }
    }
    
    @Input(action = "down")
    public void down( boolean value, float tpf){
        if(player.hasVehicle()){
            player.getVehiculo().onBrake(value);
        }
    }
    
    @Input(action = "up")
    public void up( boolean value, float tpf){
        if(player.hasVehicle()){
            player.getVehiculo().onAccelerate(value);
        }
    }
    
    @Input(action = "left")
    public void left( boolean value, float tpf){
        if(player.hasVehicle()){
            player.getVehiculo().onLeft(value);
        }
    }
    
    @Input(action = "right")
    public void right( boolean value, float tpf){
        if(player.hasVehicle()){
            player.getVehiculo().onRight(value);
        }
    }

}
