/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import super_airways.Query_Database;

/**
 *
 * @author sanika
CREATE TABLE `flights` (
`FLIGHTNUMBER` int(11) NOT NULL,
`OPERATOR` varchar(20) NOT NULL,
`SOURCE` varchar(20) DEFAULT NULL,
`DESTINATION` varchar(20) DEFAULT NULL,
`SEATS_TOTAL` int(11) DEFAULT NULL,
`SEATS_TAKEN` int(11) DEFAULT NULL,
`COST` int(11) DEFAULT NULL,
`DEPARTURE` varchar(20) DEFAULT NULL,
`ARRIVAL` varchar(20) DEFAULT NULL,
PRIMARY KEY (`FLIGHTNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 */
public class Flight {

    private ArrayList<Flight> flightList;
    private int Flight_Number;
    private String Operator;
    private String Flight_Source;
    private String Flight_Destination;
    private String Flight_Date;
    private int Number_of_Total_Seats;
    private int Number_of_Seats_Taken;
    private int Cost;
    private int Number_of_Stops;
    private String Departure_Time;
    private String Arrival_Time;
    Query_Database qd = new Query_Database();

    public Flight(int Flight_Number, String Operator, String Flight_Source, String Flight_Destination, String Flight_Date, int Number_of_Seats, String Departure_Time, String Arrival_Time, int Number_of_Stops, int Cost) {
        this.Flight_Number = Flight_Number;
        this.Flight_Source = Flight_Source;
        this.Flight_Destination = Flight_Destination;
        this.Flight_Date = Flight_Date;
        this.Number_of_Total_Seats = Number_of_Seats;
        this.Departure_Time = Departure_Time;
        this.Arrival_Time = Arrival_Time;
        this.Number_of_Stops = Number_of_Stops;
        this.Cost = Cost;
    }

    public Flight() {
        qd.setConnection();
    }

    public int getFlight_Number() {
        return Flight_Number;
    }

    public void setFlight_Number(int Flight_Number) {
        this.Flight_Number = Flight_Number;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String Operator) {
        this.Operator = Operator;
    }

    public String getFlight_Source() {
        return Flight_Source;
    }

    public void setFlight_Source(String Flight_Source) {
        this.Flight_Source = Flight_Source;
    }

    public String getFlight_Destination() {
        return Flight_Destination;
    }

    public void setFlight_Destination(String Flight_Destination) {
        this.Flight_Destination = Flight_Destination;
    }

    public String getFlight_Date() {
        return Flight_Date;
    }

    public void setFlight_Date(String Flight_Date) {
        this.Flight_Date = Flight_Date;
    }

    public int getNumber_of_Total_Seats() {
        return Number_of_Total_Seats;
    }

    public void setNumber_of_Total_Seats(int Number_of_Total_Seats) {
        this.Number_of_Total_Seats = Number_of_Total_Seats;
    }

    public int getNumber_of_Seats_Taken() {
        return Number_of_Seats_Taken;
    }

    public void setNumber_of_Seats_Taken(int Number_of_Seats_Taken) {
        this.Number_of_Seats_Taken = Number_of_Seats_Taken;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int Cost) {
        this.Cost = Cost;
    }

    public int getNumber_of_Stops() {
        return Number_of_Stops;
    }

    public void setNumber_of_Stops(int Number_of_Stops) {
        this.Number_of_Stops = Number_of_Stops;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String Departure_Time) {
        this.Departure_Time = Departure_Time;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public void setArrival_Time(String Arrival_Time) {
        this.Arrival_Time = Arrival_Time;
    }

    public ArrayList<Flight> getFlightList() {
        return flightList;
    }

    public void setIpList(ArrayList<Flight> flightList) {
        this.flightList = flightList;
    }

    public boolean checkFlights(String source, String destination, String date) {
        flightList = new ArrayList<Flight>();
        Query_Database qd = new Query_Database();
        boolean check = false;
        try {
            
            String SQL = "SELECT * FROM sjoshi.flights f WHERE f.SOURCE = '" + source + "' AND f.DESTINATION = '" + destination + "' AND f.FLIGHT_DATE ='"+date+"'";
            ResultSet rset1 = qd.execute(SQL);

            while (rset1.next()) {
                Flight flight = new Flight();
                flight.setFlight_Number(rset1.getInt("FLIGHTNUMBER"));
                flight.setFlight_Source(rset1.getString("SOURCE"));
                flight.setFlight_Destination(rset1.getString("DESTINATION"));
                flight.setFlight_Date(rset1.getString("FLIGHT_DATE"));
                flight.setNumber_of_Seats_Taken(rset1.getInt("SEATS_TAKEN"));
                flight.setNumber_of_Total_Seats(rset1.getInt("SEATS_TOTAL"));
                flight.setArrival_Time((rset1.getString("ARRIVAL")));
                flight.setDeparture_Time((rset1.getString("DEPARTURE")));
                flight.setNumber_of_Stops((Integer.parseInt(rset1.getString("NUMBER_OF_STOPS"))));
                flight.setCost(Integer.parseInt(rset1.getString("COST")));
                flight.setOperator(rset1.getString("OPERATOR"));
                


                this.flightList.add(flight);
                check = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public void getFlightDetails(String flightName) {

        try {
            String SQL = "SELECT * FROM sjoshi.Flights f WHERE f.FLIGHTNUMBER = " + flightName;
            ResultSet rset1 = qd.execute(SQL);

            while (rset1.next()) {
                this.Flight_Number = (rset1.getInt("FLIGHTNUMBER"));
                this.Flight_Source = (rset1.getString("SOURCE"));
                this.Flight_Destination = (rset1.getString("DESTINATION"));
                this.Flight_Date = (rset1.getString("FLIGHT_DATE"));
                this.Number_of_Seats_Taken = (rset1.getInt("SEATS_TAKEN"));
                this.Number_of_Total_Seats = (rset1.getInt("SEATS_TOTAL"));
                this.Arrival_Time = ((rset1.getString("ARRIVAL")));
                this.Departure_Time = ((rset1.getString("DEPARTURE")));
                this.Number_of_Stops = (rset1.getInt("NUMBER_OF_STOPS"));
                this.Cost = (rset1.getInt("COST"));
                this.Operator = (rset1.getString("OPERATOR"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateTakenSeats(int flight_number, int seats_taken) {
       String SQL = "SELECT SEATS_TAKEN FROM sjoshi.flights WHERE FLIGHTNUMBER= " + flight_number;
       ResultSet rs = qd.execute(SQL);
       int total_seats_taken_now = 0;
       try  {
               if(rs.next()) {
               int flight_seats_taken = rs.getInt("SEATS_TAKEN");
               total_seats_taken_now = flight_seats_taken + seats_taken;    
               String UpdateSQL = "Update sjoshi.flights SET SEATS_TAKEN = " + total_seats_taken_now + " WHERE FLIGHTNUMBER=" + flight_number + ";";
               int set_flight_seats_taken = qd.executeAnUpdate(UpdateSQL);
               this.Number_of_Seats_Taken = total_seats_taken_now;
               if(set_flight_seats_taken > 0) {
                    System.out.println("SUCCESS!!");
                }
               }
       } catch(SQLException sq) {
           System.out.println(sq.getStackTrace());    
       }
    }

    public int getNumberOfAvailableSeats() {
        return Number_of_Total_Seats - Number_of_Seats_Taken;
    }
}
