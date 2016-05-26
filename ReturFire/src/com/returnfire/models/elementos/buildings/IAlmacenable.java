package com.returnfire.models.elementos.buildings;

import com.returnfire.dao.elementos.buildings.EdificioAlmacenDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.elementos.vehicles.VehiculoModel;

public interface IAlmacenable<D extends EdificioAlmacenDAO> {
	 public boolean isVehiculoEnZona(VehiculoModel v);
	 public void onVehiculoEnZona(VehiculoModel v);
	 public void onVehiculoFueraZona(VehiculoModel v);
         public D getAlmacenDAO();
         public CeldaModel getAlmacenCelda();
}
