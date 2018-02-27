<%-- 
    Document   : index
    Created on : Feb 26, 2018, 5:59:25 PM
    Author     : ruben
--%>

<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart; %>

<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="text-center" style="background-color:#f0fbff">
        <ul class="nav justify-content-end border-bottom">
            <li class="nav-item">
                    <form action="FrontServlet" method="POST">
                        <input type="hidden" name="command" value="IndexCommand">
                        <input class="btn btn-link" type="submit" value="Pagina principal">
                    </form>
            </li>
            <li class="nav-item">
                    <form action="FrontServlet" method="POST">
                        <input type="hidden" name="command" value="ListCommand">
                        <input class="btn btn-link" type="submit" value="Productos">
                    </form>
            </li>
            <%  username = (String) session.getAttribute("user");
                cart = (Cart) session.getAttribute("cart");
                if(username != null){
            %> <li> <a class="nav-link disabled"> <% out.println("Bienvenido " + username); %> </a> </li>
                <li> <form action="FrontServlet" method="GET">
                        <input type="hidden" name="command" value="CartCommand">
                        <button class="btn btn-link" type="submit">Cesta <span class="badge badge-secondary">
                <%if(cart != null){
                    out.print(cart.getContents().size());
                }%>
                        </button></span></form><a>  </a></li>
                <li class="nav-item">
                    <form action="SessionServlet" method="GET">
                        <input type="hidden" name="command" value="IndexCommand">
                        <input type="hidden" name="logout" value="yes">
                        <input class="btn btn-secondary" type="submit" value="Desloguearse">
                    </form>
                </li> 
            <% } else { %>
                <li class="nav-item">
                    <form action="FrontServlet" method="GET">
                        <input type="hidden" name="command" value="ProfileCommand">
                        <input class="btn btn-primary" type="submit" value="Loguearse">
                    </form>
                </li>
            <% } %>
        </ul>
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