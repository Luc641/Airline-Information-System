package com.group_twelve.businesslogic;
import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.BookingManager;
import com.group_twelve.entities.Booking;
import com.group_twelve.persistence.BookingPersistence;
import com.group_twelve.persistence.Persistence;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.mockito.Mockito.*;


public class BookingManagerTest {

    BookingManager bookingManager;

    @Mock
    AirportManager mockAirportManager;

    @Mock
    Persistence persistenceMock;

    @Mock
    BookingPersistence bookingPersistence;

    @BeforeEach
    public void setup(){
        //Setup real classes
        this.bookingManager = new BookingManager((BookingPersistence) this.persistenceMock);
    }

    /**
     * This method checks whether the validateInput method in the createBookingMain controller works
     * correctly.
     */
    @ParameterizedTest
    @CsvSource(value ={
            // Arrival airport, Departure airport, nr of tickets || expected arrival, departure, ticket
            "berlin,amsterdam,3,Berlin,Amsterdam,3",
            "amsterdam,Dubai,2,Amsterdam,Dubai,2"
    })
    public void correctInputValidationShouldBeAcceptedTest(String a1, String a2, String ticketCount,
                                                           String ea1, String ea2, String eTicketCount){


        ArrayList<String> returnedValues = this.bookingManager.validateInput(a1, a2, ticketCount);

        SoftAssertions.assertSoftly(v -> {
            v.assertThat(returnedValues.get(0)).as("The first letter should be capitalized").isEqualTo(ea1);
            v.assertThat(returnedValues.get(1)).as("The first letter should be capitalized").isEqualTo(ea2);
            v.assertThat(returnedValues.get(2)).as("This should be a letter").isEqualTo(eTicketCount);
        });

    }

    /**
     * Test if wrong inputs are detected and filtered out. If a wrong input has been detected it should
     * return an empty arraylist.
     */
    @ParameterizedTest
    @CsvSource(value ={
            // Arrival airport, Departure airport, nr of tickets
            "b1rlin,amsterdam,1",
            "berlin,amst3rd@m,3",
            "berlin,amsterdam,e",
            "b1rl1n,@mst3rd@m,r"
    })
    public void incorrectInputValidationShouldReturnEmptyArraylist(String a1, String a2, String ticketCount){
        ArrayList<String> r = this.bookingManager.validateInput(a1,a2,ticketCount);
        assertThat(r.isEmpty()).isTrue();
    }


    /**
     * Test whether the creator returns a valid booking entity whenever it gets
     * called from the getAll() method. (The persistence is mocked).
     */
    @Test
    public void getAllBookingsReturnValidBookingEntity(){

        // Train the Booking persistence to return two mocked rows from the database.
        ArrayList<Booking> dbr = new ArrayList<>();
        dbr.add(new Booking(1, LocalDate.now(),1,1,1));
        dbr.add(new Booking(2,LocalDate.now(), 2,3,4));
        when(this.bookingPersistence.load()).thenReturn(dbr);

        assertThat(this.bookingManager.getAll()).isSameAs(dbr);

    }

}
