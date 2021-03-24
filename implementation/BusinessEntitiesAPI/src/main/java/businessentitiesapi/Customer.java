/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package businessentitiesapi;

import java.time.LocalDate;

/**
 * Customer class, representing the customer of a company.
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
public interface Customer {
    
    /**
     * Get name of customer.
     * @return customer name
     */
    String getName();   
    
    /**
     * Get date of birth of customer.
     * @return date of birth
     */
    LocalDate getDateOfBirth();
    
}
