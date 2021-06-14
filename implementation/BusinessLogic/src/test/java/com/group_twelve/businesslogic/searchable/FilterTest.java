package com.group_twelve.businesslogic.searchable;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FilterTest {


    @ParameterizedTest
    @CsvSource({
            "Flight Number,FLIGHT_ID",
            "Plane Id,PLANE_ID",
            "Plane,PLANE_NAME",
            "Flight Price,FLIGHT_PRICE",
            "DepartureDate,DEP_TIME",
            "ArrivalDate,ARR_TIME",
            "Arrival,ARRIVAL",
            "Departure,DEPARTURE",
            "Null,ALWAYS_FALSE"

    })

    void findFilter(String string, String name) {
        var result = Filter.findFilter(string);
        assertThat(result).hasToString(name);

    }
}