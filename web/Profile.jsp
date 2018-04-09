<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart;%>
<!DOCTYPE html>
<html>
    <%@include file="/Adds/Header.jsp" %>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="signin.css" rel="stylesheet">
    </head>
    <body class="text-center" style="background-color:#f0fbff">
        <br>
        <form class="form-signin" action="EditServlet" method="GET">
            <div>
                <label for="username"> Usuario </label><br>
                
                <input type="text" id="username" name="username" placeholder="Usuario" value="${name}"> 
            </div>
            <div>
                <label for="username"> Dirección </label><br>
                <input type="text" id="adress" name="adress" placeholder="Direccion" value="${adress}"> 
            </div>
            <div>
                <label for="username"> Email </label><br>
                <input type="text" id="email" name="email" placeholder="Email" value="${email}"> 
            </div>
            <input type="hidden" name="command" value="ProfileCommand"> <br><br>
            <input class="btn btn-primary" type="submit" value="Cambiar">
        </form>
        <%@include file="/Adds/Footer.jsp" %>
</html>
