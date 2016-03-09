/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire.models.elementos;

import com.entity.core.items.Model;
import com.returnfire.dao.elementos.EstaticoDAO;

/**
 *
 * @author Edu
 */
public class EstaticoModel<T extends EstaticoDAO> extends Model {
    protected T dao;

    public void setDao(T dao) {
        this.dao = dao;
    }
    
    public T getDAO(){
        return dao;
    }
}
