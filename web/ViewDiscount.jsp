<%-- 
    Document   : Cart
    Created on : Feb 26, 2018, 9:28:34 PM
    Author     : ruben
--%>
<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



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
            <th scope="col">Nombre</th>
            <th scope="col">% de rebaja</th>
            <th scope="col"></th>
        </thead>
        <tbody>
            <c:forEach var="discount" items="${discounts}">
            <tr class="table-info">
                <td> ${discount.name} </td>
                <td> ${discount.amount} </td>
                <td> <a href="FrontServlet?command=EliminateDiscountCommand&id=${discount.id}"> Eliminar</a> </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <form class="form-signin" action="FrontServlet" method="Post">
        <label for="code"> Nuevo codigo descuento</label>
        <input type="text" id="new" name="new" placeholder="codigo" required> 
        <input type="text" id="value" name="value" placeholder="%" required> 
        <input type="hidden" name="command" value="ViewDiscountCommand">
        <input class="btn btn-primary" type="submit" value="Añadir">
    </form>
    <%@include file="/Adds/Footer.jsp" %>
</body>
</html>
