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
import models.Accounts;
import models.Bookings;
import models.Flight;
import models.SingleFlightTransaction;
import models.Transactions;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author sanika
 */
public class TransactionConfirmation_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Transactions trans = new Transactions();
        Bookings booking = new Bookings();
        Accounts account = new Accounts();
        try {
            HttpSession session = request.getSession();
            ArrayList<SingleFlightTransaction> cart = (ArrayList<SingleFlightTransaction>) session.getAttribute("cart");
            if (session.getAttribute("user") == null) {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            if (cart == null) {
                request.getRequestDispatcher("/WEB-INF/Flight_Search_Query_Page.jsp").forward(request, response);
            }
            for (SingleFlightTransaction flightTransaction : cart) {
                Flight flight = new Flight();
                flight.setFlight_Number(flightTransaction.getFlight_no());
                flight.updateTakenSeats(flightTransaction.getFlight_no(), flightTransaction.getNoOfSeatsRequired());
                //update number of seats in DB. noOfSeatsRequired+ noOfSeatsTakenInDB


            }


            float flightTotalCost = Float.parseFloat(session.getAttribute("totalCostForAllFlights").toString());
            float packageCost = Float.parseFloat(session.getAttribute("packagesCost").toString());
            
            float totalCost = flightTotalCost + packageCost;
            int account_id = Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("account_number")));
            String account_holder = StringEscapeUtils.escapeHtml4(request.getParameter("account_holder_name"));
            String routing_number = StringEscapeUtils.escapeHtml4(request.getParameter("routing_number"));
            String bank_number = StringEscapeUtils.escapeHtml4(request.getParameter("bank_number"));
            if (trans.checkTransactions(account_id, account_holder, routing_number)) {
                //&& trans.getRouting_number().equals(Integer.parseInt(routing_number)) && trans.getBalance() != 0 );
                if (trans.getAccount_id() == account_id && trans.getHolder_name().equals(account_holder) && trans.getRouting_number().equals(routing_number) && trans.getBank_number().equals(bank_number) && Integer.parseInt(trans.getBalance()) != 0) {
                    if (Integer.parseInt(trans.getBalance()) < totalCost) {
                        session.setAttribute("transactionStatus", "Failure");
                        session.setAttribute("errorMessage", "<p>Please check your bank balance as it is not sufficient!!</p>");
                        //session.setAttribute("error message","<html><title>Error Page</title><body><p>Please check your bank balance as it is not sufficient!!</p></body></html>");
                        //   out.println("<html><title>Error Page</title><body><p>Please check your bank balance as it is not sufficient!!</p></body></html>");
                        request.getRequestDispatcher("/WEB-INF/Transaction_Confirmation_Page.jsp").forward(request, response);
                    } else {
                        session.setAttribute("transactionStatus", "success");
                        String passengerDetailsTableString = getTableStringForEnteringPassengerDetails(cart);
                        String niner_id = session.getAttribute("user").toString();
                        account.updateAccountForUser(trans.getAccount_id(),totalCost);
                        int bookingId = booking.getMaxBookingID() + 1;
                        for (SingleFlightTransaction flightTransaction : cart) {
                            booking.insertBookingDetails(niner_id, bookingId, Integer.parseInt(bank_number), flightTransaction.getDate(), flightTransaction.getFlight_no() + "", flightTransaction.getNoOfSeatsRequired(), (int) flightTransaction.getTotalCost());

                        }

                        session.setAttribute("tableString", passengerDetailsTableString);
                        session.setAttribute("account_holder", null);
                        session.setAttribute("routing_number", null);
                        session.setAttribute("bank_number", null);
                        session.setAttribute("cart", null);
                        session.setAttribute("cartOfTravelPackages", null);

                        request.getRequestDispatcher("/WEB-INF/Transaction_Confirmation_Page.jsp").forward(request, response);
                    }
                }

            } else {
                session.setAttribute("transactionStatus", "Failure");
                session.setAttribute("errorMessage", "<p>Please check your bank details</p>");
                //session.setAttribute("error message","<html><title>Error Page</title><body><p>Please check your bank details</p></body></html>");
                //out.println("<html><title>Error Page</title><body><p>Please check your bank details</p></body></html>");
                request.getRequestDispatcher("/WEB-INF/Transaction_Confirmation_Page.jsp").forward(request, response);
            }


        } catch (Exception e) {
            System.out.println("The exception is" + e.getStackTrace());
        } finally {
            out.close();
        }
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

    String getTableStringForEnteringPassengerDetails(ArrayList<SingleFlightTransaction> cart) {
        StringBuilder tableString = new StringBuilder();
        String FlightHeader = "<tr>"
                + "<th>Flight No.</th>"
                + "<th>Date</th>"
                + "<th>Departure Time</th>"
                + "<th>Arrival Time</th>"
                + "<th>Number of Stops</th>"
                + "<th>No Of seats booked</th>"
                + "<th>Total Cost</th>"
                + "</tr>";
        for (SingleFlightTransaction flightTransaction : cart) {

            tableString.append("<table border=\"1\">");
            tableString.append(FlightHeader);
            tableString.append("<tr>");
            int flight_Number = flightTransaction.getFlight_no();
            tableString.append("<td>" + flight_Number + "</td>");
            tableString.append("<td>" + flightTransaction.getDate() + "</td>");
            tableString.append("<td>" + flightTransaction.getDeparture_time() + "</td>");
            tableString.append("<td>" + flightTransaction.getArrival_time() + "</td>");
            tableString.append("<td>" + flightTransaction.getNumberOfStops() + "</td>");
            tableString.append("<td>" + flightTransaction.getNoOfSeatsRequired() + "</td>");
            tableString.append("<td>" + flightTransaction.getTotalCost() + "</td>");
            tableString.append("</tr>");

            tableString.append("<p>");

            StringBuilder str = new StringBuilder();

            tableString.append("<table border=\"1\">");
            for (int i = 0; i < flightTransaction.getNoOfSeatsRequired(); i++) {
                tableString.append("<tr><td>Passenger " + (i+1) + "</td></tr>"
                        + "<tr><td>Name: <input type=\"Name\" name =\"Name\"  style=\"margin-left:0.2em\" /></td></tr>"
                        + "<tr><td>Age: <input type=\"Age\" name=\"Age\" style=\"margin-left:1em\" /></td></tr>"
                        + "<tr><td>Sex: <input type=\"Sex\" name=\"Sex\" placeholder=\"Male/Female\"  style=\"margin-left:1.2em\"/></td></tr>");

            }

            tableString.append(str.toString());
            tableString.append("</table>");
            tableString.append("</p>");
            tableString.append("</table>");

        }




        return tableString.toString();

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
