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
import com.entity.anot.Task;
import com.entity.anot.components.input.ComposedKeyInput;
import com.entity.anot.components.input.Input;
import com.entity.anot.components.input.KeyInputMapping;
import com.entity.anot.network.ActivateNetSync;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.core.EntityManager;
import com.entity.network.core.items.InGameClientScene;
import com.entity.network.core.tasks.NetWorldPersistTask;
import com.jme3.input.KeyInput;
import com.jme3.scene.Spatial;
import com.returnfire.client.gui.DebugGUI;
import com.returnfire.client.listeners.InGameClientListener;
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
@ActivateNetSync
public class InGame extends InGameClientScene<InGameClientListener, MundoModel, JugadorModel,  ClientMundoService>{
    @Entity
    public MundoModel world;
    
    @Entity(attach=false)
    private JugadorModel player;
    
    @Sky(texture = "Textures/sky.jpg")
    private Spatial sky;
    
    @WorldService
    private ClientMundoService service;
    
    @MessageListener
    private InGameClientListener listener;
    
            
    @FollowCameraNode(debug = false)
    private FollowCameraAdapter camera;
    
	@Task(period=30)
	public NetWorldPersistTask saveTask;

    
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
    public InGameClientListener getListener() {
        return listener;
    }

	@Override
	public void onLoadPlayer()throws Exception {
          // EntityManager.getGame().getStateManager().attach(new DebugGUI());
		player.seleccionarVehiculo();
        camera.attachToParent(world);
        //camera.followTo(player.getVehiculo());
        camera.setListener(new IFollowCameraListener() {
            @Override
            public void onUpdate(FollowCameraAdapter adapter) {
                getService().updatePlayerLocation(player.getPosicion());
                camera.setLocalTranslation(player.getPosicion());
            }
        });
	}

	@Override
	public void onLoadRemotePlayers() throws Exception{
		world.cargarJugadores();
	}

    @Input(action = "space")
    public void space(boolean value, float tpf){
        /*System.out.println("UP!!!"+value);
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
        }*/
    	 if(value && player.hasVehicle()){
             player.getVehiculo().disparar();
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

	@Override
	public NetWorldPersistTask getPersistTask() {
		return saveTask;
	}


}
