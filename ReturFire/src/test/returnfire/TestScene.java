/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.returnfire;

import com.entity.adapters.DirectionalLightShadow;
import com.entity.adapters.ScrollCameraAdapter;
import com.entity.anot.Entity;
import com.entity.anot.ScrollCameraNode;
import com.entity.anot.components.lights.AmbientLightComponent;
import com.entity.anot.components.lights.DirectionalLightComponent;
import com.entity.anot.components.shadows.DirectionalShadowComponent;
import com.entity.core.items.Scene;
import com.jme3.light.AmbientLight;
import com.returnfire.models.CeldaModel;

/**
 *
 * @author Edu
 */
public class TestScene extends Scene<Test>{

    private int OFFSET=CeldaModel.CELL_SIZE/2-50;
    
    @Entity
    private TestTerrain lowerSide;
        /*@Entity
    private TestTerrain upperSide;*/
    
              @AmbientLightComponent(color = {1,1,1,1}, mult = 1f)
        private AmbientLight ambient;

        @DirectionalLightComponent(color = {1,1,1,0.2f},direction = {1,-1,-0.5f})
        @DirectionalShadowComponent
        private DirectionalLightShadow sol;
        
            @ScrollCameraNode(speed = 100, debug = true)
    private ScrollCameraAdapter camera;

    @Override
    public void onLoadScene() throws Exception {
        super.onLoadScene(); //To change body of generated methods, choose Tools | Templates.
      /*  float y=0;
            for(int z=0;z<MundoModel.CELL_SIZE/2+1;z++){
                for(int x=-MundoModel.CELL_SIZE/2;x<MundoModel.CELL_SIZE/2+1;x++){                     
                    lowerSide.getT().setHeight(new Vector2f(x,z), y);  
               }
                if(z<OFFSET)
                    y+=0.5f;
           }
        y=0;
            for(int z=0;z<MundoModel.CELL_SIZE/2+1;z++){
                for(int x=-MundoModel.CELL_SIZE/2;x<MundoModel.CELL_SIZE/2+1;x++){                     
                    upperSide.getT().setHeight(new Vector2f(x,z), y);  
               }
                if(z<OFFSET)
                    y+=0.5f;
           }
            upperSide.move(0, 0, 256);
            upperSide.getT().rotate(0, FastMath.PI, 0);*/
    }
            
            
            
}
