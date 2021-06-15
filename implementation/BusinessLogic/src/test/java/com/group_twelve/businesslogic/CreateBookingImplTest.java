package com.group_twelve.businesslogic;

import com.group_twelve.entities.*;
import com.group_twelve.persistence.Persistence;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.api.SoftAssertions;

public class CreateBookingImplTest {

    // Mock all managers and persistences needed
    @Mock
    Persistence<Airport> airportPersistenceMock;

    AirportManager airportManagerMock;
    @Mock
    Persistence<Route> routePersistenceMock;

    RouteManager routeManagerMock;
    @Mock
    Persistence<Flight> flightPersistenceMock;

    FlightManager flightManagerMock;
    @Mock
    Persistence<Booking> bookingPersistenceMock;

    BookingManager bookingManagerMock;
    @Mock
    Persistence<Customer> customerPersistenceMock;

    CustomerManager customerManagerMock;
    @Mock
    Persistence<Ticket> ticketPersistenceMock;

    TicketManager ticketManagerMock;
    @Mock
    Persistence<Option> optionPersistenceMock;

    OptionManager optionManagerMock;

    selectedRoutes selectedRoutesMock;
    Flight flightMock;
    Plane planeMock;

    // SUT
    CreateBookingImpl sut;

    @BeforeEach
    public void setup(){

        this.airportManagerMock = mock(AirportManager.class);
        this.routeManagerMock = mock(RouteManager.class);
        this.bookingManagerMock = mock(BookingManager.class);
        this.customerManagerMock = mock(CustomerManager.class);
        this.ticketManagerMock = mock(TicketManager.class);
        this.optionManagerMock = mock(OptionManager.class);
        this.flightManagerMock = mock(FlightManager.class);

        this.selectedRoutesMock = mock(selectedRoutes.class);
        this.flightMock = mock(Flight.class);
        this.planeMock = mock(Plane.class);

        // Train mocks on the most often used methods.
        trainOftenUsedMethods();
        setupEntityMocks();

        // Init the SUT.
        this.sut = new CreateBookingImpl(airportManagerMock,routeManagerMock,flightManagerMock,bookingManagerMock,customerManagerMock,ticketManagerMock,optionManagerMock);
        this.sut.tCount = 1;
    }

    private void setupEntityMocks() {
        // FLIGHTMOCK
        when(flightMock.getID()).thenReturn(1);
        when(flightMock.getPlane()).thenReturn(planeMock);
        when(flightMock.getArrivalAirport()).thenReturn(new Airport(1, "arrival"));
        when(flightMock.getDepartureAirport()).thenReturn(new Airport(2, "departure"));
        when(flightMock.getDepartureTime()).thenReturn(LocalDateTime.now());
        when(flightMock.getArrivalTime()).thenReturn(LocalDateTime.now().plusHours(2));
        when(flightMock.getFlightPrice()).thenReturn(100);

        // SELECTEDROUTEMOCK
        when(selectedRoutesMock.getFlightID()).thenReturn(2);
        when(selectedRoutesMock.getPlane()).thenReturn(planeMock);
        when(selectedRoutesMock.getArrivalAirport()).thenReturn(new Airport(3, "arrival3"));
        when(selectedRoutesMock.getDepartureAirport()).thenReturn(new Airport(4, "departure4"));
        when(selectedRoutesMock.getDepartureDateTime()).thenReturn(LocalDateTime.now());
        when(selectedRoutesMock.getArrivalDateTime()).thenReturn(LocalDateTime.now().plusHours(10));
        when(selectedRoutesMock.getPrice()).thenReturn(400);
    }

    public void trainOftenUsedMethods(){
        // om.getAll()
        ArrayList<Option> options = new ArrayList<>();
        options.add(new Option(1,"option1",10));
        options.add(new Option(2,"option2",1000));
        options.add(new Option(3,"option3",5));
        when(optionManagerMock.getAll()).thenReturn(options);

        // CUSTOMER
        when(customerManagerMock.capitalizeName(any())).thenReturn("Erik");
        when(customerManagerMock.getOrCreateCustomerByName(any())).thenReturn(new Customer(1, "Erik"));
    }

    public List<String> capitalizeFirstLetterOfWord(List<String> args){
        return args.stream().map(v -> v.substring(0, 1).toUpperCase() + v.substring(1)).collect(Collectors.toList());
    }

    public String capitalizeFirstLetterOfWord(String arg){
        return capitalizeFirstLetterOfWord(List.of(arg)).get(0);
    }

