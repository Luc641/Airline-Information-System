/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public interface Persistence<E> {

    List<E> load() throws SQLException;

    //TODO: Make it so that when something has been saved that it returns the entity instead of for example a boolean
    void save(E entity);



}
