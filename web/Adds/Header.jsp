<%-- 
    Document   : Header
    Created on : Apr 9, 2018, 2:16:01 AM
    Author     : ruben
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <ul class="nav justify-content-end border-bottom">
        <li class="nav-item">
            <a href="FrontServlet?command=SearchCommand"class="btn navbar-btn ml-2"> Buscar</a> 
        </li>
        <li class="nav-item">
            <a href="FrontServlet?command=ListCommand"class="btn navbar-btn ml-2"> Ver Productos</a> 
        </li>
        <c:if test="${user.id == 1}">
            <li class="nav-item">
                <a href="FrontServlet?command=ViewDiscountCommand"class="btn navbar-btn ml-2"> Cupones</a> 
            </li>
            <li class="nav-item">
                <a href="FrontServlet?command=LogCommand"class="btn navbar-btn ml-2"> Ver log</a> 
            </li>
            <li class="nav-item">
                <a href="FrontServlet?command=StatisticCommand"class="btn navbar-btn ml-2"> Ver estadisticas</a> 
            </li>
        </c:if>
        <c:if test="${user != null}">
            <li> <a class="nav-link" href="FrontServlet?command=WalletCommand"> ${wallet.cuantity} <span class="badge badge-primary">+</span></a> </li>
            <li> <a class="nav-link" href="FrontServlet?command=ProfileCommand"> ${user.name} </a> </li>
            <li> <form action="FrontServlet" method="GET">
                    <input type="hidden" name="command" value="CartCommand">
                    <button class="btn btn-link" type="submit">Cesta <span class="badge badge-secondary">
                            ${cart.size}
                    </button></span></form><a>  </a></li>
            <li class="nav-item">
                <a href="FrontServlet?command=SignOffCommand"class="btn navbar-btn btn-primary ml-2"> Salir</a> 
            </li> 
        </c:if>

        <c:if test="${user == null}">
            <a href="FrontServlet?command=SignCommand"class="btn navbar-btn btn-primary ml-2"> Loguearse</a> 
        </c:if>
        <li class="nav-item">

        </li>

    </ul>
</html>