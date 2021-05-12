/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;


import com.group_twelve.persistence.PersistenceAPI;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class BusinessLogic {
    private Map<Class, Manager> managers;
    
    public BusinessLogic() {
        this.managers = new HashMap<>();
    }

    public void addManager(Class managerClass, Manager manager){
        if(!managers.containsKey(managerClass))
            managers.put(managerClass, manager);
        else
            throw new IllegalArgumentException(String.format("Attempted to add already added Manager for Class '%s'", managerClass.getSimpleName()));
    }
    
    public void replaceManager(Class managerClass, Manager manager) {
        if(!managers.containsKey(managerClass))
            throw new IllegalArgumentException(String.format("Attempted to replace non-added Manager for class '%s'.", managerClass.getSimpleName()));
        else managers.replace(managerClass, manager);
    }
    
    public void removeManager(Class managerClass) {
        managers.remove(managerClass);
    }
    
    public Manager getManager(Class managerClass) {
        return managers.get(managerClass);
    }
    
    public boolean hasManager(Class managerClass) {
        return managers.containsKey(managerClass);
    }
}
