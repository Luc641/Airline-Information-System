# Employees

Within the employees we have several roles as employees.
-	Sales officer
-	Sales employee
-	Sales manager
	
## Sales officer:
A Sales Officer should be able to:
*	Register upcoming flights.
*	Start the sales process for registered flights
*	Enable temporary price reduction. 
    * Discount can be static (E.g. 10% reduction for limited time)
    * Discount can be dynamic (E.g. based on number of sun hours at the destination, on the day before the booking is made – this needs to be retrieved from an external API.)

## Sales Employee:
A Sales Employee should be able to:
*	Lookup available flights
*	Create bookings for one or more persons.
    *	Booking can have (paid) options like, extra legroom, food, luggage and seats chosen.
    *	Prices need to be calculated by the software and depend on the chosen options, class, availability of the seats in that class, applicable discounts and the number of days left before the flight departs.
*	There are multiple Sales Employees. The application needs to cope with concurrent users, potentially trying to book limited seats for the same flight.

## Sales Manager:
A Sales Manager needs:
*	A management dashboard.
    *	Important management key performance indicators
        *	for each route, sales managers want to view total revenue numbers, number of tickets sold in each class, and statistics on all options sold.
