package com.returnfire.models.elementos.buildings;

import com.returnfire.models.elementos.vehicles.VehiculoModel;

public interface IAlmacenable {
	 public boolean isVehiculoEnZona(VehiculoModel v);
	 public void onVehiculoEnZona(VehiculoModel v);
	 public void onVehiculoFueraZona(VehiculoModel v);
}
