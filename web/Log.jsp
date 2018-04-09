<%-- 
    Document   : Log
    Created on : Apr 9, 2018, 4:32:53 PM
    Author     : ruben
--%>

<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
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
    <body class="text-center" style="background-color:#f0fbff">
        <%@include file="/Adds/Header.jsp" %>
        <% for (String temp : ((String) request.getAttribute("log")).split(";")){
            out.println("<p>");
            out.println(temp);
            out.println("</p>");
        }
        %>
        <%@include file="/Adds/Footer.jsp" %>
    </body>
</html>
