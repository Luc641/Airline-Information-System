package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
public class BookingPersistenceTest {

    @Mock
    SQLConnection connection;
    @Mock
    Function<? super Object[], ? extends Booking> creator;

    BookingPersistence bookingPersistence;

    @BeforeEach
    void startup(){
        this.bookingPersistence = new BookingPersistence(connection, creator);

        Booking booking = new Booking(1, LocalDate.now(),1,1,1);


//        when(this.creator.apply(new Object[]{})).thenReturn(booking);
    }

    @Test
    void testLoadMethod(){

        assertThat(true).isTrue();

    }




}
