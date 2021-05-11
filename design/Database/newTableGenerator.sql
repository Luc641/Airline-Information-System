 
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
CREATE TABLE Flight (
  ID SERIAL NOT NULL,
  flightNr int4 NOT NULL,
  arrivalTime timestamptz NOT NULL,
  departureTime timestamptz NOT NULL,
  flightPrice int4 NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE FlightRoute (
  ID SERIAL NOT NULL,
  routeName varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE Options (
  ID SERIAL NOT NULL,
  optionName varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE Plane (
  ID SERIAL NOT NULL,
  airplaneType int4 NOT NULL,
  PRIMARY KEY (ID)
);
CREATE TABLE Ticket (
  ID SERIAL NOT NULL,
  seatNr int4 NOT NULL,
  PRIMARY KEY (ID)
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
  percentage decimal(5, 4),
  prType int4 NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (prType) REFERENCES PriceReductionType(ID)
);

CREATE TABLE Booking (
  ID SERIAL NOT NULL,
  bookingDate timestamptz NOT NULL,
  routeID int4 NOT NULL,
  employeeID int4 NOT NULL,
  priceReductionID int4,
  PRIMARY KEY (ID),
  FOREIGN KEY (routeID) REFERENCES FlightRoute(ID),
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
CREATE TABLE employeeType (
  ID SERIAL NOT NULL,
  typeName varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);


INSERT INTO FLIGHTROUTE (ID, routeName) VALUES
	(1, 'idk lol');

INSERT INTO EmployeeType (ID, typeName) VALUES
	(1, 'Sales Officer')

INSERT INTO Employee (ID, username, employeeType) VALUES
	(1, 'hi', 1);

INSERT INTO PRICEREDUCTIONTYPE (ID, typeName, description) VALUES
	(1, 'STATIC', 'static price reduction applied as is');

INSERT INTO PRICEREDUCTION (ID, prName, description, amount, prType) VALUES
	(1, 'prName', 'description', 0.33, 1);

INSERT INTO Booking(ID, bookingDate, routeID, employeeID, priceReductionID) VALUES
	(1, '2020-04-21 08:51:22+1', 1, 1, 1);

