/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.factory;

import com.entity.anot.Instance;
import com.entity.core.items.BaseService;
import com.returnfire.dao.CeldaDAO;
import com.returnfire.dao.elementos.ContenedorDAO;
import com.returnfire.dao.elementos.buildings.ConstruyendoDAO;
import com.returnfire.dao.elementos.buildings.EdificioVehiculosDAO;
import com.returnfire.dao.elementos.buildings.ExtensionDAO;
import com.returnfire.dao.elementos.buildings.ExtensionDAO.EXTENSIONES;
import com.returnfire.dao.elementos.buildings.impl.ExtractorHierroDAO;
import com.returnfire.dao.elementos.buildings.impl.ExtractorPetroleoDAO;
import com.returnfire.dao.elementos.buildings.impl.MolinoEolicoDAO;
import com.returnfire.dao.elementos.contenedores.BarrilDAO;
import com.returnfire.dao.elementos.environment.impl.ArbolDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoHierroDAO;
import com.returnfire.dao.elementos.environment.impl.RecursoPetroleoDAO;
import com.returnfire.dao.elementos.environment.impl.RockDAO;
import com.returnfire.models.elementos.EstaticoModel;
import com.returnfire.models.elementos.buildings.ext.BuildingExtension;
import com.returnfire.models.elementos.buildings.ext.PetroleoExt;
import com.returnfire.models.elementos.buildings.ext.PiezasExt;
import com.returnfire.models.elementos.buildings.ext.VacioExt;
import com.returnfire.models.elementos.buildings.impl.BaseTierraPequeModel;
import com.returnfire.models.elementos.buildings.impl.ConstruyendoModel;
import com.returnfire.models.elementos.buildings.impl.ExtractorHierroModel;
import com.returnfire.models.elementos.buildings.impl.ExtractorPetroleoModel;
import com.returnfire.models.elementos.buildings.impl.MolinoEolicoModel;
import com.returnfire.models.elementos.contenedores.ContenedorModel;
import com.returnfire.models.elementos.contenedores.impl.BarrilModel;
import com.returnfire.models.elementos.environment.ArbolModel;
import com.returnfire.models.elementos.environment.BrownRock1;
import com.returnfire.models.elementos.environment.BrownRock2;
import com.returnfire.models.elementos.environment.BrownRock3;
import com.returnfire.models.elementos.environment.RecursoHierroModel;
import com.returnfire.models.elementos.environment.RecursoPetroleoModel;

/**
 *
 * @author Edu
 */
public class ModelFactory extends BaseService{
    @Instance(attachTo = "")
    public ArbolModel crearArbol(ArbolModel arbol, ArbolDAO dao){
        return arbol;
    } 
    
    
    public EstaticoModel crearRoca(RockDAO dao){
        EstaticoModel model=null;
        if(dao.getSubTipo()==RockDAO.ROCK_TYPE.BROWN_1){
            model= crearRockBrown1(null, dao);
        }else if(dao.getSubTipo()==RockDAO.ROCK_TYPE.BROWN_2){
            model= crearRockBrown2(null, dao);
        }else{ //if(dao.getTipo()==RockDAO.ROCK_TYPE.BROWN_3){
            model= crearRockBrown3(null, dao);
        }
        
        return model;
    }
    
    @Instance(attachTo = "")
    public BrownRock1 crearRockBrown1(BrownRock1 rock, RockDAO dao){
        return rock;
    } 
    
    @Instance(attachTo = "")
    public BrownRock2 crearRockBrown2(BrownRock2 rock, RockDAO dao){
        return rock;
    }
    
    @Instance(attachTo = "")
    public BrownRock3 crearRockBrown3(BrownRock3 rock, RockDAO dao){
        return rock;
    } 
    
    @Instance(attachTo = "")
    public ConstruyendoModel crearConstruyendo(ConstruyendoModel model, ConstruyendoDAO dao){
        return model;
    } 
    
    @Instance(attachTo = "")
    public BaseTierraPequeModel crearBaseTierra(BaseTierraPequeModel base, EdificioVehiculosDAO dao){
        return base;
    } 
    
    @Instance(attachTo = "")
    public MolinoEolicoModel crearMolinoEolico(MolinoEolicoModel base, MolinoEolicoDAO dao){
        return base;
    }
    
    @Instance(attachTo = "")
    public ExtractorHierroModel crearExtractorHierro(ExtractorHierroModel base, ExtractorHierroDAO dao){
        return base;
    }
    
    @Instance(attachTo = "")
    public ExtractorPetroleoModel crearExtractorPetroleo(ExtractorPetroleoModel base, ExtractorPetroleoDAO dao){
        return base;
    }
    
    @Instance(attachTo = "")
    public RecursoPetroleoModel crearRecursoPetroleo(RecursoPetroleoModel model, RecursoPetroleoDAO  dao){
        return model;
    } 
    
    @Instance(attachTo = "")
    public RecursoHierroModel crearRecursoHierro(RecursoHierroModel model, RecursoHierroDAO  dao){
        return model;
    } 
    
    public BuildingExtension crearExtension(ExtensionDAO ext){
        if(ext!=null){
            if(ext.getTipo()==EXTENSIONES.PETROLEO){
                    return crearExtensionPetroleo(null, ext);
            }else if(ext.getTipo()==EXTENSIONES.PIEZAS){
                    return crearExtensionPiezas(null, ext);
            }
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
    
    public ContenedorModel crearContenedor(ContenedorDAO dao){
    	ContenedorModel model=null;
    	
    	if(dao instanceof BarrilDAO){
            model=crearBarril(null, (BarrilDAO)dao);   
        }
    	
        return model;
    }
    
    
    
    @Instance(attachTo = "")
    public BarrilModel crearBarril(BarrilModel model, BarrilDAO dao){
        return model;
    } 
}
