package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.BookingManager;
import com.group_twelve.persistence.BookingPersistence;
import com.group_twelve.persistence.Persistence;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.isA;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import com.group_twelve.entities.Booking;
import org.testfx.framework.junit5.ApplicationExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
public class CreateBookingTest {

    BookingManager bookingManager;

    @Mock
    AirportManager mockAirportManager;

    @Mock
    Persistence persistenceMock;

    @BeforeEach
    public void setup(){
        //Setup real classes
        this.bookingManager = new BookingManager((BookingPersistence) this.persistenceMock);
    }






}
