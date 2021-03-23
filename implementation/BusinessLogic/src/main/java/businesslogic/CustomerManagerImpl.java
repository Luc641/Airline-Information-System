/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package businesslogic;

import businessentitiesapi.Customer;
import businessentitiesapi.CustomerManager;
import java.time.LocalDate;
import persistence.CustomerStorageService;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
class CustomerManagerImpl implements CustomerManager { 
    
    private CustomerStorageService customerStorageService;
    
    // No lists of Customer objects here, to avoid sync problems 

    public void setCustomerStorageService( CustomerStorageService customerStorageService ) {
        this.customerStorageService = customerStorageService;
    }
    
    @Override
    public Customer add(Customer c){
        customerStorageService.add(c);
        return c;
    }

    @Override
    public Customer createCustomer( String name, LocalDate dob ) {
        return new CustomerImpl(name, dob);
    }
    
}
