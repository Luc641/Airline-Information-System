# Register Flight
| Step Number | Description |
|-|-|
|1|check system behaviour when valid required data is entered|
|2|check system behaviour when invalid required data is entered|
|3|check system behaviour when "Submit" button is pressed and all required data is not entered|
|4|check system behaviour when "Submit" button is pressed and all required data is  entered|
|5|check system behaviour when "cancel" button is pressed and all required data is not entered
|6| check system behaviour when "go Back" button is pressed |

---

# Lookup Flight
| Step Number | Description |
|-|-|
|1|Assert that flights have been saved before looking them up.|
|2|Assert that flights are going to be correctly displayed.|
|3|Assert that there are available flights.|
|4|Assert that the filter that has been chosen to search with exists.|
|Exception:|Assert that there aren't available flights,meaning the list is empty.|

---

# Reschedule Flight
| Step Number | Description |
|-|-|
|1|Lookup flight|
|2|Choose flight|
|3|Choose to edit flight|
|4|Place flight in a new schedule|
|5|Assert that the flight time is available for the flight|
|6|Assert that the plane is available for the flight|
|7|Assert that the crew is available for the flight|

---

# Create Booking
| Step Number | Description |
|-|-|
|1|Lookup available flights between entered airports|
|2|Choose route / flight|
|3|choose option|
|4|fill in customer data and nr of tickets|
|5|Assert that the booking has been created|

---

# Change Booking Options
| Step Number | Description |
|-|-|
|1|Create a Booking|
|2|Set specific options for the booking|
|3|Save the booking|
|4|Edit the bookings options|
|5|Assert that the options of the booking have been changed to the inputted options|

---

# Provide Price Reduction
| Step Number | Description |
|-|-|
|1|Lookup price reduction options for specific registered flight|
|2|Set discount option (static or dynamic) for specific flight|
|3|Save the added discount|
|4| Assert that the registered flight with discount shows the right (new) calculated<br> total price for the flight|

---

# Calculating Flight Price Works Correctly
Using a ParameterizedTest do:
| Step Number | Description |
|-|-|
|1|Register a Flight|
|Optional: 2|Create a Price Reduction|
|3|Create a Booking for this Flight|
|Optional: 4|Add Options to the Booking|
|5|Get total price from calculation|
|6|Assert that the total price from the calculation is equal to the expected price|

---
