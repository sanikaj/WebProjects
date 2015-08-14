/*
 * To change this template, choose Tools | Templates
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
import models.SingleFlightTransaction;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author nikhildahake
 */
public class AddFlightServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();

            if (session.getAttribute("user") == null) {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            ArrayList<SingleFlightTransaction> cart = (ArrayList<SingleFlightTransaction>) session.getAttribute("cart");

            if (cart == null) {
                cart = new ArrayList<SingleFlightTransaction>();

            }

            String wasCheckoutPerformed = request.getParameter("wasCheckoutPerformed");
            Flight flight = new Flight();
            SingleFlightTransaction flightTransaction = new SingleFlightTransaction();

            String flight_number = request.getParameter("selected_flight_number");
            String flight_class = request.getParameter("selected_flight_class");
            //flightnumber is null
            flight.getFlightDetails(flight_number);

            String flight_source = flight.getFlight_Source();
            String flight_destination = flight.getFlight_Destination();
            String dateOfTravel = flight.getFlight_Date();

            
            int numberOfSeatsRequired = -1;
            try{
               numberOfSeatsRequired =  Integer.parseInt(StringEscapeUtils.escapeHtml4(request.getParameter("no_of_seats")));
            }
            
            catch(NumberFormatException  e)
            {
                numberOfSeatsRequired=-1;
            }
           
            
            
            flightTransaction.setFlight_no(Integer.parseInt(flight_number));
            flightTransaction.setDate(dateOfTravel);
            flightTransaction.setDeparture_time(flight.getDeparture_Time());
            flightTransaction.setArrival_time(flight.getArrival_Time());
            flightTransaction.setNumberOfStops(flight.getNumber_of_Stops());
            flightTransaction.setNoOfSeatsRequired(numberOfSeatsRequired);
            float costPerSeat = flight.getCost();
            float total_cost = numberOfSeatsRequired * costPerSeat;
            flightTransaction.setTotalCost(total_cost);
                
            int numberOfSeatsAvailableOnFlight = flight.getNumberOfAvailableSeats();

            if (numberOfSeatsRequired <= numberOfSeatsAvailableOnFlight &&numberOfSeatsRequired>0 ) {

                cart.add(flightTransaction);
                session.setAttribute("cart", cart);
                if (wasCheckoutPerformed.equals("yes")) {
                    flight.updateTakenSeats(Integer.parseInt(flight_number),numberOfSeatsRequired);
                    request.getRequestDispatcher("ViewAndBook_Servlet").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/Flight_Search_Query_Page.jsp").forward(request, response);
                }
            } else {
                out.println("<html>");
                out.println("<head>");
                out.println("<script>");
                out.println("function submitForm(){document.getElementById(\"resubmitRequest\").submit(); }");
                out.println("</script>");
                out.println("</head>");
                out.println("<body style=\"background-color:#87CEEB\">");


                out.println("Sorry. The number of seats you wanted to book are not avaialble!");

                out.println("<form id=\"resubmitRequest\" method=\"POST\" action=\"FlightSearchQuery_Servlet\"> ");
                out.println("<input type=\"hidden\" name=\"source\" value=\"" + flight_source + "\" />");
                out.println("<input type=\"hidden\" name=\"destination\" value=\"" + flight_destination + "\" />");
                out.println("<input type=\"hidden\" name=\"date_of_travel\" value=\"" + dateOfTravel + "\" />");
                out.println("<input type=\"hidden\" name=\"no_of_seats\" value=\"" + numberOfSeatsRequired + "\" />");
                out.println("<input type=\"hidden\" name=\"class\" value=\"" + flight_class + "\" />");
                out.println("<button type=\"button\" onclick=\"submitForm()\" class=\"btn\"> Back to Flight Search Results </button>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");

            }

        }catch(Exception e)
        {
            System.out.println(e.toString());
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
