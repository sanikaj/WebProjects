/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package super_airways;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Flight;

/**
 *
 * @author sanika
 */
public class FlightSearchResult_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String flightNumber = request.getParameter("selected_flight_number");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
           request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
           return;
        }
        Flight flight = new Flight();
        flight.getFlightDetails(flightNumber);
        String flightSource = flight.getFlight_Source();
        String flightDestination = flight.getFlight_Destination();
        String flightDate = flight.getFlight_Date();
        String flightDepartureTime = flight.getDeparture_Time();
        String flightArrivalTime = flight.getArrival_Time();
        String operator = flight.getOperator();
        int flightNumberOfStops = flight.getNumber_of_Stops();
        float flightCost = flight.getCost();



        session.setAttribute("selectedFlightNumber", flightNumber);
        session.setAttribute("selectedFlightSource", flightSource);
        session.setAttribute("selectedFlightDestination", flightDestination);
        session.setAttribute("selectedFlightDate", flightDate);
        session.setAttribute("selectedFlightDepartureTime", flightDepartureTime);
        session.setAttribute("selectedFlightArrivalTime", flightArrivalTime);
        session.setAttribute("selectedFlightNumberOfStops", flightNumberOfStops);
        session.setAttribute("selectedFlightCost", flightCost);
        session.setAttribute("selectedFlightOperator", operator);


        request.getRequestDispatcher("/WEB-INF/View_and_Book_page.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
