/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package super_airways;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Car;
import models.Hotel;
import models.SingleFlightTransaction;
import models.TravelPackages;

/**
 *
 * @author sanika
 */
public class TravelPackages_Servlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String flight_number = request.getParameter("selected_flight_number");
        Map<String,List<String>> map = new HashMap();
        try {
            
            TravelPackages tp= new TravelPackages();
            map = tp.retrieveAllDeals(session.getAttribute("selectedFlightDestination").toString());
            if(map == null) 
                request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
            Car car = new Car();
            if(map.get("carList").size() > 0 && car.retrieveCars(map.get("carList")) != null) {
            String listOfCars=generateListOfCars(car.retrieveCars(map.get("carList")));
            session.setAttribute("listOfCars", listOfCars);
            } else {
                ArrayList<Car> carList = new ArrayList<Car>();
                String listOfCars=generateListOfCars(carList);
                session.setAttribute("listOfCars", listOfCars);
            }
            Hotel hotel = new Hotel();
            if(map.get("hotelList")!= null && (hotel.retrieveHotels(map.get("hotelList")) != null) ) {
                String listOfHotels= generateListOfHotels(hotel.retrieveHotels(map.get("hotelList")));
                session.setAttribute("listOfHotels", listOfHotels);
            } else {
                ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
                String listOfHotels= generateListOfHotels(hotel.retrieveHotels(map.get("hotelList")));
                session.setAttribute("listOfHotels", listOfHotels);
            }
            request.getRequestDispatcher("/WEB-INF/Travel_Packages.jsp").forward(request,response);
            
        } catch(Exception e) {
             System.out.println("The exception is" + e.getStackTrace());
        }finally {
            out.close();
        }
    }
    
    
    public String generateListOfCars(List<Car> cars) {
        StringBuilder tableString = new StringBuilder();
        if(cars.size() > 0) {
        tableString.append("<ul>");
        
        for(Car car: cars) {
            tableString.append("<input type='radio' name='car'" + "value=" + car.getCar_id() + "+" +  car.getCar_cost() + "+" +  car.getCar_rental_company() + ">");
            tableString.append("&nbsp" + car.getCar_rental_company());
            tableString.append("&nbsp" + "$" + car.getCar_cost() + "<br/>");
        }
      tableString.append("</ul>");
        } else  {
             tableString.append("<ul>");
             tableString.append("<p>No cars packages for this destination</p>");
             tableString.append("</ul>");
        }
      return tableString.toString();
   }

     public String generateListOfHotels(List<Hotel> hotels) {
        StringBuilder tableString = new StringBuilder();
        if(hotels.size() > 0) {
         tableString.append("<ul>");
        
        for(Hotel hotel: hotels) {
            tableString.append("<input type='radio' name='hotel'" + "value=" + hotel.getHotel_id() + "+" + hotel.getHotel_name() + "+" + hotel.getRoom_type() + "+" + hotel.getRoom_cost() + ">");
            tableString.append("&nbsp" + hotel.getHotel_name());
            tableString.append("&nbsp" + hotel.getRoom_type());
            tableString.append("&nbsp" + "$" + hotel.getRoom_cost()  + "<br/>");
        }
      tableString.append("</ul>");
      } else {
             tableString.append("<ul>");
             tableString.append("<p>No hotel packages for this destination</p>");
             tableString.append("</ul>");
        }
      return tableString.toString();
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
