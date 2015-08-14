/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package super_airways;

import models.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikhildahake
 */
public class RegistrationPage_Servlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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

            String username = request.getParameter("username");
            String password = request.getParameter("pass");
            
            //check if user exists in the properties file. If yes , don't create
            //
            User usr = new User();
            if(!usr.findUserWithUserName(username))//user does not exist
            {
                int success = (new Query_Database()).insertInDatabase(username, password);
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            }
            else
            {
                //user exists. inform that username is already taken
                out.println("<html><head><script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\">   </script>\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <!--  Bootstrap  -->\n" +
"        <script type=\"text/javascript\" src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js\"></script>\n" +
"        <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css\">\n" +
"        <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css\">\n" +
"</head><body style=\"background-color:#87CEEB\"><p>User Already Exists!! <br>Click on Back to go back to Registering Page.</p> <input type=\"button\" class=\"btn\" name=\"back\" value=\"Back\" style=\"margin-left:48em;width: 5em; margin-top:2em\" onclick=\"window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Registration_Page.jsp'\";\"></body></html>");
                return;
            }
        } finally {
            out.close();
        }
    }

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
