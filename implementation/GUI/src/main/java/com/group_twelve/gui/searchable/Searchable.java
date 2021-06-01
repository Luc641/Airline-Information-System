package com.group_twelve.gui.searchable;

import java.util.function.Predicate;

@FunctionalInterface
public interface Searchable<T> {
    Predicate<T> search(String searchTerm);
}
