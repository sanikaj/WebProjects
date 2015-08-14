package super_airways;

import models.User;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 *
 * @author Sanika
 */
public class LoginServlet extends HttpServlet {
//static Logger logger= Logger.getLogger("LoginServlet");

    @Override
    public void init(ServletConfig config) throws ServletException {
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        HttpSession session = request.getSession(true);
          
        String username = request.getParameter("username");
        username = StringEscapeUtils.escapeHtml4(username);
        String password = request.getParameter("password");
        password = StringEscapeUtils.escapeHtml4(password);
        User user = new User();
        try {
            int i = 0;
    
            if (isValidParameters(username, response) && isValidParameters(password, response)) {
                if (user.findUser(username, password)) {
                    session.setAttribute("user", username);
                    session.setAttribute("username", username);
                    session.setAttribute("sources", (new Query_Database()).selectAllFlightsSourcesFromDB());
                    session.setAttribute("destinations", (new Query_Database()).selectAllFlightsDestinationsFromDB());
                    request.getRequestDispatcher("/WEB-INF/Flight_Search_Query_Page.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                /*a forward reuses the current request and a redirect creates a new request, hereby losing the initial request, including all of its attributes and parameters.*/
                request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (Exception exp) {
        }
       
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private boolean isValidParameters(String parameter, HttpServletResponse response) throws IOException {
        if (parameter != null || !parameter.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
