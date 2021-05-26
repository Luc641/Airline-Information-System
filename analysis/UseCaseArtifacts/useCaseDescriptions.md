
| Name: | Provide price reductions |
|-|-|
| Actor: | Sales officer |
| Description: | Sales officer enable temporary price reductions |
| Pre-condition: | Sales Officer registered an upcomming flight and started the sales proccess for that registered flight. |
| Main success scenario: | 1.Sales officer ask the system for available price reductions for the registered flight.<br>2.The system outputs the available discounts to choose from, „static discount” or ”dynamic discount”<br>3.The sales officer selects which discount is available<br>4.The system add the discount to the registered flight.<br>5.The system informs the sales officer that the price reduction was made |
| Result: | The sales officer enabled the price reduction successfully for the registered flight. |
| Extensions: | 3a. the sales officer selected  ”static discounts” <br>    1.The system output available price reductions.<br>    2.Sales officer selects the available discount. Returns to step 4.<br><br>3b. the sales officer selected  ”dynamic discounts”<br>    1.The system output available price reductions.<br>    2.Sales officer selects the available discount. Returns to step 4. |
| Exceptions: | 1.System output : ”No discounts available ”.<br>  1.1 Use case ends here. |



<br><br>
| Name: | Create bookings |
|-|-|
| Actor: | Sales officer |
| Description: | Sales employee creates the booking for the customer |
| Pre-condition: | 1. The sales employee needs to be logged in <br>2. Flight needs to exist in the system |
| Main success scenario: | 1.Sales employee looks up flights <br>2.System gives option to book a flight <br>3.Sales employee selects flight <br>4.System asks for data (name, e-mail, id number, ...) <br>5.Sales employee enters the data |
| Result: | Booking has been made |
| Extensions: | 5a. Sales employee choses to add options for the booking <br>  1.System lets sales employee edit the ticket options |
| Exceptions: | - |



<br><br>
| Name: | Register flight |
|-|-|
| Actor: | Sales officer |
| Description: | Sales officer wants to be able to register upcoming flights |
| Pre-condition: | 1. Actor is logged in <br> 2. Actor has permission to register a flight |
| Main success scenario: | 1. Actor clicks on register flight. <br>2. System returns the register flight page.<br>3. Actor fills in data required to register a flight. <br>4.System checks if all the information has been correctly fully filled.<br>5. System checks if the information entered matches an already registered flight<br>6. System registers the flight. |
| Result: | Actor has registered a flight successfully|
| Extensions: | - |
| Exceptions: | 4.0 System message: “NOT ALL FIELDS ARE FILLED IN!”.<br>4.1 system outputs error massages  where the information have been incorrectly added.<br> 4.2 go back to step 3 .<br>5.System message: “Flight is already registered” <br>5.1 go back to step 3 |



<br><br>
| Name: | reconfigure booking options |
|-|-|
| Actor: | Sales employee |
| Description: | A sales employee selects certain flight options, to optimize a customers flight experience |
| Pre-condition: | Have a customer that books a flight |
| Main success scenario: | 1.Sales employee opens the program <br>2.System displays the log-in site <br>3.Sales employee logs-in with his credentials <br>4.System checks for valid credentials <br>5.System displays the sales employee homepage with different functions <br>6.Sales employee chooses the customers request function <br>7.System displays different requests of customers <br>8.Sales employee chooses the latest one <br>9.System displays the request in detail <br>10.Sales employee looks at the customers request <br>11.Sales employee goes back to the homepage and looks up the relating flight <br>12.Sales employee changes the options for the flight <br>13.Sales employee changes the options of the flight for the customer <br>14.Sales employee goes back to the customer requests and confirms his request |
| Result: | Sales employee has successfully changes options for the customer |
| Extensions: | - |
| Exceptions: | 4.1 The credentials are incorrect <br>4.2 System out prints:”Invalid credentials” <br>4.3 Use case ends here <br>7.1 No new request has been filed <br>7.2 Use case ends here <br>13. The options cannot be changed in a way a customer wants to <br>13.1 Sales employee declined the customers request <br>13.2 Use case ends here |



<br><br>
| Name: | Sell tickets |
|-|-|
| Actor: | Sales officer |
| Description: | Selling a ticket to a customer |
| Pre-condition: | The sales officer is logged in and authorized to sell tickets |
| Main success scenario: | 1.The sales officer asks the customer which flight he would like to book  <br>2.The sales officer asks for the customers details <br>3.The sales officer loads up  available flights from the system matching the customer s request for date/time <br>4.The sales officer |
| Result: | A ticket has been sold |
| Extensions: | - |
| Exceptions: | - |


<br><br>
| Name: | Revenue numbers |
|-|-|
| Actor: | Sales manager |
| Description: | - |
| Pre-condition: | Logged in as a Sales manager |
| Main success scenario: | 1.Actor opens Management Dashboard <br>2.System calculates revenue numbers from Sales and other Entities <br>3.System presents the Actor the Management Dashboard with the revenue numbers |
| Result: | Actor successfully opened Management Dashboard |
| Extensions: | 2a. Nothing to calculate with <br>1. System presents the Actor the Management Dashboard with default value |
| Exceptions: | - |



<br><br>
| Name: | Tickets sold |
|-|-|
| Actor: | Sales manager |
| Description: | Looking at the sold tickets for a flight |
| Pre-condition: | The sales manager is logged in |
| Main success scenario: | 1.The actor selects the option sales from the Management Dashboard <br>2.System returns the list of flight <br>3.The actor selects which flight the sold tickets need to be displayed <br>4.System returns list of tickets sold for that flight |
| Result: | Actor successfully displayed the sold tickets |
| Extensions: | - |
| Exceptions: | - |



<br><br>
|Name: | Receive price reductions |
|-|-|
| Actor: | Sales Officer |
| Description: | Sales officer receives the price reduction for a ticket |
| Pre-condition | Sales officer logged in |
| Main success scenario: | 1. The sales officer selects a flight <br> 2. The system loads the details of the flight <br> 3. The sales officer looks at the price reduction |
| Extensions: | - |
| Exceptions: | 1a. The flight doesnt exist <br> 1. The system displays an error message|



<br><br>
|Name: | Statistics |
|-|-|
| Actor: | Sales Manager |
| Description: | Sales Manager wants to view the Statistics on the management board |
| Pre-condition | Sales Manager is logged in |
| Main success scenario: | 1. The sales Manager selects management board <br> 2. The system loads the management board <br> 3. The sales Manager chooses what statistic he/she wants to see <br> 4. The System loads the statistic for chosen selection |
| Extensions: | - |
| Exceptions: | 4. System Message: "No statistics to show" <br> 4a. End of use case |



<br><br>
|Name: | Lookup flights | 
|-|-|
| Actor: | Sales employee |
| Description: | A sales employee wants to lookup available flights in order to create bookings for customers |
| Pre-condition | There must be registered flights and the sales process to be started |
| Main success scenario: | 1. The actor asks the system to show available upcoming flights.<br> 2. The system outputs: "choose date and time"<br> 3. THe sales employee choose the desired date and time for the registered upcoming flight.<br> 4. The system displays the available flights. |
| Extensions: | - |
| Exceptions: | 4. System message: "There are no available flights for the chosen date and time." <br> 4a. Return to step 3. |


