<%-- 
    Document   : Flight_Search_Query_Page
    Created on : Jan 27, 2014, 9:12:41 PM
    Author     : nikhildahake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Flight Search Page</title>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
        <style>
            .td {
                border : none;
            }
        </style> 
        <script language="JavaScript">
            /*Function for validating the input*/  
        function isValidDate(dateString)
        {
            // First check for the pattern
            if(!/^\d{2}\/\d{2}\/\d{4}$/.test(dateString))
            return false;

            // Parse the date parts to integers
            var parts = dateString.split("/");
            var day = parseInt(parts[1], 10);
            var month = parseInt(parts[0], 10);
            var year = parseInt(parts[2], 10);

            // Check the ranges of month and year
            if(year < 1000 || year > 3000 || month == 0 || month > 12)
            return false;

            var monthLength = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

            // Adjust for leap years
            if(year % 400 == 0 || (year % 100 != 0 && year % 4 == 0))
            monthLength[1] = 29;

            // Check the range of the day
            return day > 0 && day <= monthLength[month - 1];
         };
            
         function validateAndSubmit() {
                var source = document.getElementById("source");
             
                source = source.value;
                // alert(source);
                var destination = document.forms["searchFlightForm"]["destination"].value;
                var dateOfTravel = document.forms["searchFlightForm"]["date_of_travel"].value;
                var numberOfSeats = document.forms["searchFlightForm"]["no_of_seats"].value;
                var arr = {"source" : source, "destination" : destination, "dateOfTravel" : dateOfTravel, "numberOfSeats" : numberOfSeats}; 
                var allFieldsNotEmpty= true;
                var allValid = true;
                for (var key in arr) {
                    var variable_arr = arr[key];
                    if(!variable_arr) {
                        alert("Please enter a value for " + key + "!!");
                        allFieldsNotEmpty = false;
                        break;
                    }
                }
                if(!isValidDate(dateOfTravel) && allFieldsNotEmpty) {
                  alert("Please enter the date in the MM/DD/YYYY format");
                  allValid=false;
                }
                if(allValid && allFieldsNotEmpty) {
                    document.getElementById("searchFlightForm").submit(); 
                }
            }
         
        </script>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" type="text/css" media="all" />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(function() {
                $( "#date_of_travel" ).datepicker();
            });
            
                
        </script>
    </head>

    <body style="background-color:#87CEEB">
        <% if(session.getAttribute("user") == null) 
         {   
             response.sendRedirect(request.getContextPath()+"/NavigationServlet?fwdTo=login.jsp");
             return;
         } %>
        
        <div><input type="button" class="btn" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout"/>  
            <label for="UserName:" style="float:right;margin-right:2em;margin-top:0.5em">UserName: <%= session.getAttribute("username")%></label>

        </div>
        <h1 style="text-align:center;">Flight Search Page</h1>
        <form class="form-horizontal" name="searchFlightForm" method="post" action="FlightSearchQuery_Servlet" id="searchFlightForm" style="width: 30em;margin-left: 33em;">
            <table class="table table-striped table-bordered table-condensed" border="1" >
                    <tr><td>&nbspSource          
                                <%= session.getAttribute("sources")%>
                        </td><td><input type="text" id="source" name="source" >       
                        </td></tr>
                    <tr><td>&nbspDestination  
                            <%= session.getAttribute("destinations")%></td>
                        <td><input type="text" id="destination"  name="destination">     
                        </td>
                    </tr>
                    <tr><td>&nbspDate Of Travel</td><td>  <input type="text" id="date_of_travel" name="date_of_travel"  placeholder="MM/DD/YYYY"></td></tr>
                    <tr><td>&nbspNumber of Seats </td><td><input type="text" name="no_of_seats" ></td></tr>
                    <tr><td>&nbspClass </td>           
                        <td> <select  name="class">
                                <option value="economy">Economy</option>
                                <option value="business">Business</option>
                                <option value="firstclass">First Class</option>
                            </select>
                        </td>
                    </tr>
                </table>
            <div class="control-group" style="margin-left: 7em;">
                <input type="button" value="Search" onclick="validateAndSubmit()" class="btn"/>
                <input type="button" onclick="window.location='/Assignment_1_Flight_Reservation_System/BookingHistory_Servlet'" value="Booking History" class="btn"/>
            </div>
        </form>
    </body>
</html>