    /**
     * This test provides two valid airports and a ticket count. This test should not throw an error and the expected flights should be found in the
     * foundRoutes table.
     */
    @ParameterizedTest
    @CsvSource(value = {
            // arrivalname, arrivalID, departurename, departureID, ticketcount, expected flightID
            "amsterdam,1,berlin,2,1,1",
            "dubai,3,amsterdam,1,10,3"
    })
    void searchForFlightRoutesWithoutErrors(String arrival, int arrivalID, String departure, int departureID, String ticketCount, int flightID){

        // Setup mocks

        // bm.validateInput()
        ArrayList<String> validateInputReturnList = new ArrayList<>();
        validateInputReturnList.add(capitalizeFirstLetterOfWord(arrival));
        validateInputReturnList.add(capitalizeFirstLetterOfWord(departure));
        validateInputReturnList.add(ticketCount);
        when(bookingManagerMock.validateInput(arrival,departure,ticketCount)).thenReturn(validateInputReturnList);

        // apm.getAirportId()
        when(airportManagerMock.getAirportId(arrival)).thenReturn(arrivalID);
        when(airportManagerMock.getAirportId(departure)).thenReturn(departureID);

        // rm.getRouteBasedOnAirports()
        ArrayList<Route> routeReturnList = new ArrayList<>();
        routeReturnList.add(new Route(1,"name", flightID));
        when(routeManagerMock.getRouteBasesOnAirports(departureID,arrivalID)).thenReturn(routeReturnList);

        // fm.getFlightById
        when(flightManagerMock.getFlightById(flightID)).thenReturn(new Flight(flightID));

        // Assert that everything went correctly and no errors has been thrown.
        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.searchFlightRoutes(arrival,departure,ticketCount);
        };

