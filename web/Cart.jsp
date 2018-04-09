<%-- 
    Document   : Cart
    Created on : Feb 26, 2018, 9:28:34 PM
    Author     : ruben
--%>
<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String username; %>
<% Cart cart;
%>


<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="text-center" style="background-color:#f0fbff">
        <%@include file="/Adds/Header.jsp" %>
        <table class="table">
            <thead>
            <th scope="col">Producto</th>
            <th scope="col">Nº identificación</th>
            <th scope="col">Precio</th>
        </thead>
        <tbody>
            <%
                int total = 0;
                for (Item item : cart.getContents()) { %>
            <tr class="table-info">
                <td> <% out.print(item.getName()); %> </td>
                <td> <% out.print(item.getId()); %> </td>
                <td> <% out.print(item.getValue()); %> $</td>
            </tr>
            <% } %>
            <tr>
                <td></td><td class="table-info">Total:</td>
                <td class="table-info"> <% out.print(session.getAttribute("total"));%> $</td> 
            </tr>
        </tbody>
    </table>
    <%@include file="/Adds/Footer.jsp" %>
</body>
</html>
