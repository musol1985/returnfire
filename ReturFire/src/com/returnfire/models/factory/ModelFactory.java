/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.buildings.ExtensionDAO;
import com.returnfire.dao.elementos.buildings.ExtensionDAO.EXTENSIONES;
import com.returnfire.dao.elementos.environment.ArbolDAO;
import com.returnfire.dao.elementos.environment.RockDAO;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.BaseTierraPequeModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;
import com.returnfire.models.elementos.buildings.ext.PetroleoExt;
import com.returnfire.models.elementos.buildings.ext.PiezasExt;
import com.returnfire.models.elementos.buildings.ext.VacioExt;
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
        arbol.move(dao.getPos().add(0, 5, 0));//.add(realPos.x,0,realPos.y));
        arbol.rotate(0, dao.getAng(), 0);
        return arbol;
    } 
    
    
    public EstaticoModel crearRoca(RockDAO dao, CeldaDAO celda){
        EstaticoModel model=null;
        if(dao.getSubTipo()==RockDAO.ROCK_TYPE.BROWN_1){
            model= crearRockBrown1(null, dao, celda);
        }else if(dao.getSubTipo()==RockDAO.ROCK_TYPE.BROWN_2){
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
    
    @Instance(attachTo = "")
    public BaseTierraPequeModel crearBaseTierra(BaseTierraPequeModel base, EdificioVehiculosDAO dao){
        //Vector2f realPos=celda.getId().id.mult(MundoModel.CELL_SIZE);
        base.move(dao.getPos());//.add(realPos.x,0,realPos.y));
        base.rotate(0, dao.getAng(), 0);
        return base;
    } 
    
    public BuildingExtension crearExtension(ExtensionDAO ext){
    	if(ext.getTipo()==EXTENSIONES.PETROLEO){
    		return crearExtensionPetroleo(null, ext);
    	}else if(ext.getTipo()==EXTENSIONES.PIEZAS){
    		return crearExtensionPiezas(null, ext);
    	}
    	return crearExtensionVacia(null);
    }
    
    @Instance(attachTo = "")
    public PetroleoExt crearExtensionPetroleo(PetroleoExt model, ExtensionDAO dao ){
        return model;
    } 
    

    @Instance(attachTo = "")
    public PiezasExt crearExtensionPiezas(PiezasExt model, ExtensionDAO dao ){
        return model;
    } 
    
    @Instance(attachTo = "")
    public VacioExt crearExtensionVacia(VacioExt model){
        return model;
    } 
}
