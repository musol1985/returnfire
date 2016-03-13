package com.returnfire.dao;

import com.entity.network.core.dao.NetWorldCellDAO;
import com.entity.utils.Utils;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.estaticos.ArbolDAO;
import com.returnfire.models.MundoModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Serializable
public class CeldaDAO extends NetWorldCellDAO{
        
        private List<ArbolDAO> arboles;
        
        public void generar(Random rnd, MundoModel mundo){
            if(!mundo.dao.isSide(getId())){
                arboles=new ArrayList<ArbolDAO>();
                int numArboles=Utils.getRandomBetween(rnd, 10,30);
                for(int i=0;i<numArboles;i++){
                    int x=Utils.getRandomBetween(rnd, 0, MundoModel.CELL_SIZE-1);
                    int z=Utils.getRandomBetween(rnd, 0, MundoModel.CELL_SIZE-1);
                    arboles.add(ArbolDAO.getNew(new Vector3f(x,0,z)));
                }
            }
        }

    public List<ArbolDAO> getArboles() {
        return arboles;
    }
    
    public boolean hasArboles(){
        return arboles!=null;
    }
        
}
