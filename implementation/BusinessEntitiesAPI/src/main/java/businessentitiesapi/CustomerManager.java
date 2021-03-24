/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package businessentitiesapi;

import java.time.LocalDate;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public interface CustomerManager {
            
    Customer createCustomer( String name, LocalDate dateOfBirth );
    
    Customer add(Customer c);
    
}
