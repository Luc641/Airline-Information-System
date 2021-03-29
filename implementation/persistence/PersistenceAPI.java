/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author timom
 */
public class PersistenceAPI {
    private Map<Class, Persistence> persistences;

    public PersistenceAPI() {
        this.persistences = new HashMap<>();
    }
    
    public void addPersistence(Class persistenceClass, Persistence persistence){
        if(!persistences.containsKey(persistenceClass))
            persistences.put(persistenceClass, persistence);
        else
            throw new IllegalArgumentException(String.format("Attempted to add already added Persistence for Class '%s'", persistenceClass.getSimpleName()));
    }
    
    public void replacePersistence(Class persistenceClass, Persistence persistence) {
        if(!persistences.containsKey(persistenceClass))
            throw new IllegalArgumentException(String.format("Attempted to replace non-added Persistence for class '%s'.", persistenceClass.getSimpleName()));
    }
    
    public void removePersistence(Class persistenceClass) {
        persistences.remove(persistenceClass);
    }
    
    public Persistence getPersistence(Class persistenceClass) {
        return persistences.get(persistenceClass);
    }
    
    public boolean hasPersistence(Class persistenceClass) {
        return persistences.containsKey(persistenceClass);
    }
}
