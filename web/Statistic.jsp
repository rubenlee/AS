<%-- 
    Document   : Statistic
    Created on : Apr 9, 2018, 4:33:03 PM
    Author     : ruben
--%>

<%@page import="Session.Cart"%>
<%@page import="Session.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! String username; %>
<%! Cart cart;%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>The webShop</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="main.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="/Adds/Header.jsp" %>
        <h4>Numero de logins: ${login}</h4>
        <h4>Numero de logoffs: ${logoff}</h4>
        <h4>Numero de Productos añadidos a la cesta: ${productsClicked}</h4>
        <h4>Numero de veces acceso al FrontServlet: ${frontServlet}</h4>
        <h4>Numero de veces acceso al SessionServlet: ${sessionServlet}</h4>
        <h4>Numero de veces acceso a la página principal: ${index}</h4>
        <h4>Numero de veces acceso a la página de productos: ${list}</h4>
        <h4>Numero de veces acceso a la página de cesta: ${cart}</h4>
        <h4>Numero de veces acceso a la página de logueo: ${profile}</h4>
        <h4>Numero de veces acceso a la página de error: ${Unknown}</h4>
        <h4>Numero de veces acceso al la página de iniciar sesion: ${sign}</h4>
        <h4>Numero de veces acceso al la página de estadisticas: ${statistic}</h4>
        <h4>Numero de veces acceso al la página de log: ${singletonAccess}</h4>
        <h4>Numero de veces acceso al singleton: ${singletonAccess}</h4>
        <%@include file="/Adds/Footer.jsp" %>
    </body>
</html>
