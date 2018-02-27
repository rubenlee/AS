<%-- 
    Document   : list
    Created on : 26-feb-2018, 9:43:51
    Author     : Usuario
--%>

<%@page import="Session.Cart"%>
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
        <table class="table">
            <tbody>
                <tr>
                    <td><img class="img-thumbnail" src="Img/Products/1700X.jpg" alt="something" width="200" height="200">
                         <p>300$</p> 
                         <form action="SessionServlet" method="POST">
                            <input type="hidden" name="id" value="00001">
                            <input type="hidden" name="name" value="ATI 1700X">
                            <input type="hidden" name="value" value="300">
                            <input type="hidden" name="command" value="ListCommand">
                            <input class="btn btn-primary" type="submit" value="añadir">
                        </form>
                    </td>
                    <td><img class="img-thumbnail" src="Img/Products/HD6870.jpg" alt="something" width="200" height="200">
                         <p>249$</p>
                         <form action="SessionServlet" method="POST">
                            <input type="hidden" name="id" value="00002">
                            <input type="hidden" name="name" value="AMD HD6870">
                            <input type="hidden" name="value" value="249">
                            <input type="hidden" name="command" value="ListCommand">
                            <input class="btn btn-primary" type="submit" value="añadir">
                        </form>
                    </td>
                    <td><img class="img-thumbnail" src="Img/Products/gtx1060.jpg" alt="something" width="200" height="200">
                         <p>150$</p>
                         <form action="SessionServlet" method="POST">
                            <input type="hidden" name="id" value="00003">
                            <input type="hidden" name="name" value="NVIDIA gtx1060">
                            <input type="hidden" name="value" value="150">
                            <input type="hidden" name="command" value="ListCommand">
                            <input class="btn btn-primary" type="submit" value="añadir">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td><img class="img-thumbnail" src="Img/Products/gtx970.jpg" alt="something" width="200" height="200">
                         <p>95$</p>
                         <form action="SessionServlet" method="POST">
                            <input type="hidden" name="id" value="00004">
                            <input type="hidden" name="name" value="NVIDIA gtx970">
                            <input type="hidden" name="value" value="95">
                            <input type="hidden" name="command" value="ListCommand">
                            <input class="btn btn-primary" type="submit" value="añadir">
                        </form>
                    </td>
                    <td><img class="img-thumbnail" src="Img/Products/i7.jpg" alt="something" width="200" height="200">
                         <p>500$</p>
                         <form action="SessionServlet" method="GET">
                            <input type="hidden" name="id" value="00005">
                            <input type="hidden" name="name" value="INTEL core i7">
                            <input type="hidden" name="value" value="500">
                            <input type="hidden" name="command" value="ListCommand">
                            <input class="btn btn-primary" type="submit" value="añadir">
                        </form>
                    </td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
