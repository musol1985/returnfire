package com.returnfire.models;

import com.entity.network.core.models.NetPlayer;
import com.returnfire.dao.JugadorDAO;

public class JugadorModel extends NetPlayer<JugadorDAO>{
    private VehiculoModel vehiculo;
    
    public boolean hasVehicle(){
        return vehiculo!=null;
    }

    public VehiculoModel getVehiculo() {
        return vehiculo;
    }
    
    public void cargarVehiculo(){
        vehiculo=new VehiculoModel(dao.getPosition());
    }
}
