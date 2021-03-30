/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;
import com.group_twelve.entities.*;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class ManagerAPITest {
    
    public ManagerAPITest() {
    }
    
    @Test
    public void testAddManager() {
        ManagerAPI api = new ManagerAPI();
        AirportManager apm = new AirportManager();
        api.addManager(Airport.class, apm);
        assertThat(api.getManager(Airport.class))
                .isNotNull()
                .isSameAs(apm);
    }
    
    @Test
    public void testIllegalAddException() {
        ManagerAPI api = new ManagerAPI();
        AirportManager apm = new AirportManager();
        api.addManager(Airport.class, apm);
        AirportManager secondapm = new AirportManager();
        assertThatThrownBy(() -> {
            api.addManager(Airport.class, secondapm);
        }).isInstanceOf(IllegalArgumentException.class); 
    }
    
    @Test
    public void testReplaceManager() {
        ManagerAPI api = new ManagerAPI();
        AirportManager apm = new AirportManager();
        api.addManager(Airport.class, apm);
        AirportManager replacement = new AirportManager();
        api.replaceManager(Airport.class, replacement);
        assertThat(api.getManager(Airport.class))
                .isNotNull()
                .isSameAs(replacement);
    }
    
    @Test
    public void testRemoveManager() {
        ManagerAPI api = new ManagerAPI();
        AirportManager apm = new AirportManager();
        api.addManager(Airport.class, apm);
        api.removeManager(Airport.class);
        assertThat(api.getManager(Airport.class))
                .isNull();
    }
    
    @Test
    public void testHasManager() {
        ManagerAPI api = new ManagerAPI();
        AirportManager apm = new AirportManager();
        api.addManager(Airport.class, apm);
        assertThat(api.hasManager(Airport.class))
                .isTrue();
    }
}
