package com.returnfire.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.network.core.dao.NetWorldCellDAO;
import com.entity.utils.Utils;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.MundoModel;

@Serializable
public class CeldaDAO extends NetWorldCellDAO{
        public static final int OFFSET_RANDOM_ELEMENT=50;       
        
        private List<EstaticoDAO> estaticos;
        private List<EdificioDAO> edificios;
        
        public void generar(Random rnd, MundoModel mundo){
            if(!mundo.dao.isSide(getId())){
                estaticos=new ArrayList<EstaticoDAO>();
                
                int elementos=EstaticoDAO.ELEMENTOS_ESTATICOS.values().length;
                for(int x=0;x<CeldaModel.CELL_SIZE;x+=10){
                    for(int z=0;z<CeldaModel.CELL_SIZE;z+=10){
                        int i=rnd.nextInt(elementos+OFFSET_RANDOM_ELEMENT);
                        if(i<elementos){
                            float ang=Utils.getRandomBetween(rnd, 0, FastMath.TWO_PI);
                            if(i==EstaticoDAO.ELEMENTOS_ESTATICOS.ARBOL.ordinal()){
                                estaticos.add(ArbolDAO.getNew(new Vector3f(x,0,z), ang));
                            }else if(i==EstaticoDAO.ELEMENTOS_ESTATICOS.ROCA.ordinal()){                                
                                estaticos.add(RockDAO.getNew(new Vector3f(x,0,z),ang, rnd));
                            }
                        }
                    } 
                }
                
                edificios=new ArrayList<EdificioDAO>();
            }
        }


    public List<EstaticoDAO> getEstaticos() {
        return estaticos;
    }

    public void setEstaticos(List<EstaticoDAO> estaticos) {
        this.estaticos = estaticos;
    }

    public boolean hasEstaticos(){
        return estaticos!=null;
    }


	public List<EdificioDAO> getEdificios() {
		return edificios;
	}
        
    public boolean hasEdificios(){
        return edificios!=null;
    }
    
}
