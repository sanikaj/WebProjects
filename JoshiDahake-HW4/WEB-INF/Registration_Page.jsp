<%-- 
    Document   : RegistrationPage
    Created on : Jan 27, 2014, 9:17:11 PM
    Author     : nikhildahake
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">   </script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--  Bootstrap  -->
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap.css">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.2/css/bootstrap-theme.min.css">

        <script language ="JavaScript">
            function validateAndSubmit()
            {
                var username = document.forms["registrationForm"]["username"].value;
                var pass=document.forms["registrationForm"]["pass"].value;
                var confirm_pass = document.forms["registrationForm"]["confirm_pass"].value;
                $( "#checkLogin").text("");
                if(username==null || username=="")
                {
                    alert("Invalid username...try again!");
                    return false;
                }
                if (pass!=confirm_pass)
                {
                    alert("Passwords do not match...try again!");
                    return false;
                }
                return true;
                
            }
        </script>
    </head>
    
    <body style="background-color:#87CEEB">
    <div class="container">
        <h1 style="text-align:center;margin-top:2em">Registration Page</h1>
        <form name="registrationForm" method="post" action="RegistrationPage_Servlet" onsubmit="javascript:return validateAndSubmit();" id="registrationForm">
         <table border="1" class="table table-striped table-bordered table-condensed" style="margin-left: auto; margin-right: auto;width:25em" >
             <tr><td>&nbspUsername<input type="text" name="username" style="margin-left:5px;float:right; text-align: left"/></td></tr>
             <tr><td>&nbspPassword<input type="password" name="pass" style="margin-left:5px;float:right; text-align: left"/></td></tr>
             <tr><td>&nbspConfirm Password<input type="password" name="confirm_pass" style="margin-left:5px;float:right; text-align: left"/></td></tr>
         </table> 
            <div id="checkLogin"></div>
            <div style="margin-left:40em;width: 5em; margin-top:3em">
             <input type="submit" value="Submit" class="btn"/>
            </div>
        </form>
      </div>
    </body>
</html>
