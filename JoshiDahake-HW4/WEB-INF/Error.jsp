<%-- 
    Document   : Error.jsp
    Created on : Apr 26, 2014, 10:28:40 PM
    Author     : sanika
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Airline Reservation System</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
         <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">


    <body style="background-color:#87CEEB">
        <div>
        <h1>No Travel Packages for this Destination!!</h1>
        <button type ="button" class="btn" onclick="window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=View_and_Book_page.jsp'">Back</button>
        </div>
    </body>
</html>
