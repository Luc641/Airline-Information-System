package com.group_twelve.businesslogic;

import com.group_twelve.entities.Booking;
import com.group_twelve.persistence.BookingPersistence;
import com.group_twelve.persistence.Persistence;

import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith( MockitoExtension.class )
public class BookingManagerTest {

    // Interface
    @Mock
    Persistence<Booking> persistenceMock;

    // SUT
    BookingManager bookingManager;//= new BookingManager(bookingPersistence);

    @BeforeEach
    public void setup() {
        //Setup system under test
//        initMocks();

        this.bookingManager = new BookingManager( persistenceMock );
    }

    /**
     * This method checks whether the validateInput method in the
     * createBookingMain controller works correctly.
     */
    @ParameterizedTest
    @CsvSource( value = {
        // Arrival airport, Departure airport, nr of tickets || expected arrival, departure, ticket
        "berlin,amsterdam,3,Berlin,Amsterdam,3",
        "amsterdam,Dubai,2,Amsterdam,Dubai,2"
    } )
    public void correctInputValidationShouldBeAcceptedTest( String a1, String a2, String ticketCount,
            String ea1, String ea2, String eTicketCount ) {

        ArrayList<String> returnedValues = this.bookingManager.validateInput( a1, a2, ticketCount );

        SoftAssertions.assertSoftly( v -> {
            v.assertThat( returnedValues.get( 0 ) ).as( "The first letter should be capitalized" ).isEqualTo( ea1 );
            v.assertThat( returnedValues.get( 1 ) ).as( "The first letter should be capitalized" ).isEqualTo( ea2 );
            v.assertThat( returnedValues.get( 2 ) ).as( "This should be a letter" ).isEqualTo( eTicketCount );
        } );

    }

    /**
     * Test if wrong inputs are detected and filtered out. If a wrong input has
     * been detected it should return an empty arraylist.
     */
    @ParameterizedTest
    @CsvSource( value = {
        // Arrival airport, Departure airport, nr of tickets
        "b1rlin,amsterdam,1",
        "berlin,amst3rd@m,3",
        "berlin,amsterdam,e",
        "b1rl1n,@mst3rd@m,r"
    } )
    public void incorrectInputValidationShouldReturnEmptyArraylist( String a1, String a2, String ticketCount ) {
        ArrayList<String> r = this.bookingManager.validateInput( a1, a2, ticketCount );
        assertThat( r.isEmpty() ).isTrue();
    }

    /**
     * Test whether the creator returns a valid booking entity whenever it gets
     * called from the getAll() method. (The persistence is mocked).
     */
    @Test
    public void getAllBookingsReturnValidBookingEntity() throws SQLException {

        // Train the Booking persistence to return two mocked rows from the database.
        Booking b1 = new Booking( 1, LocalDate.now(), 1, 1, 1 );
        Booking b2 = new Booking( 2, LocalDate.now(), 2, 3, 4 );
        List<Booking> dbr = List.of( b1, b2 );
        when( persistenceMock.load() ).thenReturn( dbr );

//        verify(persistenceMock, times(1)).load();
        assertThat( this.bookingManager.getAll() ).isEqualTo( dbr );

    }

    /**
     * Test whether the getAll() method returns an empty arraylist when it can't
     * retrieve data from the database.
     */
    @Test
    void getAllBookingsReturnEmptyArrayListWhenDBIsEmpty() throws SQLException {

        ArrayList<Booking> dbr = new ArrayList<>();
        when( this.persistenceMock.load() ).thenReturn( dbr );

        List<Booking> t = this.persistenceMock.load();

//        assertThat(this.bookingManager.getAll()).isEqualTo(dbr);
    }

}
