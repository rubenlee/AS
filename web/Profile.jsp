<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <form class="form-signin" action="FrontServlet" method="GET">
            <div>
                <label for="username"> Usuario </label><br>
                <input type="text" id="username" name="username" placeholder="Usuario" value="${user.name}"> 
            </div>
            <div>
                <label for="city"> Dirección </label><br>
                <input type="text" id="city" name="city" placeholder="Direccion" value="${user.city}"> 
            </div>
            <div>
                <label for="phone"> Nº de telefono </label><br>
                <input type="text" id="phone" name="phone" placeholder="Nº de telefono" value="${user.phone}"> 
            </div>
            <div>
                <label for="pass"> Nueva contraseña</label><br>
                <input type="password" id="pass" name="pass"> 
            </div>
            <input type="hidden" name="command" value="ProfileEditCommand"> <br><br>
            <input class="btn btn-primary" type="submit" value="Cambiar">
        </form>
        <%@include file="/Adds/Footer.jsp" %>
</html>
