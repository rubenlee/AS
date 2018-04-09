<%-- 
    Document   : index
    Created on : Feb 26, 2018, 5:59:25 PM
    Author     : ruben
--%>

<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart;%>

<!DOCTYPE html>

<html>
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
    </body>
</html>