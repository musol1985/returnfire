package com.returnfire.models.elementos.buildings.ext;

import com.entity.anot.components.model.PhysicsBodyComponent;
import com.entity.core.IBuilder;
import com.entity.core.items.Model;
import com.jme3.bullet.control.RigidBodyControl;
import com.returnfire.dao.elementos.buildings.ExtensionDAO;

public abstract class BuildingExtension extends Model{
	//@PhysicsBodyComponent(nodeName="extension")
	public RigidBodyControl body;
	
	public ExtensionDAO dao;

	@Override
	public void onInstance(IBuilder builder, Object[] params)  throws Exception{
		if(params!=null && params.length>0)
			dao=(ExtensionDAO)params[0];
		
		super.onInstance(builder, params);
	}
	
	
}
