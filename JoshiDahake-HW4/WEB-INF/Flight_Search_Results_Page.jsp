<%-- 
    Document   : Flight_Search_Results_Page
    Created on : Jan 27, 2014, 9:14:03 PM
    Author     : nikhildahake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Search Results Page</title>
        <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/themes/base/jquery-ui.css" type="text/css" media="all" />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.1/jquery-ui.min.js"></script>
         <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
             
        <script language="JavaScript">
                   
            function setValuesAndSubmit(row_id){
                document.getElementById('row_id').value=row_id;
                document.getElementById('selected_flight_number').value=document.getElementById('flight_Number_'+row_id).value;
                document.getElementById('selected_flight_source').value=document.getElementById('flight_source_'+row_id).value;
                document.getElementById('selected_flight_destination').value=document.getElementById('flight_destination_'+row_id).value;
                document.getElementById('selected_flight_date').value=document.getElementById('flight_date_'+row_id).value;
                document.getElementById('selected_departure_time').value=document.getElementById('flight_departureTime_'+row_id).value;
                document.getElementById('selected_arrival_time').value=document.getElementById('flight_arrivalTime_'+row_id).value;
                document.getElementById('selected_numberOfStops').value=document.getElementById('flight_getNoOfStops_'+row_id).value;
                document.getElementById('selected_cost').value=document.getElementById('flight_cost_'+row_id).value;
                document.getElementById('selected_operator').value=document.getElementById('flight_operator_'+row_id).value;
                
                document.getElementById("view_book_flight").submit();
            }
        </script>
    </head>
    <body style="background-color:#87CEEB">
        <h3 align="center">Flight Search Results Page!! </h3>    
        <% if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/NavigationServlet?fwdTo=login.jsp");
                return;
         }%>
            
        <input type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout" class="btn"/>  
        <div style="float:right;"><label for="UserName:" style="float:right;margin-right:2em;">UserName: <%= session.getAttribute("username")%></label></div>
        <div></div>
        <form id ="view_book_flight" name="view_book_flight" action="FlightSearchResult_Servlet" method="POST">

            <input type="hidden" name="row_id" id="row_id"/>
            <input type="hidden" name="selected_flight_number" id="selected_flight_number"/>
            <input type="hidden" name="selected_flight_source" id="selected_flight_source"/>
            <input type="hidden" name="selected_flight_destination" id="selected_flight_destination"/>
            <input type="hidden" name="selected_flight_date" id="selected_flight_date"/>
            <input type="hidden" name="selected_departure_time" id="selected_departure_time"/>
            <input type="hidden" name="selected_arrival_time" id="selected_arrival_time"/>
            <input type="hidden" name="selected_numberOfStops" id="selected_numberOfStops"/>
            <input type="hidden" name="selected_cost" id="selected_cost"/>
            <input type="hidden" name="selected_operator" id="selected_operator"/>
            <div class="container-fluid">
            <table border ="1" class="table table-striped table-bordered table-condensed" style="width: 100em; margin-left: auto;margin-right: auto;">
                <tr>
                    <th>Flight Number</th>
                    <th>Source</th>
                    <th>Destination</th>
                    <th>Flight Date</th>
                    <th>Departure Time</th>
                    <th>Arrival Time</th>
                    <th>Number of Stops</th>
                    <th>Operator</th>
                    <th>Cost</th>
                    <th>View and Book</th>
                </tr>
                <%= session.getAttribute("htmlSearchResultString")%>
            </table>
            </div>
        </form>
        <div>
            <input type="hidden" name="backFunc" id="backFunc"/>
            <input type="button" name="back" value="Back" onclick= "window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp';" style="margin-left:32em;width: 5em; margin-top:2em" class="btn">
        </div>
    </body>
</html>
