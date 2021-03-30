/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;


import com.group_twelve.persistence.PersistenceAPI;


/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class BusinessLogic {
    PersistenceAPI persistenceAPI;
    ManagerAPI managerAPI;
    
    public BusinessLogic(ManagerAPI managerAPI, PersistenceAPI persistenceAPI) {
        this.persistenceAPI = persistenceAPI;
        this.managerAPI = managerAPI;
    }

    public PersistenceAPI getPersistenceAPI() {
        return persistenceAPI;
    }
    
    public ManagerAPI getManagerAPI() {
        return managerAPI;
    }
    
    
    
}
