package com.returnfire.models.elementos.buildings;

import com.entity.anot.OnUpdate;
import com.returnfire.client.gui.items.Icono;
import com.returnfire.dao.elementos.buildings.EdificioExtractorDAO;
import com.returnfire.models.elementos.buildings.nodos.BuildNode;

public abstract class EdificioExtractorModel<T extends EdificioExtractorDAO, N extends BuildNode> extends EdificioAlmacenModel<T, N>{
	private long t;
	
	private Icono icoFull;
	
	@OnUpdate
	public void onUpdate(float tpf){
		if(System.currentTimeMillis()-t>EdificioExtractorDAO.VELOCIDAD_EXTRACCION){
			System.out.println("Produciendo!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			if(getDAO().producir()){
				t=System.currentTimeMillis();
			}		
			
			updateIcoFull();
                        
            if(getWindow()!=null){
                getWindow().actualizar();
            }
		}
	}
	
	public void updateIcoFull(){
		
		if(dao.puedeAlmacenarMas(dao.recursoProducido())){
			if(icoFull!=null ){
				if(icoFull.getParent()!=null)
					icoFull.hide();
			}
		}else{
			if(icoFull==null){
				icoFull=new Icono();
				icoFull.instance("");
			}
			if(icoFull.getParent()==null)
				icoFull.show(this);
		}
	}
}
