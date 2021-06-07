DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE Customer(
  ID SERIAL NOT NULL,
  fullname varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Airport (
  ID SERIAL NOT NULL,
  airportName varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE employeeType (
  ID SERIAL NOT NULL,
  typeName varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE Employee (
  ID SERIAL NOT NULL,
  username varchar(255) NOT NULL,
  employeeType int4 NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (employeeType) REFERENCES employeeType(ID)
);
CREATE TABLE Options (
  ID SERIAL NOT NULL,
  optionName varchar(255) NOT NULL,
  price int not null,
  PRIMARY KEY (ID)
);

CREATE TABLE AirplaneType (
  ID SERIAL NOT NULL,
  typeName varchar(255) NOT NULL,
  primary key (ID)
);
CREATE TABLE Plane (
  ID SERIAL NOT NULL,
  planeTypeID int NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (planeTypeID) REFERENCES AirplaneType(ID)
);
CREATE TABLE PriceReductionType (
  ID SERIAL NOT NULL,
  typeName varchar(255) NOT NULL,
  description varchar(255),
  PRIMARY KEY (ID)
);
CREATE table PriceReduction (
  ID SERIAL NOT NULL,
  prName varchar(255) NOT NULL,
  description varchar(255),
  amount decimal(5, 4),
  prType int4 NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (prType) REFERENCES PriceReductionType(ID)
);

CREATE TABLE Flight (
  ID SERIAL NOT NULL,
  planeID int4 NOT NULL,
  arrivalTime timestamptz NOT NULL,
  departureTime timestamptz NOT NULL,
  flightPrice int4 NOT NULL,
  arrAirportID INT NOT NULL,
  depAirportID INT NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (planeID) REFERENCES Plane(ID),
  FOREIGN KEY (arrAirportID) REFERENCES Airport(ID),
  FOREIGN KEY (depAirportID) REFERENCES Airport(ID)
);
CREATE TABLE Ticket (
  ID SERIAL NOT NULL,
  seatNr int4 NOT NULL,
  flightID int not null,
  customerID int not null,
  PRIMARY KEY (ID),
  FOREIGN KEY (flightID) REFERENCES Flight(ID),
  FOREIGN KEY (customerID) REFERENCES Customer(ID)
);
CREATE TABLE FlightRoute (
  ID SERIAL NOT NULL,
  routeName varchar(255) NOT NULL,
  flightID int NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY(flightID) REFERENCES Flight(ID)
);

CREATE TABLE Booking (
  ID SERIAL NOT NULL,
  bookingDate date NOT NULL,
  flightRouteID int NOT NULL,
  employeeID int4 NOT NULL,
  priceReductionID int,
  PRIMARY KEY (ID),
  FOREIGN KEY (flightRouteID) REFERENCES FlightRoute(ID),
  FOREIGN KEY (employeeID) REFERENCES Employee(ID),
  FOREIGN KEY (priceReductionID) REFERENCES PriceReduction(ID)
);

CREATE TABLE TicketOptions(
  ID SERIAL NOT NULL,
  flightID int NOT NULL,
  optionId int NOT NULL,
  PRIMARY KEY(ID),
  FOREIGN KEY (flightID) REFERENCES Flight(ID),
  FOREIGN KEY (optionId) REFERENCES Options(ID)
)

INSERT INTO Airport(ID, airportName) VALUES
	(1, 'Frankfurt'),
	(2, 'Munich'),
	(3, 'Berlin'),
	(4, 'Paris'),
	(5, 'Brussels'),
	(6, 'New York'),
	(7, 'Shanghai'),
	(8, 'Dubai'),
	(9, 'Amsterdam'),
	(10, 'Madrid'),
	(11, 'Moscow'),
	(12, 'Rome');

INSERT INTO AirplaneType(ID, typeName) values
(1, 'Airbus a380');

INSERT INTO AirplaneType(ID, typeName) values
(2, 'Boeing 747');

INSERT INTO AirplaneType(ID, typeName) values
(3, 'Boeing 777-300ER');

INSERT INTO AirplaneType(ID, typeName) values
(4, 'Airbus A350-900');

INSERT INTO Plane(ID, planeTypeID) values
(1, 1);

INSERT INTO Plane(ID, planeTypeID) values
(2, 2);

INSERT INTO Plane(ID, planeTypeID) values
(3, 3);

INSERT INTO Plane(ID, planeTypeID) values
(4, 4);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(1, 1, '2021-06-23 19:10:25-07','2021-06-22 19:10:25-07', 100, 3, 6);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(2, 1, '2021-06-23 21:10:25-07','2021-06-23 20:10:25-07', 50, 9, 3);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(3, 2, '2021-06-25 05:10:25-07','2021-06-24 19:10:25-07', 2000, 8, 9);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(4, 2, '2021-06-13 05:10:25-07','2021-06-12 19:10:25-07', 200, 7, 1);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(5, 3, '2021-08-25 05:10:25-07','2021-08-24 19:10:25-07', 2500, 6, 2);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(6, 4, '2021-03-25 05:10:25-07','2021-03-24 19:10:25-07', 250, 5, 3);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(7, 3, '2021-05-25 05:10:25-07','2021-05-24 19:10:25-07', 3000, 4, 5);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(8, 1, '2021-04-15 05:10:25-07','2021-04-14 19:10:25-07', 100, 3, 6);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(9, 2, '2021-06-10 05:10:25-07','2021-06-09 19:10:25-07', 150, 2, 7);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(10, 3, '2021-06-09 05:10:25-07','2021-06-08 19:10:25-07', 220, 1, 9);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(11, 4, '2021-06-04 05:10:25-07','2021-06-03 19:10:25-07', 200, 8, 9);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(12, 4, '2021-06-15 05:10:25-07','2021-06-14 19:10:25-07', 260, 7, 8);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(13, 1, '2021-06-28 05:10:25-07','2021-06-27 19:10:25-07', 230, 8, 9);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(14, 2, '2021-06-29 05:10:25-07','2021-06-28 19:10:25-07', 280, 6, 9);

INSERT INTO FLIGHTROUTE (ID, routeName, flightID) VALUES
	(1, 'NYC->BER', 1);

INSERT INTO FLIGHTROUTE (ID, routeName, flightID) VALUES
	(2, 'BER->AMS', 2);

INSERT INTO FLIGHTROUTE (ID, routeName, flightID) VALUES
	(3, 'AMS->DUB', 3);

INSERT INTO EmployeeType (ID, typeName) VALUES
	(1, 'Sales Officer');

INSERT INTO Employee (ID, username, employeeType) VALUES
	(1, 'Employee1', 1);

INSERT INTO PRICEREDUCTIONTYPE (ID, typeName, description) VALUES
	(1, 'STATIC', 'static price reduction');

INSERT INTO PRICEREDUCTION (ID, prName, description, amount, prType) VALUES
	(1, '35% static price reduction', 'description', 0.35, 1);

-- INSERT INTO Booking(ID, bookingDate, flightRouteID, employeeID, priceReductionID) VALUES
-- 	(1, '2020-04-21 08:51:22+1', 1, 1, 1);

-- INSERT INTO Customer(ID, fullname) VALUES (1, 'Patrick');
-- INSERT INTO Customer(ID, fullname) VALUES (2, 'Luc');
-- INSERT INTO Customer(ID, fullname) VALUES (3, 'Timo');
-- INSERT INTO Customer(ID, fullname) VALUES (4, 'Alex');
-- INSERT INTO Customer(ID, fullname) VALUES (5, 'Alexandra');

INSERT INTO options(ID, price, optionName) VALUES (1, 10, 'extra legroom');
INSERT INTO options(ID, price, optionName) VALUES (2, 50, 'Unlimited drinks');
INSERT INTO options(ID, price, optionName) VALUES (3, 25, '10kg extra baggage');
