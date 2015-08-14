/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_airways;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Flight;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author Sanika
 */
public class FlightSearchQuery_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FlightSearchQuery_Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FlightSearchQuery_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Flight flight = new Flight();
        Query_Database qd = new Query_Database();
        try {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String date_of_travel = StringEscapeUtils.escapeHtml4(request.getParameter("date_of_travel"));
        String no_of_seats = StringEscapeUtils.escapeHtml4(request.getParameter("no_of_seats"));
        String flight_class = request.getParameter("class");

        boolean FlightsExist = flight.checkFlights(source, destination, date_of_travel);
        String resultString = "<table style='margin-left:25em;margin-top:2em'><tr><td>Sorry No Flights Found!!</td></tr></table>";
        ArrayList<Flight> flights = flight.getFlightList();
        if (flights.size() < 2) { //perform 2 inserts
            // qd.insertFlightSearchIntoDB(source, destination, date_of_travel, no_of_seats);
            // qd.insertFlightSearchIntoDB(source, destination, date_of_travel, no_of_seats);
        }


        FlightsExist = flight.checkFlights(source, destination, date_of_travel);
        flights = flight.getFlightList();

        if (FlightsExist) {
            resultString = convertFlightListToHtmlResultString(flights);
        }
        session.setAttribute("htmlSearchResultString", resultString);
        request.getRequestDispatcher("/WEB-INF/Flight_Search_Results_Page.jsp").forward(request, response);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    String convertFlightListToHtmlResultString(ArrayList<Flight> flightList) {
        StringBuilder tableString = new StringBuilder();

        int row_no = 0;
        for (Flight flight : flightList) {

            row_no++;
            tableString.append("<tr>");
            int flight_Number = flight.getFlight_Number();
            tableString.append("<td>" + flight_Number + "<input type=\"hidden\" id=\"flight_Number_" + row_no + "\" value=\"" + flight_Number + "\"/> " + "</td>");
            tableString.append("<td>" + flight.getFlight_Source() + "<input type=\"hidden\" id=\"flight_source_" + row_no + "\" value=\"" + flight.getFlight_Source() + "\"/> " + "</td>");
            tableString.append("<td>" + flight.getFlight_Destination() + "<input type=\"hidden\" id=\"flight_destination_" + row_no + "\" value=\"" + flight.getFlight_Destination() + "\"/>" + "</td>");
            tableString.append("<td>" + flight.getFlight_Date() + "<input type=\"hidden\" id=\"flight_date_" + row_no + "\" value=\"" + flight.getFlight_Date() + "\"/>" + "</td>");
            tableString.append("<td>" + flight.getDeparture_Time() + "<input type=\"hidden\" id=\"flight_departureTime_" + row_no + "\" value=\"" + flight.getDeparture_Time() + "\"/> " + "</td>");
            tableString.append("<td>" + flight.getArrival_Time() + "<input type=\"hidden\" id=\"flight_arrivalTime_" + row_no + "\" value=\"" + flight.getArrival_Time() + "\"/>" + "</td>");
            tableString.append("<td>" + flight.getNumber_of_Stops() + "<input type=\"hidden\" id=\"flight_getNoOfStops_" + row_no + "\" value=\"" + flight.getNumber_of_Stops() + "\"/> " + "</td>");
            tableString.append("<td>" + flight.getOperator() + "<input type=\"hidden\" id=\"flight_operator_" + row_no + "\" value=\"" + flight.getOperator() + "\"/>" + "</td>");
            tableString.append("<td>" + flight.getCost() + "<input type=\"hidden\" id=\"flight_cost_" + row_no + "\" value=\"" + flight.getCost() + "\"/>" + "</td>");


            tableString.append("<td> <button type=\"button\" onClick=\"setValuesAndSubmit('" + row_no + "');\"> View and Book </button></td>");
            tableString.append("</tr>");

        }



        return tableString.toString();

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
