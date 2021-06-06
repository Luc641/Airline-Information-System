package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Booking;
import com.group_twelve.entities.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class CustomerPersistence implements Persistence<Customer>{

    SQLConnection database;
    Function<? super Object[], ? extends Customer> creator;

    public CustomerPersistence(SQLConnection database, Function<? super Object[], ? extends Customer> creator) {
        this.database = database;
        this.creator = creator;
    }

    public Customer getCustomerById(int ID){
        try{
            String query = String.format("SELECT * FROM customer WHERE id = %s", ID);
            ResultSet customer = database.query(query);

            customer.next();
            return creator.apply(new Object[]{customer.getString(1), customer.getString(2)});

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Customer(0, "null");
    }

    @Override
    public List<Customer> load() throws SQLException {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean save(Customer entity) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
