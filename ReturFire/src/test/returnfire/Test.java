/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.returnfire;

import com.entity.anot.entities.SceneEntity;
import com.entity.core.EntityGame;
import com.entity.core.EntityManager;

/**
 *
 * @author Edu
 */
public class Test extends EntityGame{

    
    @SceneEntity(preLoad=false, singleton=false, first = true)
    public TestScene main;
    
   public static void main(String[] args)throws Exception{
        EntityManager.startGame(Test.class);                
    }
   

}
