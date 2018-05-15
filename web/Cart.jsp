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
            <th scope="col">Producto</th>
            <th scope="col">Nº identificación</th>
            <th scope="col">Precio</th>
        </thead>
        <tbody>
            <c:forEach var="product" items="${list}">
            <tr class="table-info">
                <td> ${product.name} </td>
                <td> ${product.id} </td>
                <td> ${product.price} $</td>
            </tr>
            </c:forEach>
            <tr>
                <td></td><td class="table-info">Total:</td>
                <td class="table-info"> ${total} $</td> 
            </tr>
        </tbody>
    </table>
    <form class="form-signin" action="FrontServlet" method="Post">
        <label for="code"> Cod. descuento</label>
        <input type="text" id="code" name="code" placeholder="codigo" > 
        <input type="hidden" name="command" value="DiscountCommand">
        <input class="btn btn-primary" type="submit" value="Descontar">
    </form>
    <form class="form-signin" action="FrontServlet" method="Post">
        <input type="hidden" name="command" value="PayCommand"> 
        <c:if test="${total <= wallet.cuantity}">
            <input class="btn btn-primary" type="submit" value="Comprar">
        </c:if>
        <c:if test="${total > wallet.cuantity}">
            <input class="btn btn-primary" type="submit" value="Comprar" disabled>
        </c:if>
    </form>
    <%@include file="/Adds/Footer.jsp" %>
</body>
</html>
