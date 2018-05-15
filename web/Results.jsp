<%-- 
    Document   : list
    Created on : 26-feb-2018, 9:43:51
    Author     : Usuario
--%>


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
            <tbody>
                <tr>
                    <c:forEach var="product" items="${results}">
                        <td><p>${product.name}</p>
                            <img class="img-thumbnail" src="${product.image}" alt="something" width="200" height="200">
                            <p>${product.price}$</p> 
                            <form action="FrontServlet" method="POST">
                                <input type="hidden" id="id" name="id" value="${product.id}">
                                <input type="hidden" id="price" name="price" value="${product.price}">
                                <input type="hidden" name="command" value="AddCommand">
                                <input class="btn btn-primary" type="submit" value="añadir">
                            </form>
                        </td>
                    </c:forEach>
                </tr>
            </tbody>
        </table>
        <%@include file="/Adds/Footer.jsp" %>
    </body>
</html>
