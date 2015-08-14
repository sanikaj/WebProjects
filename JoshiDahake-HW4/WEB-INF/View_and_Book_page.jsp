<%-- 
    Document   : View_and_Book_page
    Created on : Jan 27, 2014, 9:14:31 PM
    Author     : nikhildahake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View and Book Page</title>
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

        <script>
            function submitForm(){
                if(document.getElementById("no_of_seats").value=="" || document.getElementById("no_of_seats").value==null )
                {
                    alert("Check number of seats please!");
                    return;
                }
                document.getElementById("wasCheckoutPerformed").value = 'yes';
                document.getElementById("flightDetailsForm").submit();
            }
            
            function addMoreFlights()
            {
                if(document.getElementById("no_of_seats").value=="" || document.getElementById("no_of_seats").value==null )
                {
                    alert("Check number of seats please!");
                    return;
                }
                document.getElementById("flightDetailsForm").submit();
                
            }
        </script>
    </head>
    <body style="background-color:#87CEEB">
        <% if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/NavigationServlet?fwdTo=login.jsp");
                return;
            }%>
        <div>
            <input type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout" class="btn"/>  
            <label for="UserName:" style="float:right;margin-right:2em;margin-top:0.5em">UserName: <%= session.getAttribute("username")%></label>
        </div>
        <h3 style="text-align:center;margin-left:2em">View & Book</h3>
        <form id="flightDetailsForm" method="POST" action="AddFlightServlet">
            <table border ="1" class="table table-striped table-bordered table-condensed" style="width: 30em;margin-left: auto; margin-right: auto;">
                <tr>
                    <td>Flight Number</td>
                    <td><input type="hidden" name="selected_flight_number" id="selected_flight_number" value=<%= session.getAttribute("selectedFlightNumber")%> /> <%= session.getAttribute("selectedFlightNumber")%> </td>
                </tr>
                <tr>
                    <td>Departure</td>
                    <td><%= session.getAttribute("selectedFlightSource")%></td>
                </tr>
                <tr>
                    <td>Arrival</td>
                    <td><%= session.getAttribute("selectedFlightDestination")%>
                    <button type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/TravelPackages_Servlet?selected_flight_number='+document.getElementById('selected_flight_number').value" class="btn" style="margin-left:4em">Travel Packages</button></td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td><%= session.getAttribute("selectedFlightDate")%></td>

                </tr>
                <tr>
                    <td>Departure Time</td>
                    <td><%= session.getAttribute("selectedFlightDepartureTime")%></td>

                </tr>
                <tr>
                    <td>Arrival Time</td>
                    <td><%= session.getAttribute("selectedFlightArrivalTime")%></td>

                </tr>
                <tr>
                    <td>No of Stops</td>
                    <td><%= session.getAttribute("selectedFlightNumberOfStops")%></td>

                </tr>
                <tr>
                    <td>Cost</td>
                    <td><%= session.getAttribute("selectedFlightCost")%></td>

                </tr>
                <tr>
                    <td>Operator</td>
                    <td><%= session.getAttribute("selectedFlightOperator")%></td>

                </tr>
                <tr>
                    <td>No of Seats</td>
                    <td><input type="text" id="no_of_seats" name="no_of_seats"></td>
                </tr>
            </table>
            <br><br>
            <div align="center">
                <button type ="button" onclick="submitForm();" class="btn">Select And CheckOut</button>
                <button type ="button" onclick="window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp'" class="btn">Back</button>
                <button type ="button" onclick="window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp'" class="btn">Home</button>
                <button type ="button" onclick="addMoreFlights();" class="btn">Add More Flights
                </button>
            </div>
            <input type ="hidden" id="wasCheckoutPerformed" name="wasCheckoutPerformed" value=""/>
        </form>
    </body>
</html>
