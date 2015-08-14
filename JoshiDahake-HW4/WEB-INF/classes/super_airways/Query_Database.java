/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_airways;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *
 * @author Sanika
 */
public class Query_Database {
    Connection con;
    public void setConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
        }
    }

    public int checkUserNameExists(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");
        
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM sjoshi.users where niner_id= " + "'" + username + "'");
            res.next();
            int count = res.getInt(1);
            return count;
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return -1;

        }
  
    }
    
    public int checkValueExists(String username, String password) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            
           
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM sjoshi.users where niner_id= " + "'" + username + "'" + "AND PASSWORD=" + "'" + password + "'");
            res.next();
            int count = res.getInt(1);
            return count;
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return -1;

        }

    }

    public ResultSet selectAllFlightsSrcAndDest() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT Flight_Source, Flight_Destination FROM sjoshi.flights;");
            return res;
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return null;
        }
    }

    public int executeAnUpdate(String SQL) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            int value = stmt.executeUpdate(SQL);
            con.commit();
            return value;
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return -1;
        }
    }

    public int insertInDatabase(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            int value = stmt.executeUpdate("INSERT INTO sjoshi.users(niner_id,Password) VALUES( " + "'" + username + "'," + "'" + password + "'" + ")");
            return value;
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return 0;
        }
    }

    public ResultSet execute(String SQL) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(SQL);
            return res;
        } catch (SQLException e) {
            System.out.println(" Error in excuting SQL statement on database ");
            e.printStackTrace();
            return null;
        }
    }

    public void insertFlightSearchIntoDB(String source, String destination, String date_of_travel, String no_of_seats) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            con.setAutoCommit(false);
            //SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
           // Date date = formatter.parse(date_of_travel);


            Random rn = new Random();
            int range = 9999999 - 111111 + 1;
            int randomNum = rn.nextInt(range) + 111111;
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO sjoshi.flights VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, randomNum);
            pstmt.setString(2, "VirginGalactic");
            pstmt.setString(3, source);
            pstmt.setString(4, destination);
            pstmt.setInt(5, 1000);
            pstmt.setInt(6, 6);
            pstmt.setInt(7, 213);
            pstmt.setString(8, "11:11:13");
            pstmt.setString(9, "1:10:13");
            pstmt.setInt(10, 3);
            pstmt.setString(11, date_of_travel);

            // stmt.executeUpdate("INSERT INTO airline_reservation_system.flights(Flight_Name,Flight_Source,Flight_Destination, Flight_Date,Number_of_Seats,Departure_Time,Arrival_Time,Number_of_Stops,Cost)VALUES(QF232," + source + "," +destination + "1989/06/12",12,"12:13:11","11:13:11",3,12)");
            pstmt.executeUpdate();
            con.commit();
        } catch (Exception exp) {
            System.out.println("The exception is" + exp);

        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

    public String selectAllFlightsDestinationsFromDB() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT DESTINATION FROM sjoshi.flights");
            return getHtmlStringFromResultSet(rs, 3);

        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return null;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

    public String selectAllFlightsSourcesFromDB() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT SOURCE FROM sjoshi.flights");
            return getHtmlStringFromResultSet(rs, 2);


        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return null;
        } finally {
            try {

                con.close();
            } catch (Exception e) {
            }

        }
    }

    public String selectFlightsFromDB(String flightSource, String flightDestination, String dateOfTravel, String numberOfSeats, String[] classForAirline) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(dateOfTravel);

            Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT Flight_Name, Flight_Source, Flight_Destination, Flight_Date, Departure_Time, Arrival_Time, Number_of_Stops, Cost from airline_reservation_system.flights WHERE Flight_Source=" + "'" + flightSource + "'");
            ResultSet rs = stmt.executeQuery("SELECT FLIGHTNUMBER, SOURCE, DESTINATION, Flight_Date, DEPARTURE, ARRIVAL, NUMBER_OF_STOPS, COST from sjoshi.flights WHERE Flight_Source='Atlanta'");
            return getHtmlStringFromResultSet(rs, 1);

        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return null;
        } finally {
            try {

                con.close();
            } catch (Exception e) {
            }

        }
    }

    public ResultSet selectFlightsFromDBWithFlightNumber(String flightNumber) {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/sjoshi?"
                    + "user=root&password=S@pass123#");

            Statement stmt = con.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT Flight_Name, Flight_Source, Flight_Destination, Flight_Date, Departure_Time, Arrival_Time, Number_of_Stops, Cost from airline_reservation_system.flights WHERE Flight_Source=" + "'" + flightSource + "'");
            ResultSet rs = stmt.executeQuery("SELECT Flight_Name, Flight_Source, Flight_Destination, Flight_Date, Departure_Time, Arrival_Time, Number_of_Stops, Cost from sjoshi.flights WHERE Flight_Name=" + flightNumber);
            return rs;

        } catch (Exception exp) {
            System.out.println("The exception is" + exp);
            return null;
        } finally {
            try {

                con.close();
            } catch (Exception e) {
            }

        }
    }

    private String getHtmlStringFromResultSet(ResultSet rs, int pageNumber) {
        try {
            StringBuilder tableString = new StringBuilder();
            int count = 0;
            switch (pageNumber) {
                case 1:
                    while (rs.next()) {
                        tableString.append("<tr>");
                        int flight_Number = rs.getInt("FLIGHTNUMBER");
                        tableString.append("<td>" + flight_Number + "</td>");
                        tableString.append("<td>" + rs.getString("SOURCE") + "</td>");
                        tableString.append("<td>" + rs.getString("DESTINATIOM") + "</td>");
                        tableString.append("<td>" + rs.getDate("FLIGHT_DATE") + "</td>");
                        tableString.append("<td>" + rs.getTime("DEPARTURE") + "</td>");
                        tableString.append("<td>" + rs.getTime("ARRIVAL") + "</td>");
                        tableString.append("<td>" + rs.getInt("NUMBER_OF_STOPS") + "</td>");
                        tableString.append("<td>" + rs.getInt("COST") + "</td>");
                        //tableString.append("<td> <button type=\"button\" onclick='flightSearchServlet(\""+rs.getString("Flight_Name")+"\")'" + ">View and book</button></td>");
                        tableString.append("<td> <input type=\"submit\" value=\"View And Book\"></td>");
                        tableString.append("</tr>");
                    }

                    return tableString.toString();

                case 2:
                    while (rs.next()) {
                        tableString.append("<option value=\"" + rs.getString("SOURCE") + "\">" + rs.getString("SOURCE") + "</option>");
                        count++;
                    }
                    return tableString.toString();

                case 3:
                    while (rs.next()) {
                        tableString.append("<option value=\"" + rs.getString("DESTINATION") + "\">" + rs.getString("DESTINATION") + "</option>");
                        count++;
                    }
                    return tableString.toString();

            }

        } catch (Exception e) {
            //put in logger
            System.out.println(e.toString());
        }
        return "No results Found!!";
    }
    
    public void closeConnection() {
        try {
        con.close();
        } catch(SQLException sq) {
            System.out.println(sq.getStackTrace());
        }
    }
}
