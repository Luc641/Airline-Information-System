/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import java.util.Collection;
import com.group_twelve.entities.Airport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class AirportPersistence implements Persistence<Airport>{

    Path path;
    String seperator;
    Predicate<String> lineFilter;
    Function<? super String[], ? extends Airport> creator;
    
    public AirportPersistence(Path path, String seperator, Predicate<String> lineFilter, Function<? super String[], ? extends Airport> creator) {
        this.path = path;
        this.seperator = seperator;
        this.lineFilter = lineFilter;
        this.creator = creator;
    }
    
    public void save(Collection<Airport> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Airport> load() throws IOException {
        ArrayList<Airport> list = new ArrayList<>();
        Stream<String> lines = Files.lines(path);
        lines.distinct().forEach((String line) -> {
                    if(lineFilter.test(line)) {
                        list.add(creator.apply(line.split(seperator)));
                    }
                }
        );
        
        return list;
    }
    
}
