/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.returnfire;

import com.entity.core.EntityManager;
import static com.entity.network.core.models.NetWorld.CELLS_CACHE_SIZE;
import com.entity.utils.Vector2;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Edu
 */
public class Test {
    
    	
	private static float HASH_TABLE_LOAD_FACTOR=0.75f;
	private static int HASH_TABLE_CAPACITY = (int) Math.ceil(CELLS_CACHE_SIZE / HASH_TABLE_LOAD_FACTOR) + 1;
    

    public static void main(String[] args){
        LinkedHashMap<Vector2, String> cache=new LinkedHashMap<Vector2, String>(HASH_TABLE_CAPACITY, HASH_TABLE_LOAD_FACTOR, true) {
             @Override
            protected boolean removeEldestEntry(Map.Entry<Vector2, String> eldest) {
                return false;
            }
        };
        
        System.out.println("---> IGUAL: "+new Vector2(0,0).equals(new Vector2(0,0)));
        System.out.println("---> Hash: "+new Vector2(0,0).hashCode()+" - "+new Vector2(0,0).hashCode());
        cache.put(new Vector2(0,0), "primero");
        System.out.println("------->"+cache.containsKey(new Vector2(0,0)));
        System.out.println("------->"+cache.get(new Vector2(0,0)));
    }
}
