<%-- 
    Document   : Travel_Packages
    Created on : Mar 30, 2014, 6:55:51 PM
    Author     : sanika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travel Packages</title>
         <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

        <script language="JavaScript">
            function checkRadioButton() {
                var selectedValue="";
                var selectedHotelValue= "";
                var selectedCar = $('input[name=car]:radio:checked');
                var selectedHotel = $('input[name=hotel]:radio:checked');
                if (selectedCar.length > 0) {
                    selectedValue = selectedCar.val();
                    document.getElementById("carId").value = selectedValue;
                } 
        
                if(selectedHotel.length > 0) {
                    selectedHotelValue= selectedHotel.val();
                    document.getElementById("hotelId").value = selectedHotelValue;
                } 
        
                if(!selectedCar.length > 0 && !selectedHotel.length >0 ){
                    alert("Please select either car or hotel package to checkout");
                    return;
                }
       
                document.getElementById("wasTravelPackagesCheckoutPerformed").value = 'yes'; 
       
                document.getElementById("TravelPackagesForm").submit();
            }
       
        </script>
    </head>

    <body style="background-color:#87CEEB">
        <jsp:useBean id="counter" 
                     class="super_airways.PageAccessCountBean"
                     scope="application">
            <jsp:setProperty name="counter" 
                             property="firstPage"
                             value="Travel_Packages.jsp" />
        </jsp:useBean>
        <div>
            <input class="btn" type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout"/>  
            <label for="UserName:" style="float:right;margin-right:2em;margin-top:0.5em">UserName: <%= session.getAttribute("username")%></label>
        </div>
        <h1 align="center">Travel Packages</h1>
        <h4 align="center">Destination <%=session.getAttribute("selectedFlightDestination")%></h4>
        <form name="TravelPackagesForm" id="TravelPackagesForm" action="AddTravelPackage_Servlet" method="POST" style="align-items: center; margin-left: 29em;">
            <table class="table table-striped table-bordered table-condensed" style="width: 40em;" border ="1">
                <th>Cars</th>
                <th>Hotels</th>
                <tr>
                    <td>
                        <%= session.getAttribute("listOfCars")%>                
                    </td>
                    <td>
                        <%= session.getAttribute("listOfHotels")%>
                    </td>
                </tr>
            </table>
            <div style="align-items: center; margin-left: 10em;">
                <button type="button" onclick="checkRadioButton();" class="btn">Add</button>
                <button class="btn" type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/FlightSearchResult_Servlet?selected_flight_number='+<%= session.getAttribute("selectedFlightNumber")%>">Back</button>
            </div>
            <input type ="hidden" id="wasTravelPackagesCheckoutPerformed" name="wasTravelPackagesCheckoutPerformed" value=""/>
            <input type ="hidden" id="carId" name="carId" value=""/>
            <input type="hidden" id="hotelId" name="hotelId" value=""/>
            <input type="hidden" name="selected_flight_number" id="selected_flight_number" value="<%= session.getAttribute("selectedFlightNumber")%>"/>
        </form>
        <jsp:setProperty name="counter" property="accessCountIncrement" value="1" />             
    </body>
    <footer>
        <p>This page has been accessed <jsp:getProperty name="counter" property="accessCount"/> times!!</p>
    </footer>
</html>
