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
import models.Bookings;
import models.Flight;

/**
 *
 * @author sanika
 */
public class BookingHistory_Servlet extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            Bookings booking = new Bookings();
            booking.retrieveBookingDetails(session.getAttribute("user").toString());
            session.setAttribute("bookingsList",convertBookingHistoryToString(booking.getBookings()));
            request.getRequestDispatcher("/WEB-INF/Booking_History_Page.jsp").forward(request,response);
        } catch(Exception e) {
            System.out.println(e.getStackTrace());
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

    
    public StringBuilder convertBookingHistoryToString(ArrayList<Bookings> booking) {
        StringBuilder tableString = new StringBuilder();

       
        for (Bookings bookingIndList : booking) {

            
            tableString.append("<tr>");
            tableString.append("<td>" + bookingIndList.getBookingI() + "</td>");
            tableString.append("<td>" + bookingIndList.getFlight_id() + "</td>");
            tableString.append("<td>" + bookingIndList.getDate_Of_Booking() + "</td>");
            tableString.append("<td>" + bookingIndList.getNumber_of_seats()+ "</td>");
            tableString.append("<td>" + bookingIndList.getTotal_cost() + "</td>");
            tableString.append("</tr>");
            
        }
         return tableString;
    }
}
