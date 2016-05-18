/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos.buildings.impl;

import com.entity.anot.Conditional;
import com.entity.anot.Entity;
import com.entity.anot.entities.ModelEntity;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.math.Vector3f;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.models.elementos.buildings.BaseVehiculosModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;
import com.returnfire.models.elementos.buildings.nodos.BaseTierraPequeNode;

/**
 *
 * @author Edu
 */

@ModelEntity
public class BaseTierraPequeModel extends BaseVehiculosModel<EdificioVehiculosDAO, BaseTierraPequeNode>{
	
	@Entity
	public BaseTierraPequeNode building;
	
	//@Entity(substituteNode="zonaA")
	@Conditional(method="injectZona", includeFieldName=true)
	public BuildingExtension zonaA;
	
	//@Entity(conditional="injectZona", conditionalIncludeFieldName=true, substituteNode="zonaB")
	@Conditional(method="injectZona", includeFieldName=true)
	public BuildingExtension zonaB;
	
	
    @Override
    public CollisionShape getColisionShape() {
            // TODO Auto-generated method stub
            return new BoxCollisionShape(new Vector3f(11,0.3f,11));
    }

    @Override
    public boolean onEliminar(Vector3f vel) {
            // TODO Auto-generated method stub
            return false;
    }

	@Override
	public CollisionShape getParkingZoneColisionShape() {
		// TODO Auto-generated method stub
		return new SphereCollisionShape(5f);
	}

	@Override
	public BaseTierraPequeNode getNodo() {
		return building;
	}



}