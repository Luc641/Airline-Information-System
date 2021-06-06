package com.group_twelve.businesslogic;

import com.group_twelve.entities.Customer;
import com.group_twelve.persistence.CustomerPersistence;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements Manager<Customer> {

    CustomerPersistence persistence;

    public CustomerManager(CustomerPersistence persistence) {
        this.persistence = persistence;
    }

    public static Customer create(Object[] args){
        return new Customer(Integer.parseInt((String) args[0]), (String) args[1]);
    }

    public Customer getCustomerById(int ID){
        try{
            return persistence.getCustomerById(ID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Customer(0, "null");
    }

    @Override
    public List<Customer> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean save(Customer object) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
