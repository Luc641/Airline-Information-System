/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package businesslogic;

import java.time.LocalDate;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
class CustomerImpl implements businessentitiesapi.Customer{
    
    private final String name;
    private final LocalDate dateOfBirth;

    public CustomerImpl( String name, LocalDate dob ) {
        this.name = name;
        this.dateOfBirth = dob;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", dateOfBirth=" + dateOfBirth + '}';
    }
    
    

    
    
}
