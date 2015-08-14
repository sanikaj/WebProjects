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
import models.Car;
import models.Hotel;
import models.TravelPackages;
import org.apache.catalina.tribes.util.Arrays;

/**
 *
 * @author sanika
 */
public class AddTravelPackage_Servlet extends HttpServlet {

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
        
        ArrayList<TravelPackages> cart = (ArrayList<TravelPackages>) session.getAttribute("cartOfTravelPackages");
        if (cart == null) {
            cart = new ArrayList<TravelPackages>();
      }
      String wasCheckoutPerformed = request.getParameter("wasTravelPackagesCheckoutPerformed");
      TravelPackages tp1 = new TravelPackages();
      TravelPackages tp2 = new TravelPackages();
      
      String[] carIdorHotelIdCost = new String[20];
      String requestParameter = request.getParameter("carId");
      
      if(request.getParameter("carId") != "") 
      {
         String carId=request.getParameter("carId").toString();
         carIdorHotelIdCost =(carId.split("\\+"));
         tp1.getTravelPack(carIdorHotelIdCost[0],carIdorHotelIdCost[1],null);
         Car car= new Car(carIdorHotelIdCost[0],carIdorHotelIdCost[2],Float.valueOf(carIdorHotelIdCost[1]));
         session.setAttribute("carTravelPackages" , generateCarHTMLString(car));
         if(wasCheckoutPerformed.equals("yes")) {
            cart.add(tp1);
         }
      }
    
      if(request.getParameter("hotelId") != "") {
          String hotelId = request.getParameter("hotelId").toString();
          carIdorHotelIdCost =(hotelId.split("\\+"));
          tp2.getTravelPack(carIdorHotelIdCost[0], null, carIdorHotelIdCost[3]);
          Hotel hotel = new Hotel(carIdorHotelIdCost[0],carIdorHotelIdCost[1],carIdorHotelIdCost[2],Float.valueOf(carIdorHotelIdCost[3]));
          session.setAttribute("hotelTravelPackages",generateHotelHTMLString(hotel));
          if(wasCheckoutPerformed.equals("yes")) {
            cart.add(tp2);
          }
      }
      
      if(wasCheckoutPerformed.equals("yes")) {
           // cart.add(tp);
            String travelPackagesCart = generateHtmlStringForCart(cart);
            session.setAttribute("cartOfTravelPackages",cart);
            session.setAttribute("cartOfTravelPackagesString", travelPackagesCart);
            request.getRequestDispatcher("/WEB-INF/View_and_Book_page.jsp").forward(request,response);
       }  
      
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

    public String generateHtmlStringForCart(ArrayList<TravelPackages> cart) {
        StringBuilder tableString = new StringBuilder();

        int row_no = 0;
        for (TravelPackages travelPackage : cart) {
            row_no++;
            tableString.append("<tr>");
            tableString.append("<td>");
            tableString.append(travelPackage.getDestination());
            tableString.append(travelPackage.getCar_idOrhotel_id());
            tableString.append("</td>");
            tableString.append("</tr>");
        }
        return tableString.toString();
    }
    
    public String generateCarHTMLString(Car car) {
        StringBuilder tableString = new StringBuilder();
       
        tableString.append("<table border='1'>");
        tableString.append("<tr><td>");
        tableString.append(car.getCar_rental_company());
        tableString.append("</td><td>");
        tableString.append("&nbsp$" + car.getCar_cost());
        tableString.append("</td></tr>");
        tableString.append("</table>");
     return tableString.toString();
    
     }
    
    public String generateHotelHTMLString(Hotel hotel) {
        StringBuilder tableString = new StringBuilder();
        int row_no = 0;
        tableString.append("<center><table border='1'>");
        tableString.append("<tr><td>");
        tableString.append(hotel.getHotel_name());
        tableString.append("</td><td>");
        tableString.append(hotel.getRoom_type());
        tableString.append("</td><td>");
        tableString.append(hotel.getRoom_cost());
        tableString.append("</td></tr>");
        tableString.append("</table></center>");
   
     return tableString.toString();
    }
             
}