        assertThatCode(code)
                .doesNotThrowAnyException();
    }

    /**
     * Test whether or not the IllegalArgumentException occurs if a false input has been given.
     */
    @Test
    void searchForFlightRoutesShouldThrowIllegalArgumentException(){

        ArrayList<String> returnList = new ArrayList<>();
        when(bookingManagerMock.validateInput("a","b","e")).thenReturn(returnList);

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.searchFlightRoutes("a","b","e");
        };

        assertThatThrownBy(code).isExactlyInstanceOf(IllegalArgumentException.class);

    }

    /**
     * Test if the overlap filter works. An error SHOULD occur.
     */
    @Test
    void selectRouteMethodShouldThrowExceptionOnFlightOverlap(){

        // Fill the selectedRoutesList to pass one of the checks to even get to the overlap check.
        this.sut.selectedRoutesList.add(new selectedRoutes());

        // Train mocks
        when(flightManagerMock.checkForFlightOverlap(any(), any())).thenReturn(true);

        ThrowableAssert.ThrowingCallable code = () -> {
           this.sut.selectRoute(new Flight());
        };

        assertThatThrownBy(code).isExactlyInstanceOf(IllegalArgumentException.class);

    }

    /**
     * Test if it's possible to sucessfully select a route.
     */
    @Test
    void selectRouteShouldThrowErrorWhenFlightHasAlreadyBeenSelected(){

        // Fill certain lists in the class so that the method can be executed properly.
        this.sut.selectedRoutesList.add(selectedRoutesMock);
        when(selectedRoutesMock.getFlightID()).thenReturn(1);

        // Try to insert a new route which is identical to the one already in the list.
        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.selectRoute(flightMock);
        };

        assertThatThrownBy(code).isExactlyInstanceOf(RuntimeException.class);

    }

    /**
     * This test sucessfully selects a new route. An error should therefor not occur.
     */
    @Test
    void selectingRouteWithoutExceptionShouldBePossible(){

        this.sut.selectedRoutesList.add(selectedRoutesMock);
        this.sut.foundRoutes.add(flightMock);
        this.sut.tCount = 1;

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.selectRoute(flightMock);
        };


        assertThatCode(code).doesNotThrowAnyException();

    }

    /**
     * Test if its possible to remove an already selected route.
     */
    @Test
    void verifyThatRemovingASelectedRouteIsPossible(){
        // fill the selected route
        this.sut.selectedRoutesList.add(selectedRoutesMock);

        // Expected entity that its transformed into
        Flight expected = new Flight(selectedRoutesMock.getFlightID(), selectedRoutesMock.getPlane(), selectedRoutesMock.getArrivalDateTime(), selectedRoutesMock.getDepartureDateTime(), selectedRoutesMock.getPrice(), selectedRoutesMock.getArrivalAirport(), selectedRoutesMock.getDepartureAirport());

        this.sut.removeSelectedRoute(selectedRoutesMock);

        // Assert that the selectedRoutes mock has been transformed in a flight and that its in the foundRoutes list.
        assertThat(this.sut.getFoundRoutes().get(0).getID()).isEqualTo(expected.getID());

    }

    /**
     * Test that the savebooking method throws an error if no customer name has been inserted
     */
    @Test
    void saveBookingMethodShouldThrowExceptionIfNoCustomerNameHasBeenEntered(){

        String input = "";

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.saveBooking(input);
        };

        assertThatThrownBy(code).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    /**
     * Test that the savebooking method throws an error if a customer couldn't be fetched or created.
     */
    @Test
    void saveBookingMethodShouldThrowExceptionWhenCustomerCouldNotBeFetched(){
        String input = "erik";

        when(customerManagerMock.capitalizeName("erik")).thenReturn("Erik");
        when(customerManagerMock.getOrCreateCustomerByName(any())).thenReturn(new Customer(-1, "null"));

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.saveBooking(input);
        };

        assertThatThrownBy(code).isExactlyInstanceOf(RuntimeException.class);
    }

    /**
     * Test that the savebooking method throws an error if something goes wrong with the creation/insertion of the tickets.
     */
    @Test
    void saveBookingMethodShouldThrowExceptionWhenTicketsCantBeSaved(){
        // fill selected routes list
        this.sut.selectedRoutesList.add(selectedRoutesMock);

        when(ticketManagerMock.save(any())).thenReturn(false);

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.saveBooking("jan");
        };

        assertThatThrownBy(code).isExactlyInstanceOf(RuntimeException.class);

    }

    /**
     * Test that the savebooking method throws an error if something goes wrong with the creation/insertion of the booking.
     */
    @Test
    void saveBookingMethodShouldThrowExceptionWhenBookingCantBeSaved(){
        // fill selected routes list
        this.sut.selectedRoutesList.add(selectedRoutesMock);

        when(ticketManagerMock.save(any())).thenReturn(true);
        when(bookingManagerMock.save(any())).thenReturn(false);

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.saveBooking("jan");
        };

        assertThatThrownBy(code).isExactlyInstanceOf(RuntimeException.class);
    }

    /**
     * Test if sucessfully inserting a new booking is possible.
     */
    @Test
    void succesfullyEnteringABookingShouldNotThrownAnException(){
        // fill selected routes list
        this.sut.selectedRoutesList.add(selectedRoutesMock);
        // fill the selectedOptions list with one
        this.sut.selectedOptions.add(this.sut.foundOptions.get(0));

        when(ticketManagerMock.save(any())).thenReturn(true);
        when(bookingManagerMock.save(any())).thenReturn(true);

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.saveBooking("jan");
        };

        assertThatCode(code).doesNotThrowAnyException();
    }

    /**
     * Verify that selecting an already existing option throws an error.
     */
    @Test
    void selectingAnAlreadySelectedOptionShouldThrowException(){
        this.sut.selectedOptions.add(this.sut.foundOptions.get(0));

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.selectOption(this.sut.foundOptions.get(0));
        };

        assertThatThrownBy(code).isExactlyInstanceOf(RuntimeException.class);
    }

    /**
     * Verify that adding a new option which hasn't been selected yet is possible.
     */
    @Test
    void selectingAnOptionShouldNotThrowException(){
        this.sut.selectedOptions.add(this.sut.foundOptions.get(0));

        ThrowableAssert.ThrowingCallable code = () -> {
            this.sut.selectOption(this.sut.foundOptions.get(1));
        };

        assertThatCode(code).doesNotThrowAnyException();
    }

    /**
     * Verify that removing an option is possible.
     */
    @Test
    void verifyThatRemovingAnOptionIsPossible(){
        this.sut.selectedOptions.add(this.sut.foundOptions.get(0));
        this.sut.selectedOptions.add(this.sut.foundOptions.get(1));

        this.sut.removeSelectedOption(this.sut.foundOptions.get(1));

        // Assert that the removed option isn't present in the selectedOptions list anymore
        assertThat(this.sut.getSelectedOptions()).doesNotContain(this.sut.foundOptions.get(1));
    }

}
