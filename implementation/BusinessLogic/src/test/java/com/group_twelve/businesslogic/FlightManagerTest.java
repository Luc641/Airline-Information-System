package com.group_twelve.businesslogic;


import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.entities.Plane;
import com.group_twelve.persistence.FlightPersistence;
import com.group_twelve.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightManagerTest {

    @Mock
    static FlightPersistence persistence;

    FlightManager flightManager;

    @BeforeEach
    public void setup() {
        this.flightManager = new FlightManager(persistence);
    }

    @Test
    public void getAllFlightsReturnValidFlightEntity() throws SQLException {

        Flight f1 = new Flight(1, new Plane(1, 1, 1, "Boeing"), LocalDateTime.now(), LocalDateTime.now(), 200, new Airport(1, "Berlin"), new Airport(2, "Amsterdam"));
        Flight f2 = new Flight(2, new Plane(2, 1, 1, "Boeing"), LocalDateTime.now(), LocalDateTime.now(), 200, new Airport(1, "Berlin"), new Airport(2, "Amsterdam"));
        ArrayList<Flight> flightList = new ArrayList<>(List.of(f1,f2));
        when(persistence.load()).thenReturn(flightList);
        assertThat(this.flightManager.getAll()).isEqualTo(flightList);
    }

    @Test
    public void flightCanBeSavedThroughManager() {
        Flight f1 = new Flight(1, new Plane(1, 1, 1, "Boeing"), LocalDateTime.now(), LocalDateTime.now(), 200, new Airport(1, "Berlin"), new Airport(2, "Amsterdam"));
        assertThat(this.flightManager.save(f1)).isTrue();
    }


}
