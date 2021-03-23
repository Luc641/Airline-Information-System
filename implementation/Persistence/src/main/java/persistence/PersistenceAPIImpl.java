/*
 * Copyright (c) 2019 Informatics Fontys FHTenL University of Applied Science Venlo
 */
package persistence;

import businessentitiesapi.CustomerManager;

/**
 *
 * @author Richard van den Ham {@code r.vandenham@fontys.nl}
 */
class PersistenceAPIImpl implements PersistenceImplementationProvider, PersistenceAPI {

    @Override
    public CustomerStorageService getCustomerStorageService( CustomerManager customerManager ) {
        return new CustomerStorageServiceImpl( customerManager );
    }
    
}
