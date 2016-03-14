/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models;

import com.entity.adapters.ScrollCameraAdapter;
import com.entity.core.EntityManager;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CompoundCollisionShape;
import com.jme3.bullet.control.VehicleControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Cylinder;

/**
 *
 * @author Edu
 */
public class VehiculoModel {
    public VehicleControl vehicle;
    private final float accelerationForce = 1000.0f;
    private final float brakeForce = 100.0f;
    private float steeringValue = 0;
    private float accelerationValue = 0;
    private Vector3f jumpForce = new Vector3f(0, 3000, 0);
    public Node vehicleNode;
    
    public VehiculoModel(Vector3f pos){
        Material mat = new Material(EntityManager.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", ColorRGBA.Red);
        
        CompoundCollisionShape compoundShape = new CompoundCollisionShape();
        BoxCollisionShape box = new BoxCollisionShape(new Vector3f(1.5f, 1f, 3f));
        compoundShape.addChildShape(box, new Vector3f(0, 2, 0));

        //create vehicle node
        vehicleNode=(Node)EntityManager.getAssetManager().loadModel("Models/vehicles/hammer.j3o");
        vehicle = new VehicleControl(compoundShape, 400);
        //vehicleNode.getChild(0).rotate(0, FastMath.PI, 0);
        vehicleNode.addControl(vehicle);
        
        float stiffness = 60.0f;//200=f1 car
        float compValue = .3f; //(should be lower than damp)
        float dampValue = .4f;
        vehicle.setSuspensionCompression(compValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionDamping(dampValue * 2.0f * FastMath.sqrt(stiffness));
        vehicle.setSuspensionStiffness(stiffness);
        vehicle.setMaxSuspensionForce(10000.0f);

        //Create four wheels and add them at their locations
        Vector3f wheelDirection = new Vector3f(0, -1, 0); // was 0, -1, 0
        Vector3f wheelAxle = new Vector3f(-1, 0, 0); // was -1, 0, 0
        float radius = 0.5f;
        float restLength = 0.3f;
        float yOff = 1f;
        float xOff = 1f;
        float zOff = 2f;


        Node node1 = (Node)vehicleNode.getChild("wheel_fl");
        vehicle.addWheel(node1, new Vector3f(-node1.getLocalTranslation().x,yOff,-node1.getLocalTranslation().y),
                wheelDirection, wheelAxle, restLength, radius, true);

        Node node2 = (Node)vehicleNode.getChild("wheel_fr");
        vehicle.addWheel(node2, new Vector3f(-node2.getLocalTranslation().x,yOff,-node1.getLocalTranslation().y),
                wheelDirection, wheelAxle, restLength, radius, true);

        Node node3 = (Node)vehicleNode.getChild("wheel_rl");
        vehicle.addWheel(node3, new Vector3f(-node3.getLocalTranslation().x,yOff,-node3.getLocalTranslation().y),
                wheelDirection, wheelAxle, restLength, radius, false);

        Node node4 = (Node)vehicleNode.getChild("wheel_rr");
        vehicle.addWheel(node4, new Vector3f(-node4.getLocalTranslation().x,yOff,-node4.getLocalTranslation().y),
                wheelDirection, wheelAxle, restLength, radius, false);
        
        vehicle.setPhysicsLocation(pos.add(0,50,0));
    }
    
    public void attach(MundoModel mundo){
        mundo.attachChild(vehicleNode);
        EntityManager.getGame().getPhysics().add(vehicle);
    }
    
    public void attachCamera(ScrollCameraAdapter cam){
        vehicleNode.attachChild(cam);
    }
    
    public void onLeft(boolean value){
        if (value) {
                steeringValue += .5f;
            } else {
                steeringValue += -.5f;
            }
            vehicle.steer(steeringValue);
    }
    
    public void onRight(boolean value){
                    if (value) {
                steeringValue += -.5f;
            } else {
                steeringValue += .5f;
            }
            vehicle.steer(steeringValue);
    }
    
    public void onAccelerate(boolean value){
                    if (value) {
                accelerationValue += accelerationForce;
            } else {
                accelerationValue -= accelerationForce;
            }
            vehicle.accelerate(accelerationValue);
    }
    
    public void onBrake(boolean value){
                    if (value) {
                vehicle.brake(brakeForce);
            } else {
                vehicle.brake(0f);
            }
    }
}
