<%-- 
    Document   : index
    Created on : Feb 26, 2018, 5:59:25 PM
    Author     : ruben
--%>

<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; 
    Cart cart;%>

<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="main.css" rel="stylesheet">
    </head>
    <body class="text-center" style="background-color:#f0fbff">
        <%@include file="/Adds/Header.jsp" %>
        <img src="Img/logo.png" alt="logo.png" class="rounded mx-auto d-block" width="500" height="400">
        <div class="btn-group">
            <form action="FrontServlet" method="GET">
                <input type="hidden" name="command" value="UnknownCommand">
                <input class="btn btn-outline-primary" type="submit" value="En desarrollo...">
            </form>
        </div>    
        <div class="btn-group">
            <form action="FrontServlet" method="GET">
                <input type="hidden" name="command" value="ListCommand">
                <input class="btn btn-outline-primary" type="submit" value="Ver productos">
            </form>
        </div>
        <%@include file="/Adds/Footer.jsp" %>
    </body>

</html>