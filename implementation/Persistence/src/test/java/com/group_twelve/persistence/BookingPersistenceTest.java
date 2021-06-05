package com.group_twelve.persistence;

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

@ExtendWith( MockitoExtension.class )
public class BookingPersistenceTest {

    Booking mockBooking = new Booking(1, LocalDate.of(2021,2,12),2,3,4);

    @Mock
    SQLConnection databaseMock;
    @Mock
    Function<? super Object[], ? extends Booking> creator;

    BookingPersistence bookingPersistence;

    @BeforeEach
    void startup(){
        // Init the SUT
        this.bookingPersistence = new BookingPersistence(databaseMock, creator);
    }

    /**
     * This method tests whether the creator.apply functionality correctly works.
     */
    @Test
    void testLoadMethodReturnsValidBookingThroughCreator(){

        // Create mock booking for the creator to return

        doReturn(mockBooking).when(this.creator).apply(new Object[]{"1","12-2-21","2","3","4"});

        Booking booking = creator.apply(new Object[]{"1","12-2-21","2","3","4"});

        // Verify all values
        SoftAssertions.assertSoftly(v -> {
            v.assertThat(booking.getID()).isEqualTo(1);
            v.assertThat(booking.getBookingDate().toString()).isEqualTo("2021-02-12");
            v.assertThat(booking.getEmployeeID()).isEqualTo(3);
            v.assertThat(booking.getFlightRouteID()).isEqualTo(2);
            v.assertThat(booking.getPriceReductionID()).isEqualTo(4);
        });

    }


    /**
     * Verify that the load method returns an empty list whenever nothing could be retrieved from the database.
     */
    @Test
    void verifyThatEmptyListIsReturnedWhenNoValuesAreGottenFromDatabase() throws SQLException {

        // Mock resultset
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);

        // Train mock not to return anything when the query functionality is called within the load method
        when(databaseMock.query("SELECT * FROM booking")).thenReturn(mockResultSet);

        // Verify that an empty list is returned
        assertThat(bookingPersistence.load()).isEqualTo(new ArrayList<Booking>());
    }

    /**
     * Verify that no Exception occurs when the database returns an empty resultset
     * @throws SQLException
     */
    @Test
    void verifyThatNoExceptionOccursWhenDatabaseIsEmpty() throws SQLException {
        // Mock resultset
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);

        // Train mock not to return anything when the query functionality is called within the load method
        when(databaseMock.query("SELECT * FROM booking")).thenReturn(mockResultSet);

        ThrowableAssert.ThrowingCallable code = () -> {
            bookingPersistence.load();
        };

        assertThatCode(code)
                .doesNotThrowAnyException();

    }

    /**
     * Verify that the class handles an SQL exception which occurs in the layer above correctly.
     */
    // TODO: Fix.
//    @Disabled("Not working correctly yet")
    @Test
    void verifyThatExceptionDoesOccur() throws SQLException {

        // Mock resultset
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenThrow(SQLException.class);

        when(databaseMock.query("SELECT * FROM booking")).thenReturn(mockResultSet);

        ThrowableAssert.ThrowingCallable code = () -> {
            bookingPersistence.load();
        };

        assertThatThrownBy(code).isInstanceOf(Exception.class);

    }

    /**
     * Test that the saving of a booking doesn't result in an exception.
     */
    @Test
    void verifySavingBookingDoesntCauseException(){

        ThrowableAssert.ThrowingCallable code = () -> {
            bookingPersistence.save(mockBooking);
        };
        assertThatCode(code)
                .doesNotThrowAnyException();
    }

}
