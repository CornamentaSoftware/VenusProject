<%-- 
    Document   : redireccionar
    Created on : 06-nov-2017, 17:30:24
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <script>
        function mandar(){
            document.formulario.submit() 
        }
    </script>
    <body onload="mandar()">
        <%
            String usuario = (String) session.getAttribute("usuario");
            String contrasenia = (String) session.getAttribute("contrasenia");
        %>
        <form name="formulario" action="http://localhost:8084/VenusProject/LoginServlet" method="POST">
            <div>
                <input type="text" name="user" value="<%=usuario%>" visibility="hidden">
                <input type="text" name="contra" value="<%=contrasenia%>" visibility="hidden">
            </div>
        </form>
    </body>
</html>
