package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import super_airways.Query_Database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author sanika
 */
public class Bookings {

    private int bookingI;
    private String niner_id;
    private String flight_id;
    private String date_Of_Booking;
    private int number_of_seats;
    private int account_id;
    private int total_cost;
    private Query_Database qd;
    private ArrayList<Bookings> bookings;

    public Bookings() {
        qd = new Query_Database();
    }

    public Bookings(String niner_id, String flight_id, String date_Of_Booking, int number_of_seats, int account_id, int total_cost) {
        this.niner_id = niner_id;
        this.date_Of_Booking = date_Of_Booking;
        this.number_of_seats = number_of_seats;
        this.account_id = account_id;
        this.total_cost = total_cost;
    }

    public int getBookingI() {
        return bookingI;
    }

    public void setBookingI(int bookingI) {
        this.bookingI = bookingI;
    }

    public String getNiner_id() {
        return niner_id;
    }

    public void setNiner_id(String niner_id) {
        this.niner_id = niner_id;
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getDate_Of_Booking() {
        return date_Of_Booking;
    }

    public void setDate_Of_Booking(String date_Of_Booking) {
        this.date_Of_Booking = date_Of_Booking;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public void retrieveBookingDetails(String username) {
        String SQL = "SELECT BOOKINGID,ACCOUNTID,DATE,FLIGHTID,NUMBEROFSEATS,TOTALCOST FROM sjoshi.bookings WHERE niner_id =" + "'" + username + "';";
        this.bookings = new ArrayList<Bookings>();
        ResultSet rset1 = qd.execute(SQL);
        try {
            while (rset1.next()) {
                Bookings booking = new Bookings();
                booking.bookingI = rset1.getInt("BOOKINGID");
                booking.account_id = rset1.getInt("ACCOUNTID");
                booking.date_Of_Booking = rset1.getString("DATE");
                booking.flight_id = rset1.getString("FLIGHTID");
                booking.number_of_seats = rset1.getInt("NUMBEROFSEATS");
                booking.setTotal_cost(rset1.getInt("TOTALCOST"));
                this.bookings.add(booking);
            }

        } catch (SQLException sq) {
            System.out.println(sq.getStackTrace());
        }

    }

    public int insertBookingDetails(String niner_id, int bookingId, int accountId, String date, String flightId, int numberOfSeats, int cost) {
        String SQL = "Insert into sjoshi.bookings(BookingID,niner_id,FLIGHTID,DATE,NUMBEROFSEATS,ACCOUNTID,TOTALCOST) values(" + bookingId + ",'" +niner_id  + "'," + flightId + ",'" + date + "'," + numberOfSeats + "," + accountId + ","+cost+")";

        int i = qd.executeAnUpdate(SQL);

        return i;
    }

    public ArrayList<Bookings> getBookings() {
        return this.bookings;
    }

   public int getMaxBookingID()
    {
        String SQL = "SELECT Max(BookingID) From sjoshi.bookings";
        int max =0;
        ResultSet rset1 = qd.execute(SQL);
        try {
            while (rset1.next()) {
               max = rset1.getInt(1);
            }

        } catch (SQLException sq) {
            System.out.println(sq.getStackTrace());
        }
        return max;
    }
    public void setBookings(ArrayList<Bookings> bookings) {
        this.bookings = bookings;
    }
}
