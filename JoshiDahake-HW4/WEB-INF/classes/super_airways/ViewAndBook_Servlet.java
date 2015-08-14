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
import models.TravelPackages;

/**
 *
 * @author sanika
 */
public class ViewAndBook_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("user") == null) {
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            
            ArrayList<SingleFlightTransaction> cart = (ArrayList<SingleFlightTransaction>) session.getAttribute("cart");
            String tableString = generateHtmlStringForCart(cart);
            float totalCostForAllFlights = 0;
            for (SingleFlightTransaction flightTransaction : cart) {

                float cost = flightTransaction.getTotalCost();
                totalCostForAllFlights += cost;
            }
            ArrayList<TravelPackages> cartForTravelPackages = (ArrayList<TravelPackages>) session.getAttribute("cartOfTravelPackages");
            float cost = 0.0f;
            float totalCostForAllTravelPackages = 0.0f;
            if (cartForTravelPackages != null && cartForTravelPackages.size() > 0) {
                for (TravelPackages tp : cartForTravelPackages) {
                    if (tp.getTravelPackageCostForCarPckg() != null) {
                        cost = Float.valueOf(tp.getTravelPackageCostForCarPckg());
                    } else if (tp.getTravelPackageCostForHotelPckg() != null) {
                        cost = Float.valueOf(tp.getTravelPackageCostForHotelPckg());
                    } else {
                        cost = 0;
                    }
                    totalCostForAllTravelPackages += cost;

                }
            }
            session.setAttribute("tableStringForCart", tableString);
            session.setAttribute("totalCostForAllFlights", totalCostForAllFlights);
            session.setAttribute("totalCostForAllTravelPackages", "<tr><td>" + "Total Cost For Packages :" + totalCostForAllTravelPackages + "</td></tr>");
            session.setAttribute("packagesCost", totalCostForAllTravelPackages);
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
            request.getRequestDispatcher("/WEB-INF/Confirm_Booking.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    String generateHtmlStringForCart(ArrayList<SingleFlightTransaction> cart) {
        StringBuilder tableString = new StringBuilder();

        int row_no = 0;
        for (SingleFlightTransaction flightTransaction : cart) {

            row_no++;
            tableString.append("<tr>");
            int flight_Number = flightTransaction.getFlight_no();
            tableString.append("<td>" + flight_Number + "<input type=\"hidden\" id=\"flight_Number_" + row_no + "\" value=\"" + flight_Number + "\"/> " + "</td>");
            tableString.append("<td>" + flightTransaction.getDate() + "<input type=\"hidden\" id=\"flight_source_" + row_no + "\" value=\"" + flightTransaction.getDate() + "\"/> " + "</td>");
            tableString.append("<td>" + flightTransaction.getDeparture_time() + "<input type=\"hidden\" id=\"flight_destination_" + row_no + "\" value=\"" + flightTransaction.getDeparture_time() + "\"/>" + "</td>");
            tableString.append("<td>" + flightTransaction.getArrival_time() + "<input type=\"hidden\" id=\"flight_date_" + row_no + "\" value=\"" + flightTransaction.getArrival_time() + "\"/>" + "</td>");
            tableString.append("<td>" + flightTransaction.getNumberOfStops() + "<input type=\"hidden\" id=\"flight_departureTime_" + row_no + "\" value=\"" + flightTransaction.getNumberOfStops() + "\"/> " + "</td>");
            tableString.append("<td>" + flightTransaction.getNoOfSeatsRequired() + "<input type=\"hidden\" id=\"flight_arrivalTime_" + row_no + "\" value=\"" + flightTransaction.getNoOfSeatsRequired() + "\"/>" + "</td>");
            tableString.append("<td>" + flightTransaction.getTotalCost() + "<input type=\"hidden\" id=\"flight_getNoOfStops_" + row_no + "\" value=\"" + flightTransaction.getTotalCost() + "\"/> " + "</td>");
            tableString.append("</tr>");

        }

        return tableString.toString();
    }
}
