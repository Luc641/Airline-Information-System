package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Option;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OptionPersistence implements Persistence<Option>{

    SQLConnection database;
    Function<? super Object[], ? extends Option> creator;

    public OptionPersistence(SQLConnection database, Function<? super Object[], ? extends Option> creator) {
        this.database = database;
        this.creator = creator;
    }

    @Override
    public List<Option> load() {
        List<Option> list = new ArrayList<>();
        try{
            ResultSet options = database.query("SELECT * FROM options");
            while(options.next()){
                list.add(creator.apply(new Object[]{options.getString(1), options.getString(2), options.getString(3)}));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Option entity) {
        throw new UnsupportedOperationException("todo");
    }
}
