<%-- 
    Document   : Booking_History_Page
    Created on : Jan 27, 2014, 9:12:12 PM
    Author     : nikhildahake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History Page</title>
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
        <script language="JavaScript">
            function logOutSession() {
                session.invalidate();
                window.location='login.jsp';
        }
        </script>
    </head>
    <body style="background-color:#87CEEB">
        <h1 align="center">Booking History Page</h1>
        <% if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/NavigationServlet?fwdTo=login.jsp");
                return;
            }%>
        <div>
            <input class="btn" type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout"/>  
            <label for="UserName:" style="float:right;margin-right:2em;margin-top:0.5em">UserName: <%= session.getAttribute("username")%></label>
        </div>

        <table class="table table-striped table-bordered table-condensed" style="width: 75em; margin-left: auto; margin-right: auto;" border="1">
            <tr>
                <th>Booking ID</th>
                <th>Flight ID</th>
                <th>Date</th>
                <th>Number Of Seats</th>
                <th>Total Cost</th>
            </tr>
            <%= session.getAttribute("bookingsList")%>
        </table>
         <div class="span6" style="text-align:center">
        <button onclick="window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp'" class="btn">Home</button>
         </div>
         </body>
</html>
