<%-- 
    Document   : Header
    Created on : Apr 9, 2018, 2:16:01 AM
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            if (username != null) {
        %> <li> <a class="nav-link disabled"> <% out.println("Bienvenido " + username); %> </a> </li>
        <li> <form action="FrontServlet" method="GET">
                <input type="hidden" name="command" value="CartCommand">
                <button class="btn btn-link" type="submit">Cesta <span class="badge badge-secondary">
                        <%if (cart != null) {
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
        <% }%>
    </ul>
</html>