/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * @author timo
 */
public class PriceReductionPersistence implements Persistence<PriceReduction>{

    SQLConnection database;
    Function<? super String[], ? extends PriceReduction> creator;
    
    public PriceReductionPersistence(SQLConnection database, Function<? super String[], ? extends PriceReduction> creator) {
        this.database = database;
        this.creator = creator;
    }
    
    public boolean delete(int id) {
        try{
            database.query(String.format("DELETE FROM PriceReduction WHERE id=%d", id));
            database.query(String.format("SELECT setval('pricereduction_id_seq', %d, false);", id));
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public boolean save(PriceReduction pr) {
        try{
            String insertString = String.format("INSERT INTO PriceReduction(prName, description, amount, prType) VALUES ('%s', '%s', %s, %d);", pr.getName(), pr.getDescription(), pr.getPercentage(), pr.getType().ordinal()+1);
            database.query(insertString);
            database.query("SELECT setval('pricereduction_id_seq', COALESCE((SELECT MAX(id)+1 FROM pricereduction), 1), false);");
        }catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public ArrayList<PriceReduction> load() throws SQLException {
        ArrayList<PriceReduction> list = new ArrayList<>();
        
        try{
            ResultSet priceReductions = database.query("SELECT PR.ID, PR.prName, PR.description, PR.amount, PRT.typeName FROM PriceReduction PR "
                    + "JOIN PriceReductionType PRT ON PR.prType = PRT.ID");
            if(priceReductions == null) 
                return new ArrayList<>();
            while(priceReductions.next()) {
                System.out.println(priceReductions.getString(1));
                list.add(creator.apply(new String[]{priceReductions.getString(1), priceReductions.getString(2), priceReductions.getString(3), priceReductions.getString(4), priceReductions.getString(5)}));
            }
        }catch(SQLException e){
            //e.printStackTrace();
        }
        
        return list;
    }
    
}
