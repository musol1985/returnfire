package com.returnfire.map;

import static com.returnfire.models.CeldaModel.CELL_SIZE;

import java.util.ArrayList;
import java.util.List;

import com.entity.core.items.BaseService;
import com.entity.utils.Vector2;
import com.jme3.bullet.control.PhysicsControl;
import com.jme3.math.Vector3f;
import com.returnfire.GameContext;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.models.CeldaModel;
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
        		
                    if(y>0){
                        me.setSur(mY.get(y-1));                        
                    }
                    if(x>0){
                        me.setEste(map.get(x-1).get(y));
                    }
        		
                    mY.add(me);//pos=y
        	}
        	map.add(mY);//pos=x
            }
            System.out.println("fin map....");
        
        //Obtenemos las celdas de alrededor
        CeldaModel cNorte=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(0, +1));
        CeldaModel cSur=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(0, -1));
        CeldaModel cEste=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(-1, 0));
        CeldaModel cOeste=GameContext.getService().getCellFromCache(celda.dao.getId().id.add(+1, 0));
        
        for(int i=0;i<MAP_SIZE;i++){
       	 if(cNorte!=null)
       		 cNorte.getMapa().getMapEntry(i, 0).setSur(getMapEntry(i, MAP_SIZE-1));
       	 if(cSur!=null)
       		 getMapEntry(i, 0).setSur(cSur.getMapa().getMapEntry(i, MAP_SIZE-1));
       	 if(cEste!=null)
       		 cEste.getMapa().getMapEntry(MAP_SIZE-1, i).setOeste(getMapEntry(0 ,i));
       	 if(cOeste!=null)
       		 getMapEntry(MAP_SIZE-1, i).setOeste(cOeste.getMapa().getMapEntry(0 ,i));
        }
   }
   
   public boolean isZonaOcupada(Vector3f pReal, Vector2 size)throws Exception{
       return false;
   }
   

   public void addToMap(EstaticoModel<? extends EstaticoDAO, ? extends PhysicsControl> e) throws Exception{
	   	Vector2 pos=cell2Map(e.getLocalTranslation());
                
                int sX=((int)e.getDAO().getSize().x/2);
                int sZ=((int)e.getDAO().getSize().z/2);

	   	T meY=getMapEntry(pos.x-sX, pos.z-sZ);
		T meX=meY;                                
			
	   	for(int x=-sX;x<sX;x++){
	   		                        
	   		for(int z=-sZ;z<sZ;z++){
                            if(meY!=null){                            
	   			/*if(meY.isOcupado())
	   				throw new Exception("Error, imposible ocupar con "+e+" la posicion ("+pos.x+"+"+x+","+pos.z+"+"+z+") de la celda "+celda.dao.getId()+". Est� ocupado por "+meY.getElemento());*/
	   			meY.setOcupadoPor(e);
                                setOcuparEntry(meY);
	   			
	   			meY=(T)meY.getNorte();
                            }
	   		}
	   		
	   		meX=(T)meX.getOeste();
	   		meY=meX;
	   	}
   }
   
   public T getMapEntry(int x, int z){
   	return map.get(x).get(z);
   }
   
   public Vector2 world2Map(Vector3f world){
       world=celda.worldToLocal(world);
       return new Vector2((int)world.x/MapEntry.MAP_ENTRY_SIZE,(int)world.z/MapEntry.MAP_ENTRY_SIZE);            
        
   }
   
   public Vector2 cell2Map(Vector3f world){
       return new Vector2((int)world.x/MapEntry.MAP_ENTRY_SIZE,(int)world.z/MapEntry.MAP_ENTRY_SIZE);                
   }
   
    public void setOcuparEntry(T e){

    }
}
