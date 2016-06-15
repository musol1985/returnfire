package com.returnfire.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.entity.network.core.dao.NetWorldCellDAO;
import com.entity.utils.Utils;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.EstaticoDAO;
import com.returnfire.dao.elementos.buildings.EdificioDAO;
import com.returnfire.dao.elementos.environment.RecursoNaturalDAO;
import com.returnfire.dao.elementos.environment.impl.ArbolDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoHierroDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;
import com.returnfire.dao.elementos.environment.impl.RockDAO;
import com.returnfire.models.CeldaModel;
import com.returnfire.models.MundoModel;
import com.returnfire.service.HeightService;

@Serializable
public class CeldaDAO extends NetWorldCellDAO{
        public static final int OFFSET_RANDOM_ELEMENT=50;       
        
        private List<EstaticoDAO> estaticos;
        private List<EdificioDAO> edificios;
        private List<ContenedorDAO> contenedores;
        
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
                                estaticos.add(ArbolDAO.getNew(new Vector3f(x,HeightService.MAX_HEIGHT-10,z), ang));
                            }else if(i==EstaticoDAO.ELEMENTOS_ESTATICOS.ROCA.ordinal()){                                
                                estaticos.add(RockDAO.getNew(new Vector3f(x,HeightService.MAX_HEIGHT-10,z),ang, rnd));
                            }else if(i==EstaticoDAO.ELEMENTOS_ESTATICOS.RECURSO_PETROLEO.ordinal()){
                            	estaticos.add(RecursoNaturalDAO.getNew(RecursoPetroleoDAO.class, new Vector3f(x,HeightService.MAX_HEIGHT-10,z), ang));
                            }else if(i==EstaticoDAO.ELEMENTOS_ESTATICOS.RECURSO_HIERRO.ordinal()){
                            	estaticos.add(RecursoNaturalDAO.getNew(RecursoHierroDAO.class, new Vector3f(x,HeightService.MAX_HEIGHT-10,z), ang));
                            }
                        }
                    } 
                }
                estaticos.add(RecursoNaturalDAO.getNew(RecursoHierroDAO.class, new Vector3f(0,HeightService.MAX_HEIGHT-10,0), 0));
                edificios=new ArrayList<EdificioDAO>();
                contenedores=new ArrayList<ContenedorDAO>();
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
    
    
    public void addEdificio(EdificioDAO edificio){
    	edificios.add(edificio);
    	getId().timestamp=System.currentTimeMillis();    	
    }
    
    public List<ContenedorDAO> getContenedores() {
	return contenedores;
    }
        
    public boolean hasContenedores(){
        return contenedores!=null;
    }
    
    
    public void addContenedor(ContenedorDAO edificio){
    	contenedores.add(edificio);
    	getId().timestamp=System.currentTimeMillis();    	
    }
}
