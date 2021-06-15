/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

/**
 *
 * @author timo
 */
public class PriceReduction {
    int id;
    String name, description;
    double percentage;
    PriceReductionType type;
    
    public PriceReduction(int id, String name, String description, double percentage, PriceReductionType type) {
        if(percentage <= 0 || percentage > 1)
            throw new IllegalArgumentException("Percentage of a price reduction has to be between 0.01 (1%) and 1 (100%)");
        this.id = id;
        this.name = name;
        this.description = description;
        this.percentage = percentage;
        this.type = type;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public double getPercentage() {
        return percentage;
    }
    
    public PriceReductionType getType() {
        return type;
    }
}
