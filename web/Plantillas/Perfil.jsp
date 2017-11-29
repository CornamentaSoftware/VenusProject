<%-- 
    Document   : Perfil
    Created on : 16/11/2017, 08:17:51 AM
    Author     : Alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tu perfil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Josefin+Slab" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
        <div>
            <div id="Nav">
            </div>
            <div id="Informacion">
                <img id="ppic" src="../Img/persona.png">
                <p id="nombre">Nombre de Usuario</p>
                <p id=""></p>
                <form action="../CAcceso" method="post">
                    <input type="text" value="CambiarD" name="Servicio" style="visibility: hidden">
                    <input type="submit" value="Cambiar datos">
                </form>
            </div>
            <div id="Contenido">
                <div id="pub">
                    <img id="ppic" src="../Img/persona.png">
                    <div id=contenido>
                        <p id="nombre">Nombre de usuario</p>
                        <p id="content">It all just sounds like ooh, ooh ooh hoo hoo, too young, too dumb to realize that I should have bought you flowers and held your hand…</p>
                    </div>
                </div>
            </div>
            <div id="Menu">
            </div>
        </div>
    </body>
</html>

