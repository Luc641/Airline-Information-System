package com.group_twelve.businesslogic;

import com.group_twelve.entities.Option;
import com.group_twelve.persistence.FlightPersistence;
import com.group_twelve.persistence.OptionPersistence;

import java.util.ArrayList;
import java.util.List;

public class OptionManager implements Manager<Option>{

    OptionPersistence persistence;

    public OptionManager(OptionPersistence persistence) {
        this.persistence = persistence;
    }

    public static Option create(Object[] args){
        return new Option(Integer.parseInt((String) args[0]), (String)args[1], Integer.parseInt((String) args[2]));
    }

    @Override
    public List<Option> getAll() {
        try{
            return persistence.load();
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean save(Option object) {
        throw new UnsupportedOperationException("todo");
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        throw new UnsupportedOperationException("todo");
    }
}
