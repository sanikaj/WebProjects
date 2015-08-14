<%-- 
    Document   : Transaction_Page
    Created on : Jan 27, 2014, 9:14:49 PM
    Author     : sanikajoshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flight Reservation Transaction Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
         <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
         <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">
 
        <script language="JavaScript">
            function validateAndSubmit()
            { 
                var name = document.forms["transaction_confirmation"]["Name"].value;
                var age=document.forms["transaction_confirmation"]["Age"].value;
                var sex= document.forms["transaction_confirmation"]["sex"].value;
                var arr = {"name" : name, "age" : age, "sex" : sex}; 
                
                for (var key in arr) {
                    var variable_arr = arr[key];
                    if(!variable_arr) {
                        alert("Please enter a value for " + key + "!!");
                        allValid = false;
                        break;
                    }
                }
            }
            
            function hideAccountDetails() {
                $("#bank_table").hide();
            }
            
            function confirm_func() {
              if(confirmClick() != 'false') {
              var accountHolderName = $("#account_holder_name").val();
              var accountNumber  = $("#account_number").val();
              var routingNumber = $("#routing_number").val();
              var bankNumber = $("#bank_number").val();
              $("#userName").val('<%= session.getAttribute("user").toString()%>');
              var ninerId = $("#userName").val();
              var totalCostForAllFlights= <%= session.getAttribute("totalCostForAllFlights").toString()%> 
              var totalPackagesCost = <%= session.getAttribute("packagesCost").toString() %>
              $.post("Banking_Servlet", {ninerId: ninerId,accountHolderName:accountHolderName, accountNumber: accountNumber, routingNumber: routingNumber, bankNumber: bankNumber, totalCostForAllFlights: totalCostForAllFlights, totalPackagesCost:totalPackagesCost }, function(data){
              if(data.trim().indexOf("Success") != -1) {
                 hideAccountDetails();
                 document.getElementById("checkLogin").innerHTML = data;
                 update_history_function(accountNumber,routingNumber,bankNumber,ninerId);
                 $("#confirm").hide();
              } else {
                 document.getElementById('account_holder_name').value=""
                 document.getElementById('account_number').value=""
                 document.getElementById('routing_number').value=""
                 document.getElementById('bank_number').value=""
                 document.getElementById("checkLogin").innerHTML = data;
                 document.getElementById('account_holder_name').focus();
               }
              });
            }
            
            }
            
            function update_history_function(accountNumber,routingNumber,bankNumber,ninerId) {
            
            $.post("UpdateBooking_Servlet", {accountNumber: accountNumber, routingNumber: routingNumber, bankNumber: bankNumber,ninerId: ninerId}, function(data){
                if(data.trim().indexOf("Success") != -1) {
                 alert("Updated your booking history!!");
              } else {
                 alert("Failure in updating your booking history");
              }
              });
            

            }
            
            function isNumber(n) {
                return !isNaN(parseFloat(n)) && isFinite(n);
            };
            
            function confirmClick () {
                var account_holder_name = document.getElementById("account_holder_name").value;
                var routing_number = document.getElementById("routing_number").value;
                var bank_number = document.getElementById("bank_number").value;
                
                if(account_holder_name==null || account_holder_name=="")
                {
                    alert("Check account holder name");
                    return false;
                }
                
                if(routing_number==null || routing_number=="")
                {
                    alert("Check routing number");
                    return false;
                }
                else
                {
                    if(isNumber(routing_number)&&routing_number.length<6)
                    {
                        alert("Check routing number");
                        return false;     
                    }
                }
                        
            
                if(bank_number==null || bank_number=="")
                {
                    alert("Check bank number");
                    return false;
                }else
                {
                    if(isNumber(bank_number)&&bank_number.length<6)
                    {
                        alert("Check bank number");
                        return false;     
                    }
                }
                
            };
                
            function cancelClick () {
                window.location.href = "Flight_Search_Query_Page.jsp";
            };
        </script>
    </head>
    <body style="background-color:#87CEEB">

        <% if (session.getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/NavigationServlet?fwdTo=login.jsp");
                return;
            }

        %>
        <div>
            <input type="button" onclick="location.href='/Assignment_1_Flight_Reservation_System/LogOutServlet'" style="float:right;" value="Logout" class="btn"/>  
            <label for="UserName:" id="UserName" style="float:right;margin-right:2em;margin-top:0.5em">UserName: <%= session.getAttribute("username")%></label>
        </div>
        <h1 style="text-align:center">Transaction Page</h1>
        <div class="container">
            <form id="ConfirmationForm" action="" method="POST">
                <table border="1" style="margin-left: auto; margin-right: auto;" class="table table-striped table-bordered table-condensed" >
                    <tr>
                        <th>Flight No.</th>
                        <th>Date</th>
                        <th>Departure Time</th>
                        <th>Arrival Time</th>
                        <th>Number of Stops</th>
                        <th>No Of seats booked</th>
                        <th>Total Cost</th>
                    </tr>

                    <%= session.getAttribute("tableStringForCart")%>
                    <tr> <td></td><td></td><td></td><td></td><td></td><td style="text-align:right">Total Cost:</td><td>  $ <%= session.getAttribute("totalCostForAllFlights")%></td>
                    </tr>
                </table>
                <h3 style="text-align:center">Travel Packages</h3>
                <center>  

                    <%if (session.getAttribute("hotelTravelPackages") != null) {%>
                    <%=session.getAttribute("hotelTravelPackages")%>
                    <% }%>

                    <%if (session.getAttribute("carTravelPackages") != null) {%>
                    <%=session.getAttribute("carTravelPackages")%>
                    <% }%>

                    <% if (session.getAttribute("totalCostForAllTravelPackages") != null) {%>
                    <%=session.getAttribute("totalCostForAllTravelPackages")%>
                    <% }%>


                </center>
                <div id="bank_table" >
                <h3 style="text-align:center;margin-top:2em">Bank Account Info</h3><br/>
                    <table border="1" style="margin-left: auto; margin-right: auto; width:25em" class="table table-striped table-bordered table-condensed" >
                        <tr><td>Account Holder Name : <input type="text" id="account_holder_name" name="account_holder_name" value="" style="margin-left:5px;float:right; text-align: left;"/></td></tr>
                        <tr><td>Account Number : <input type="text" id="account_number" name="account_number" value="" style="margin-left:5px;float:right; text-align: left;"/></td></tr>
                        <tr><td>Routing Number :  <input type="text" id="routing_number" name="routing_number" value="" style="margin-left:5px;float:right; text-align: left"/></td></tr>
                        <tr><td>Bank Number :  <input type="text" id="bank_number" name="bank_number" value="" style="margin-left:5px;float:right; text-align: left"/></td></tr>
                    </table>
                <input type="hidden" id="totalCostForAllFlights" /> 
                <input type="hidden" id="packagesCost"/>
                <input type="hidden" id="userName"/>
                
                </div>    
                <div id="checkLogin">
                </div>
                <div>
                    <button type="button" id="confirm"  onclick="confirm_func()" class="btn">Confirm</button>
                    <button type="button" name= "cancel" onclick="document.location.href='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp';" class="btn">Cancel</button>
                    <button type="button" name= "home" onclick="document.location.href='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Flight_Search_Query_Page.jsp'" class="btn">Home</button>
                </div>
            </form>
        </div>
    </body>
</html>
