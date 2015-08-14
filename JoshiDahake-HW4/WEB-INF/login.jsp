<%-- 
    Document   : Login
    Created on : Jan 27, 2014, 9:40:03 AM
    Author     : Sanika
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

        <script language="JavaScript">
            
            function are_cookies_enabled()
            {
                var cookieEnabled = (navigator.cookieEnabled) ? true : false;

                if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled)
                { 
                    document.cookie="testcookie";
                    cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
                }
                return (cookieEnabled);
            }
    
            function getCookie(userName) {
                var name = userName + "=";
                var ca= document.cookie.split(';');
                for( var i=0; i<ca.length; i++) {
                    var c= ca[i].trim();
                    if(c.indexOf(name)== 0)
                        return c.substring(name.length,c.length);
                    else
                        return "";
                }
            }
        
            function checkCookie() {
                var username=  getCookie("username");
                if(username != "" && username !=null) {
                        
                    document.getElementById("username").value = username;
                    document.getElementById("rememberMe").checked= true;
                   // alert("Welcome again " + username);
                } 
                else {
                    if (!are_cookies_enabled()) {
                        alert('Cookies are not enabled in your browser!!');
                    }
                    document.getElementById("rememberMe").value= false;
                }
            }
        
            function setCookie(cname,cvalue) {
                var d= new Date();
                d.setTime(d.getTime()+24*60*60*60*1000);
                var expires= "expires=" + d.toGMTString();
                document.cookie = cname + "=" + cvalue + "; " + expires;
            }
        
            function rememberMeFunc() {
                var username = document.forms["loginForm"]["username"].value;
                if(username != "" && username != null && are_cookies_enabled() && document.getElementById("rememberMe").checked) {
                    setCookie("username",username);
                }
                
            }
               
            function validateAndSubmit()
            { 
               
                $( "#checkLogin").text("");
                var username = document.forms["loginForm"]["username"].value;
                var pass=document.forms["loginForm"]["password"].value;
                if(username==null||username=="")
                {
                    alert("Invalid username...try again!");
                    return false;
                        
                }
                if (pass == null || pass=="" )
                {
                    alert("Invalid password...try again!");
                    return false;
                }
                var url="UserValidation_Ajax?username="+username+"&password="+pass;
                
                $( "#checkLogin").load(url,function(response, status, xhr) {
                   
                    if(xhr.responseText.trim()== 'Success')
                    {
                        if(document.getElementById("rememberMe").checked)
                            {
                                 rememberMeFunc();
                            }
                            else
                                {
                                   document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                                }
                            
                        document.getElementById("loginForm").submit();
                    }
                } );
            }
        </script>
    </head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    
    <body style="background-color:#87CEEB">
        
        <h1 align="center">Airline Reservation System</h1> 
        <div class="container-fluid">
        <form name="loginForm" method="post" action="LoginServlet" id="loginForm">
           <table class="table table-striped table-bordered table-condensed" style="width: 49%;margin-left: 33em;"> 
                <tr>
                    <td>
                        &nbspUserName: <input type="text" id="username" name="username" style="margin-top:1em; margin-left:2.4em"/>   </br>             
                        &nbspPassword: <input type="password" name="password"  style="margin-top:1em; margin-left:3em"/></br>
                        <div id="checkLogin">
                        </div>
                        <a href="#" onclick="window.location='/Assignment_1_Flight_Reservation_System/NavigationServlet?fwdTo=Registration_Page.jsp'">New User ?</a>
                        <input type="checkbox" id="rememberMe" name="rememberMe"> Remember Me ?<I>(Cookies must be enabled in your browser)</I>
                        <div class="span6" style="text-align:center">
                        <input type="button" onclick="validateAndSubmit()" value="login" class="btn"/> 
                        </div>
                    </td>  
                </tr>
            </table>
        </form>
        </div>
            <script>
            window.onload=checkCookie() ; 
        </script>
    </body>
</html>
