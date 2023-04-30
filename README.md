# Design a Parking Lot
This is a solution of Machine Coding (Low Level Design) of "Design a Parking Lot"



### Requirements:

* Customer looks for green signal on parking display*board to check the availability.
* Parking Automated System check for the available slot and assign a ticket to the customer.
* The ticket contains several information like vehicle number, date and time of arrival and available parking slot number.
* Parking Automated System then opens the entry barrier and update the status of parking display board.
* Customer parks the vehicle at the assigned parking spot.
* Customer scan the Ticket at exit, where Parking Automated System calculates the amount and generates bill for the parking to the customer.
* Customer makes the payment via cash or card.
* Parking Automated System opens the exit barrier and update the parking display*board.

### Assumptions after clarification from interviewer
* Parking has single Entry and single Exit point.
* Admin manages and configure the Automated System.
* Customers dont need to register
* for demo, parking charges will be per second basis
* opening*closing will together be one event : open gates


### Actors:
* Customer, Admin, Automated System

### Use Case Diagram:

* Customer:
  * Check availability on display board 
  * Scans ticket --> Makes payment

* Admin:
  * Add parking charges
  * add / remove parking spots

* App:
  * Check availabile slot 
  * Generate ticket 
  * generates bill <-- calculates the amount
 
* Automated system: 
  * scans vehicle details
  * Open/Close gates --> update display board 

### Class Diagram :

    Customer : 
        - vehicle number : Vehical number
        - ticket id 


    Admin :
        - id 
        - Name 
        - Add/Remove parking slots 
        - Add parking charges 


    Parking Slot : 
        - id 
        - floor  
        - status : occupied, availabile 


    Ticket :
        - id 
        - Vehicle number 
        - Enter time 
        - Exit time 
        - total charge  

    
    Parking lot :
        - Display board : Full, availabile 
        - parking charge  
        - Parking slots 
        - .
        .
        - perform entry -> verify availability, scan vehicle number, generate ticket, open gate, update display board 
        - perfom exit -> get ticket, calculate amount, generate bill, record payment, open gates, update display board             

