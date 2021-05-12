/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.PriceReduction;
import com.group_twelve.persistence.PriceReductionPersistence;
import java.util.ArrayList;

/**
 *
 * @author timo
 */
public class PriceReductionManager implements Manager<PriceReduction>{
    PriceReductionPersistence persistence;

    public PriceReductionManager(PriceReductionPersistence persistence) {
        this.persistence = persistence;
    }
    
    public static PriceReduction create(String[] args){
        return null;
    }
    
    public void save(PriceReduction pr) {
        
    }
    
    public ArrayList<PriceReduction> getAll() {
        try{
            return persistence.load();
        }catch(Exception e){
            //e.printStackTrace();
        }
    }
}
