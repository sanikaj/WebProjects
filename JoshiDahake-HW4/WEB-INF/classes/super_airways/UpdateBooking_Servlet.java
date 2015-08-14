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
public class UpdateBooking_Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Accounts account = new Accounts();
        Transactions trans = new Transactions();
        Bookings booking = new Bookings();
        ArrayList<SingleFlightTransaction> cart = (ArrayList<SingleFlightTransaction>) session.getAttribute("cart");
        
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
       }
       
       if (cart == null) {
           request.getRequestDispatcher("/WEB-INF/Flight_Search_Query_Page.jsp").forward(request, response);
       }
            
       try {
            /* TODO output your page here. You may use following sample code. */
            String ninerId = request.getParameter("ninerId");
            int transaction_AccountId =Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("accountNumber")));
            int routing_number = Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("routingNumber")));
            String bank_number = StringEscapeUtils.escapeHtml4(request.getParameter("bankNumber"));
          
            
            //Update the flightTransaction
            for (SingleFlightTransaction flightTransaction : cart) {
                Flight flight = new Flight();
                flight.setFlight_Number(flightTransaction.getFlight_no());
                flight.updateTakenSeats(flightTransaction.getFlight_no(), flightTransaction.getNoOfSeatsRequired());
            }
            session.setAttribute("cart", null);
            session.setAttribute("cartOfTravelPackages", null);
                 
            for (SingleFlightTransaction flightTransaction : cart) {
                int bookingId = booking.getMaxBookingID() + 1;
                int rowsUpdated = booking.insertBookingDetails(ninerId, bookingId, Integer.parseInt(bank_number), flightTransaction.getDate(), flightTransaction.getFlight_no() + "", flightTransaction.getNoOfSeatsRequired(), (int) flightTransaction.getTotalCost());
                if(rowsUpdated == 0)
                    out.println("Failure");
                else
                    out.println("Success");
            }            
            
        } catch(Exception e) {
          e.printStackTrace();
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
