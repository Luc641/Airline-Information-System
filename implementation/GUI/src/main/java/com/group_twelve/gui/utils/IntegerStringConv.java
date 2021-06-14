package com.group_twelve.gui.utils;

import javafx.util.StringConverter;

public class IntegerStringConv extends StringConverter<Integer> {
    @Override
    public String toString(Integer integer) {
        return integer.toString();
    }

    @Override
    public Integer fromString(String s) {
        int parsed;
        try {
            parsed = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return -1;
        }
        if (parsed < 0) {
            return -1;
        }
        return parsed;
    }
}
