package com.returnfire.models.elementos;

import com.entity.anot.entities.ModelEntity;
import com.returnfire.models.*;
import com.entity.core.EntityManager;
import com.entity.core.IBuilder;
import com.entity.network.core.items.IWorldInGameScene;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;

@ModelEntity(asset = "Models/pino.j3o")
public class ArbolModel extends EstaticoModel<ArbolDAO>{
    

    @Override
    public void onInstance(IBuilder builder, Object[] params) {
        super.onInstance(builder, params); 
        
        setDao((ArbolDAO)params[0]);
        
        
    }
    
    
    public MundoModel getMundo(){
        return (MundoModel)((IWorldInGameScene)EntityManager.getCurrentScene()).getWorld();
    }
}
