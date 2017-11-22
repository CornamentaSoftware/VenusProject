<%-- 
    Document   : salir
    Created on : 06-nov-2017, 18:17:49
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        session.invalidate();
        response.sendRedirect("http://localhost:8084/VenusProject/Plantillas/Inicio.html");
        %>
        
        <h1>Hello World!</h1>
    </body>
</html>
