CREATE TABLE Airport (
  ID          SERIAL NOT NULL, 
  airportName varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Booking (
  ID     SERIAL NOT NULL, 
  "Date" time(7) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Flight (
  ID            SERIAL NOT NULL, 
  flightNr      int4 NOT NULL, 
  arrivalTime   time(7) NOT NULL, 
  departureTime time(7) NOT NULL, 
  flightPrice   int4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE flightRoute (
  ID        SERIAL NOT NULL, 
  routeName varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Options (
  ID         SERIAL NOT NULL, 
  optionName varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Plane (
  ID           SERIAL NOT NULL, 
  airplaneType int4 NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE saleOfficer (
  ID       SERIAL NOT NULL, 
  email    varchar(255) NOT NULL, 
  password varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE salesEmployee (
  ID       SERIAL NOT NULL, 
  email    varchar(255) NOT NULL, 
  password varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE salesManager (
  ID       SERIAL NOT NULL, 
  email    varchar(255) NOT NULL, 
  password varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Ticket (
  ID     SERIAL NOT NULL, 
  seatNr int4 NOT NULL, 
  PRIMARY KEY (ID));
