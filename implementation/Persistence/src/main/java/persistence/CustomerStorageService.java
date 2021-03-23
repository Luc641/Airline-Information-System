/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package persistence;

import businessentitiesapi.Customer;
import java.util.List;


/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public interface CustomerStorageService {
    
    void add( Customer c);
    List<Customer> getAll();
    
}
