package com.group_twelve.persistence;
import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.function.Function;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Booking;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)

public class FlightPersistenceTest {

    Flight mockFLight = new Flight(1, new Plane(1, 1, 1, "Plane"), LocalDateTime.MIN,LocalDateTime.MAX,150 , new Airport(1, "B"), new Airport(2, "A"));

    @Mock
    SQLConnection databaseMock;
    @Mock
    public Function<? super Object[], ? extends Flight> creator;
    @Mock
    public Function<? super Integer, ? extends Plane> planeCreator;
    @Mock
    public Function<? super Integer, ? extends Airport> airportCreator;
    @Mock
    public Function<? super Integer, ? extends Route> routeCreator;

    FlightPersistence flightPersistence;

    @BeforeEach
    void startup() {
        // Init the SUT
        this.flightPersistence = new FlightPersistence(databaseMock,creator, planeCreator,airportCreator,routeCreator);
    }

    @Disabled
    @Test
    void testLoadMethodReturnsValidFlightsThroughCreator() {

        doReturn(mockFLight).when(this.creator).apply(new Object[]{"1","1", "1", "1","Plane",LocalDateTime.MIN,LocalDateTime.MAX,"150","1","B","2","A"});

        Flight flight = creator.apply(new Object[]{"1","1", "1", "1","Plane",LocalDateTime.MIN,LocalDateTime.MAX,"150","1","B","2","A"});

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(flight.getID()).isEqualTo(1);
            softAssertions.assertThat(flight.getPlane().getID()).isEqualTo(1);
            softAssertions.assertThat(flight.getArrivalTime()).isEqualTo(LocalDateTime.MIN);
            softAssertions.assertThat(flight.getDepartureTime()).isEqualTo(LocalDateTime.MAX);
            softAssertions.assertThat(flight.getDepartureAirport().getID()).isEqualTo(2);
            softAssertions.assertThat(flight.getArrivalAirport().getID()).isEqualTo(1);
            softAssertions.assertThat(flight.getFlightPrice()).isEqualTo(150);
        });
    }



    @Test
    void verifyThatEmptyListIsReturnedWhenNoValuesAreGottenFromDatabase() throws SQLException {

        // Mock resultset
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);

        // Train mock not to return anything when the query functionality is called within the load method
        when(databaseMock.query("SELECT * from Flight")).thenReturn(mockResultSet);

        // Verify that an empty list is returned
        assertThat(flightPersistence.load()).isEqualTo(new ArrayList<Flight>());
    }

    @Test
    void verifyThatNoExceptionOccursWhenDatabaseIsEmpty() throws SQLException {
        // Mock resultset
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);

        // Train mock not to return anything when the query functionality is called within the load method
        when(databaseMock.query("SELECT * from Flight")).thenReturn(mockResultSet);

        ThrowableAssert.ThrowingCallable code = () -> {
            flightPersistence.load();
        };

        assertThatCode(code)
                .doesNotThrowAnyException();

    }

    @Test
    void verifySavingFlightDoesntCauseException() {

        ThrowableAssert.ThrowingCallable code = () -> {
            flightPersistence.save(mockFLight);
        };
        assertThatCode(code)
                .doesNotThrowAnyException();
    }

}


