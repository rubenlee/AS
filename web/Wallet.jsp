<%-- 
    Document   : profile
    Created on : 26-feb-2018, 9:43:39
    Author     : Usuario
--%>
<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart;%>
<!DOCTYPE html>
<html>
    <%@include file="/Adds/Header.jsp" %>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="signin.css" rel="stylesheet">
    </head>
    <body class="text-center" style="background-color:#f0fbff">
        <form class="form-signin" action="FrontServlet" method="GET">
            <input type="hidden" name="command" value="WalletCommand">
            <label for="money"> Cantidad a ingresar </label><br>
            <input type="number" id="money" name="money" placeholder="0" > <br><br>
            <input class="btn btn-primary" type="submit" value="Aceptar">
        </form>
        <%@include file="/Adds/Footer.jsp" %>
</html>
