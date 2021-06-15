/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public interface Manager<E> {

    List<E> getAll();

    static Object create(Object[] args) {
        return null;
    }

    boolean save(E object);

    ArrayList<String> validateInput(String a1, String a2, String tCount);



}
