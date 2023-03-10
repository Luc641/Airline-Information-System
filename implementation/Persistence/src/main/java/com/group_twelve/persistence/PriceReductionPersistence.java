/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.PriceReduction;
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
    
    public boolean save(PriceReduction pr) {
        ArrayList<PriceReduction> prList = new ArrayList<>();
        prList.add(pr);
        save(prList);
        return true;
    }
    
    public void save(ArrayList<PriceReduction> data) {
        String insertString = "INSERT INTO PriceReduction (ID, prName, description, percentage, prType) VALUES ";
        for(PriceReduction pr : data ) {
            insertString += String.format("(%d, %s, %s, %d, %d), ", pr.getID(), pr.getName(), pr.getDescription(), pr.getPercentage(), pr.getType());
        }
        insertString += "ON CONFLICT (airportID) DO NOTHING;";
        
        database.query(insertString);
    }
    
    public ArrayList<PriceReduction> load() throws SQLException {
        ArrayList<PriceReduction> list = new ArrayList<>();
        
        try{
            ResultSet priceReductions = database.query("SELECT PR.ID, PR.prName, PR.description, PR.amount, PRT.typeName FROM PriceReduction PR "
                    + "JOIN PriceReductionType PRT ON PR.prType = PRT.ID");
        
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
