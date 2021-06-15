/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
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
        return new PriceReduction(Integer.valueOf(args[0]), args[1], args[2], Double.valueOf(args[3]), PriceReductionType.valueOf(args[4]));
    }
    
    public boolean delete(int id){
        return persistence.delete(id);
    }
    
    public boolean save(PriceReduction pr){
        return persistence.save(pr);
    }
    
    public boolean save(String[] args) {
        String name = args[0];
        String description = args[1];
        String percentage = args[2];
        String type = args[3];
        PriceReduction pr = new PriceReduction(-1, name, description, Double.valueOf(percentage), PriceReductionType.valueOf(type));
        return persistence.save(pr);
    }
    
    public ArrayList<PriceReduction> getAll() {
        try{
            return persistence.load();
        }catch(Exception e){
            System.out.println("Error pulling PriceReductions");
            e.printStackTrace();
        }
        return new ArrayList<PriceReduction>();
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        return null;
    }
}
