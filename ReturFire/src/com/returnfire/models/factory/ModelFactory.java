/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.models.JugadorModel;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.VehiculoModel;
import com.returnfire.models.elementos.environment.ArbolModel;
import com.returnfire.models.elementos.environment.BrownRock1;
import com.returnfire.models.elementos.environment.BrownRock2;
import com.returnfire.models.elementos.environment.BrownRock3;

/**
 *
 * @author Edu
 */
public class ModelFactory extends BaseService{
    @Instance(attachTo = "")
    public ArbolModel crearArbol(ArbolModel arbol, ArbolDAO dao, CeldaDAO celda){
        //Vector2f realPos=celda.getId().id.mult(MundoModel.CELL_SIZE);
        arbol.move(dao.getPos());//.add(realPos.x,0,realPos.y));
        arbol.rotate(0, dao.getAng(), 0);
        return arbol;
    } 
    
    
    public EstaticoModel crearRoca(RockDAO dao, CeldaDAO celda){
        EstaticoModel model=null;
        if(dao.getTipo()==RockDAO.ROCK_TYPE.BROWN_1){
            model= crearRockBrown1(null, dao, celda);
        }else if(dao.getTipo()==RockDAO.ROCK_TYPE.BROWN_2){
            model= crearRockBrown2(null, dao, celda);
        }else{ //if(dao.getTipo()==RockDAO.ROCK_TYPE.BROWN_3){
            model= crearRockBrown3(null, dao, celda);
        }
        
        model.move(dao.getPos());//.add(realPos.x,0,realPos.y));
        model.rotate(0, dao.getAng(), 0);
        
        return model;
    }
    
    @Instance(attachTo = "")
    public BrownRock1 crearRockBrown1(BrownRock1 rock, RockDAO dao, CeldaDAO celda){
        return rock;
    } 
    
    @Instance(attachTo = "")
    public BrownRock2 crearRockBrown2(BrownRock2 rock, RockDAO dao, CeldaDAO celda){
        return rock;
    }
    
    @Instance(attachTo = "")
    public BrownRock3 crearRockBrown3(BrownRock3 rock, RockDAO dao, CeldaDAO celda){
        return rock;
    } 
}