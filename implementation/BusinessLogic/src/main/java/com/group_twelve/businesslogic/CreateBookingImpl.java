package com.group_twelve.businesslogic;

import com.group_twelve.entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateBookingImpl {

    private int tCount;
    private int totalPrice;

    private List<Flight> foundRoutes = new ArrayList<>();
    private List<selectedRoutes> selectedRoutesList = new ArrayList<>();
    private List<Option> foundOptions = new ArrayList<>();
    private List<Option> selectedOptions = new ArrayList<>();

    AirportManager apm;
    RouteManager rm;
    FlightManager fm;
    BookingManager bm;
    CustomerManager cm;
    TicketManager tm;
    OptionManager om;

    public CreateBookingImpl(AirportManager apm, RouteManager rm, FlightManager fm, BookingManager bm, CustomerManager cm, TicketManager tm, OptionManager om) {
        this.apm = apm;
        this.rm = rm;
        this.fm = fm;
        this.bm = bm;
        this.cm = cm;
        this.tm = tm;
        this.om = om;
        getAllOptions();
    }

    public void searchFlightRoutes(String arrivalAirportName, String departureAirportName, String nrOfTickets) {

        // Validate input
        ArrayList<String> validatedInput = bm.validateInput(arrivalAirportName, departureAirportName, nrOfTickets);

        // If empty, return empty arraylist.
        if (validatedInput.isEmpty()) {
            throw new IllegalArgumentException("Please correct input");
        } else {
            String depAirport = validatedInput.get(0);
            String arAirport = validatedInput.get(1);
            this.tCount = Integer.parseInt(validatedInput.get(2));

            // Get flight route(s) that have matching airports
            List<Route> routes = rm.getRouteBasesOnAirports(apm.getAirportId(depAirport), apm.getAirportId(arAirport));
            List<Integer> flightIds = routes.stream().map(Route::getFlightID).collect(Collectors.toList());

            // Collect all flights and add them to the foundRoutes list.
            flightIds.stream().forEach(id -> foundRoutes.add(fm.getFlightById(id)));

            // Return the found routes list
//            return foundRoutes;
        }
    }

    public void selectRoute(Flight f) {

        // Check for potentional overlap. If there is an overlap, send the existing selected flights back.
        if (selectedRoutesList.size() > 0 && fm.checkForFlightOverlap(selectedRoutesList.get(selectedRoutesList.size() - 1), f)) {
            throw new IllegalArgumentException("The flights overlap!");
        }

        // Check if the flight hasn't been inserted into the list already.
        long matchingFlightsCount = selectedRoutesList.stream()
                .map(selectedRoutes::getFlightID)
                .filter(v -> v == f.getID())
                .count();

        // If 0, then no matching flights have already been selected.
        if (matchingFlightsCount == 0) {
            // Remove flight from the found-flights tableview
            for (int i = 0; i < foundRoutes.size(); i++) {
                if (foundRoutes.get(i).getID() == f.getID()) {
                    foundRoutes.remove(i);
                    break;
                }
            }

            // Create a new selectedRoute entity.
            selectedRoutes sl = new selectedRoutes(f.getID(), f.getPlane(), f.getArrivalAirport(), f.getDepartureAirport(), f.getDepartureTime(), f.getArrivalTime(), f.getFlightPrice(), this.tCount);

            // Add to the selected routes list
            selectedRoutesList.add(sl);

            // Update total price
            updatePrice();

            // Return
//            return selectedRoutesList;

        }
        // Flight has already been selected, return original list
//        return selectedRoutesList;
    }

    public void removeSelectedRoute(selectedRoutes r) {
        // Remove flight from the selectedRoutes list
        for (int i = 0; i < selectedRoutesList.size(); i++) {
            if (selectedRoutesList.get(i).getFlightID() == r.getFlightID()) {
                selectedRoutesList.remove(i);
                break;
            }
        }

        // Convert the selectedRoutes object back to a flight object
        Flight t = new Flight(r.getFlightID(), r.getPlane(), r.getArrivalDateTime(), r.getDepartureDateTime(), r.getPrice(), r.getArrivalAirport(), r.getDepartureAirport());
        // Add flight to the possible routes list
        foundRoutes.add(t);

        // Update total price
        updatePrice();

    }

    public void saveBooking(String customerName) {

        // Prepare variable
        boolean bookingSaveSuccess = true;
        boolean ticketSaveSuccess = true;

        // Get customer details
        if (customerName.equals("")) {
            throw new IllegalArgumentException("Please enter customer name.");
        }

        // Create customer entity
        Customer customer = cm.getOrCreateCustomerByName(cm.capitalizeName(customerName));

        if (customer.getID() == -1) {
            throw new RuntimeException("Customer couldn't be found or created.");
        }

        // Loop through the selected flights and create the booking.
        for (int i = 0; i < selectedRoutesList.size(); i++) {
            selectedRoutes sl = selectedRoutesList.get(i);

            // Create tickets (ID = -1 because it isnt used for the insertion and we dont have it yet at this stage)
            List<Integer> optionIds = selectedOptions.stream().map(Option::getID).collect(Collectors.toList());

            for (int j = 0; j < tCount; j++) {
                Ticket ticket = new Ticket(-1, -1, sl.getFlightID(), optionIds, customer.getID());
                ticketSaveSuccess = tm.save(ticket);

                if(!ticketSaveSuccess){
                    throw new RuntimeException("Something went wrong with the creation of the ticket(s)");
                }

            }

            // Create and save the booking
            Booking b = new Booking(LocalDate.now(), sl.getFlightID(), 1, 1);
            bookingSaveSuccess = bm.save(b);

            if (!bookingSaveSuccess) {
                throw new RuntimeException("Something went wrong with the creation of the booking.");
            }
        }
    }

    public void selectOption(Option option){

        // Check whether or not the option has been selected already
        Long matching = selectedOptions.stream().map(Option::getID).filter(v -> v == option.getID()).count();

        if(matching >= 1){
            throw new RuntimeException("Option has already been selected");
        }else{
            // Remove flight from the found options table
            for (int i = 0; i < foundOptions.size(); i++) {
                if(foundOptions.get(i).getID() == option.getID()){
                    foundOptions.remove(i);
                    break;
                }
            }
            // Add the option to the selected options list
            selectedOptions.add(option);

            // Update total price
            updatePrice();

//            return selectedOptions;
        }

    }

    public void removeSelectedOption(Option option){
        // Remove from selected list
        for (int i = 0; i < selectedOptions.size(); i++) {
            if(selectedOptions.get(i).getID() == option.getID()){
                selectedOptions.remove(i);
                break;
            }
        }

        // Add to found list
        foundOptions.add(option);

        // Update total price
        updatePrice();

//        return selectedOptions;
    }

    public void getAllOptions(){
        foundOptions.addAll(om.getAll());
    }

    private void updatePrice(){
        int newPrice = 0;

        // Get all the prices from the selectedRoutes.
        newPrice += selectedRoutesList.stream()
                .map(selectedRoutes::getPrice).mapToInt(v -> v).sum();

        // Same for the selected options
        newPrice += selectedOptions.stream()
                .map(Option::getPrice).mapToInt(v -> v).sum();

        // Multiply the cost by amount of tickets.
        if(tCount <= 1) {
            newPrice = newPrice * 1;
        }else{
            newPrice = newPrice * tCount;
        }
        // Display the new price.
        this.totalPrice = newPrice;

    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<Flight> getFoundRoutes() {
        return foundRoutes;
    }

    public List<selectedRoutes> getSelectedRoutesList() {
        return selectedRoutesList;
    }

    public List<Option> getFoundOptions() {
        return foundOptions;
    }

    public List<Option> getSelectedOptions() {
        return selectedOptions;
    }
}
