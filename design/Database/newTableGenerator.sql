DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
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
  options int not null,
  PRIMARY KEY (ID),
  FOREIGN key (options) REFERENCES Options(ID),
  FOREIGN KEY (flightID) REFERENCES Flight(ID)
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
  bookingDate timestamptz NOT NULL,
  flightRouteID int NOT NULL,
  employeeID int4 NOT NULL,
  priceReductionID int,
  PRIMARY KEY (ID),
  FOREIGN KEY (flightRouteID) REFERENCES FlightRoute(ID),
  FOREIGN KEY (employeeID) REFERENCES Employee(ID),
  FOREIGN KEY (priceReductionID) REFERENCES PriceReduction(ID)
);

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

INSERT INTO Plane(ID, planeTypeID) values
(1, 1);

INSERT INTO Plane(ID, planeTypeID) values
(2, 2);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(1, 1, '2021-06-23 19:10:25-07','2021-06-22 19:10:25-07', 100, 3, 6);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(2, 1, '2021-06-23 21:10:25-07','2021-06-23 20:10:25-07', 50, 9, 3);

INSERT INTO Flight(ID, planeID, arrivalTime, departureTime, flightPrice, arrAirportID, depAirportID) VALUES
(3, 2, '2021-06-25 05:10:25-07','2021-06-24 19:10:25-07', 2000, 8, 9);

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

INSERT INTO Booking(ID, bookingDate, flightRouteID, employeeID, priceReductionID) VALUES
	(1, '2020-04-21 08:51:22+1', 1, 1, 1);
