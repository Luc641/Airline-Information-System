/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public interface Persistence<E> {

    ArrayList<E> load() throws SQLException;

}
