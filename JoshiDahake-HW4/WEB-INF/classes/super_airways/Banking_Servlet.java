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
import models.SingleFlightTransaction;
import models.Transactions;

/**
 *
 * @author sanika
 */
public class Banking_Servlet extends HttpServlet {

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
        Transactions trans = new Transactions();
        HttpSession session = request.getSession();    
        Accounts account = new Accounts();
   
        try {
            int account_id= Integer.parseInt(request.getParameter("accountNumber"));
            String ninerId = request.getParameter("ninerId");
            String account_holder= request.getParameter("accountHolderName");
            String routing_number= request.getParameter("routingNumber"); 
            String bank_number = request.getParameter("bankNumber");
            
            /* Update the Users Account */
              
            float flightTotalCost = Float.parseFloat(request.getParameter("totalCostForAllFlights"));
            float packageCost = Float.parseFloat(request.getParameter("totalPackagesCost").toString());
            float totalCost = flightTotalCost + packageCost;
           
            
          
            /* TODO output your page here. You may use following sample code. */
             
            if (trans.checkTransactions(account_id, account_holder, routing_number)) {
               if (trans.getAccount_id() == account_id && trans.getHolder_name().equals(account_holder) && trans.getRouting_number().equals(routing_number) && trans.getBank_number().equals(bank_number) && Integer.parseInt(trans.getBalance()) != 0) {
                   if (Integer.parseInt(trans.getBalance()) < totalCost) {
                       out.write("Failure");
                       out.write("<p>Please check your bank balance as it is not sufficient!!</p>");
              } else {
                 ArrayList<SingleFlightTransaction> cart = (ArrayList<SingleFlightTransaction>) session.getAttribute("cart");
                 account.updateAccountForUser(trans.getAccount_id(), totalCost);
                 out.write("Success");
                 out.write("<p>Thank you!! Your purchase has been confirmed!!</p>");
                 StringBuilder tableString = new StringBuilder();
                 for (SingleFlightTransaction flightTransaction : cart) {
                 out.write("<table class=\"table table-striped table-bordered table-condensed\" style=\"width: 30em;margin-left: auto; margin-right: auto;\">");
                     for(int i =0 ;i< flightTransaction.getNoOfSeatsRequired();i++) {
                      out.write("<tr><td>Passenger " + (i+1) + "</td></tr>"
                        + "<tr><td>Name: <input type=\"Name\" name =\"Name\"  style=\"margin-left:0.2em\" /></td></tr>"
                        + "<tr><td>Age: <input type=\"Age\" name=\"Age\" style=\"margin-left:1em\" /></td></tr>"
                        + "<tr><td>Sex: <input type=\"Sex\" name=\"Sex\" placeholder=\"Male/Female\"  style=\"margin-left:1.2em\"/></td></tr>");
                     }
                     
                    out.write("</table>");
                 }
                    out.write("<input type=\"button\" name=\"Print\" value=\"Print\" style= \"margin-top:2em\" class=\"btn\" onclick=\"window.print();\"/><br>");
                
            }
         }
        } else {
            out.write("Transaction is not successfull. Please reenter the account information!!");
        }
        }catch(Exception e) {
                System.out.println(e.getStackTrace());
        } finally {
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
