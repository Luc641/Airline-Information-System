package com.group_twelve.businesslogic.searchable;

import java.util.function.Predicate;

@FunctionalInterface
public interface Searchable<T> {
    Predicate<T> search(String searchTerm);
}
