/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import java.util.Collection;
import com.group_twelve.entities.Plane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 *
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class PlanePersistence implements Persistence<Plane>{

    Path path;
    String seperator;
    Predicate<String> lineFilter;
    Function<? super String[], ? extends Plane> creator;
    
    public PlanePersistence(Path path, String seperator, Predicate<String> lineFilter, Function<? super String[], ? extends Plane> creator) {
        this.path = path;
        this.seperator = seperator;
        this.lineFilter = lineFilter;
        this.creator = creator;
    }
    
    public void save(Collection<Plane> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Plane> load() throws IOException {
        ArrayList<Plane> list = new ArrayList<>();
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
