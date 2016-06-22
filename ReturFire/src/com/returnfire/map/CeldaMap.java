package com.returnfire.map;

import java.util.ArrayList;
import java.util.List;

import com.entity.core.items.BaseService;
import com.entity.utils.Vector2;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.models.CeldaModel;
import static com.returnfire.models.CeldaModel.CELL_SIZE;
import com.returnfire.models.elementos.EstaticoModel;

public class CeldaMap<T extends MapEntry> extends BaseService{
    public static final int MAP_SIZE=(int)CELL_SIZE/MapEntry.MAP_ENTRY_SIZE;
            
	protected CeldaModel celda;
    protected List<List<T>> map;
	
	public void iniciar(CeldaModel celda){
		this.celda=celda;
		initMap();
	}
	
	protected T getNewMapEntry(int x, int y){
		return (T)new MapEntry();
	}
	
	private void initMap(){
            System.out.println("init map....");
            map=new ArrayList<List<T>>(MAP_SIZE);
            for(int x=0;x<MAP_SIZE;x++){
        	List<T> mY=new ArrayList<T>(MAP_SIZE);
        	for(int y=0;y<MAP_SIZE;y++){
                    T me=getNewMapEntry(x, y);
        		
                    if(y>0 && x>0){
                        me.setSur(map.get(x-1).get(y));
                        me.setOeste(mY.get(y-1));
                    }
        		
                    mY.add(me);
        	}
        	map.add(mY);
            }
            System.out.println("fin map....");
        
        //Obtenemos las celdas de alrededor
        /*CeldaModel cNorte=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(0, +1));
        CeldaModel cSur=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(0, -1));
        CeldaModel cEste=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(-1, 0));
        CeldaModel cOeste=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(+1, 0));
        
        for(int i=0;i<CeldaModel.CELL_SIZE;i++){
       	 if(cNorte!=null)
       		 cNorte.getMapa().getMapEntry(i, 0).setSur(getMapEntry(i, CeldaModel.CELL_SIZE-1));
       	 if(cSur!=null)
       		 getMapEntry(i, 0).setSur(cSur.getMapa().getMapEntry(i, CeldaModel.CELL_SIZE-1));
       	 if(cEste!=null)
       		 cEste.getMapa().getMapEntry(CeldaModel.CELL_SIZE-1, i).setOeste(getMapEntry(0 ,i));
       	 if(cOeste!=null)
       		 getMapEntry(CeldaModel.CELL_SIZE-1, i).setOeste(cOeste.getMapa().getMapEntry(0 ,i));
        }*/
   }
   
   public boolean isZonaOcupada(Vector3f pReal, Vector2 size)throws Exception{
       return false;/*
   	Vector2 pos=real2Map(pReal);
   	
   	MapEntry meY=getMapEntry(pos.x, pos.z);
		MapEntry meX=meY;
		
   	for(int x=0;x<size.x;x++){
   		
   		for(int z=0;z<size.z;z++){
   			if(meY.isOcupado())
   				return true;    				
   			
   			meY=meY.getNorte();
   		}
   		
   		meX=meX.getOeste();
   		meY=meX;
   	}
   	return false;*/
   }
   

   private void addToMap(EstaticoModel<? extends EstaticoDAO, ? extends PhysicsControl> e) throws Exception{
	   	Vector2 pos=real2Map(e.getLocalTranslation());
	   	
	   	MapEntry meY=getMapEntry(pos.x, pos.z);
			MapEntry meX=meY;
			
	   	for(int x=0;x<e.getDAO().getSize().x;x++){
	   		
	   		for(int z=0;z<e.getDAO().getSize().z;z++){
	   			if(meY.isOcupado())
	   				throw new Exception("Error, imposible ocupar con "+e+" la posicion ("+pos.x+"+"+x+","+pos.z+"+"+z+") de la celda "+celda.dao.getId()+". Est� ocupado por "+meY.getElemento());
	   			meY.setOcupadoPor(e);
	   			
	   			meY=meY.getNorte();
	   			
	   		}
	   		
	   		meX=meX.getOeste();
	   		meY=meX;
	   	}
   }
   
   public T getMapEntry(int x, int z){
   		return map.get(x).get(z);
   }
   
   public Vector2 real2Map(Vector3f pos){
        return new Vector2((int)pos.x/CeldaModel.CELL_SIZE,(int)pos.z/CeldaModel.CELL_SIZE);
   }
   
   public Vector2 world2Map(Vector3f world){
       world=celda.worldToLocal(world);
       return new Vector2((int)world.x/MapEntry.MAP_ENTRY_SIZE,(int)world.z/MapEntry.MAP_ENTRY_SIZE);            
        
   }
}
