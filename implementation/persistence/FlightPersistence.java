/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.Flight;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author timom
 */
public class FlightPersistence implements Persistence<Flight>{
    
    Path path;
    String seperator;
    Predicate<String> lineFilter;
    Function<? super String[], ? extends Flight> creator;
    
    public FlightPersistence(Path path, String seperator, Predicate<String> lineFilter, Function<? super String[], ? extends Flight> creator) {
        this.path = path;
        this.seperator = seperator;
        this.lineFilter = lineFilter;
        this.creator = creator;
    }
    
    public void save(Collection<Flight> data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Flight> load() throws IOException {
        ArrayList<Flight> list = new ArrayList<>();
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
