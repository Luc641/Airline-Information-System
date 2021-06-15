package com.group_twelve.businesslogic.searchable;

import com.group_twelve.entities.Flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 *
 */
public enum Filter implements Searchable<Flight> {
    ALWAYS_FALSE("") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            return f -> false;
        }
    },

    FLIGHT_ID("Flight Number") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                var number = Integer.parseInt(searchTerm);
                return f -> f.getID() == number;
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    PLANE_ID("Plane Id") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                var number = Integer.parseInt(searchTerm);
                return f -> f.getPlane().getID() == number;
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    PLANE_NAME("Plane") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                return f -> f.getPlane().getName().toLowerCase().contains(searchTerm.toLowerCase());
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },


    FLIGHT_PRICE("Flight Price") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                var number = Integer.parseInt(searchTerm);
                return f -> f.getFlightPrice() == number;
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    DEP_TIME("DepartureDate") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                var parsed = LocalDateTime.parse(searchTerm, formatter);
                return f -> f.getDepartureTime().equals(parsed);
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    ARR_TIME("ArrivalDate") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                var parsed = LocalDateTime.parse(searchTerm, formatter);
                return f -> f.getArrivalTime().equals(parsed);
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    ARRIVAL("Arrival") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                return f -> f.getArrivalAirport().getName().toLowerCase().contains(searchTerm.toLowerCase());
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    },

    DEPARTURE("Departure") {
        @Override
        public Predicate<Flight> search(String searchTerm) {
            try {
                return f -> f.getDepartureAirport().getName().toLowerCase().contains(searchTerm.toLowerCase());
            } catch (NumberFormatException e) {
                return f -> false;
            }
        }
    };

    private final String filterName;

    Filter(String name) {
        filterName = name;
    }

    /**
     * Returns the associated filter for this string argument.
     * This returns {@link Filter#ALWAYS_FALSE} by default.
     *
     * @param filterName the name of this filter
     * @return the associated filter
     */
    public static Filter findFilter(String filterName) {
        Filter toReturn = ALWAYS_FALSE;
        for (Filter value : values()) {
            if (value.filterName.equals(filterName)) {
                toReturn = value;
                break;
            }
        }
        return toReturn;
    }
}
