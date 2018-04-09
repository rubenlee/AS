<%-- 
    Document   : Statistic
    Created on : Apr 9, 2018, 4:33:03 PM
    Author     : ruben
--%>

<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart;%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="main.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Adds/Header.jsp" %>
        <h4>Numero de ${"login"}</h4>
        <h4>Numero de ${"logoff"}</h4>
        <h4>Numero de ${"productsClicked"}</h4>
        <h4>Numero de ${"frontServlet"}</h4>
        <h4>Numero de ${"sessionServlet"}</h4>
        <h4>Numero de ${"index"}</h4>
        <h4>Numero de ${"list"}</h4>
        <h4>Numero de ${"cart"}</h4>
        <h4>Numero de ${"profile"}</h4>
        <h4>Numero de ${"Unknown"}</h4>
        <h4>Numero de ${"singletonAccess"}</h4>
        <%@include file="/Adds/Footer.jsp" %>
    </body>
</html>
