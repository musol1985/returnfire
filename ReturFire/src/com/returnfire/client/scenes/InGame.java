/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.client.scenes;

import com.entity.adapters.FollowCameraAdapter;
import com.entity.adapters.ScrollCameraAdapter;
import com.entity.adapters.listeners.ICameraUpdate;
import com.entity.adapters.listeners.IFollowCameraListener;
import com.entity.anot.Entity;
import com.entity.anot.FollowCameraNode;
import com.entity.anot.ScrollCameraNode;
import com.entity.anot.Sky;
import com.entity.anot.Task;
import com.entity.anot.components.input.ComposedKeyInput;
import com.entity.anot.components.input.Input;
import com.entity.anot.components.input.KeyInputMapping;
import com.entity.anot.network.ActivateNetSync;
import com.entity.anot.network.MessageListener;
import com.entity.anot.network.WorldService;
import com.entity.core.EntityManager;
import com.entity.modules.gui.anot.ScreenGUI;
import com.entity.modules.gui.items.Sprite;
import com.entity.network.core.items.InGameClientScene;
import com.entity.network.core.tasks.NetWorldPersistTask;
import com.jme3.input.KeyInput;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.returnfire.client.gui.BuildGUI;
import com.returnfire.client.gui.DriveGUI;
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
    @KeyInputMapping(action = "space", keys = {KeyInput.KEY_SPACE}),
    @KeyInputMapping(action = "park", keys = {KeyInput.KEY_P})
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
            
    @FollowCameraNode(debug = false, attach=false)
    private FollowCameraAdapter followCam;
    
    @ScrollCameraNode(attach = false)
    private ScrollCameraAdapter scrollCam;
    
    @Task(period=30)
    public NetWorldPersistTask saveTask;

    @ScreenGUI(attach = true)
    public BuildGUI driveGUI;
    
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
		player.setVehiculoInicial();
        
		followCam.setListener(new IFollowCameraListener() {
            @Override
            public void onUpdate(FollowCameraAdapter adapter) {
                getService().updatePlayerLocation(player.getPosicion());
                followCam.setLocalTranslation(player.getPosicion());
            }
        });
                followCam.attachToParent(world);
		if(!player.hasVehicle()){
			setScrollMode();
		}
	}
	
	public void setFollowMode()throws Exception{
		if(scrollCam.isAttached()){
			scrollCam.dettach();
		
                    followCam.attachToParent(world);
                }
	}
	
	public void setScrollMode()throws Exception{            
            if(followCam.isAttached()){
                Vector3f pos=followCam.getWorldTranslation();
                followCam.dettach();
                scrollCam.attachToParent(world);
                scrollCam.setLocalTranslation(pos);
            }		            
	}
        
        public void addCamUpdate(ICameraUpdate update){
            followCam.addUpdate(update);
        }
        
        public void removeCamUpdate(ICameraUpdate update){
            followCam.removeUpdate(update);
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
    	 if( player.hasVehicle()){
             player.getVehiculo().onAccion(value);
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
    
    @Input(action = "park")
    public void park( boolean value, float tpf)throws Exception{
    	if(player.hasVehicle())
    		player.seleccionarSinVehiculo();
        
        setScrollMode();
    }

	@Override
	public NetWorldPersistTask getPersistTask() {
		return saveTask;
	}


}
