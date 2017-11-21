<%-- 
    Document   : Comprobacion
    Created on : 18-nov-2017, 14:56:14
    Author     : rodri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Venus</title>
        <meta charset="UTF-8">
        <link href="../css/Ingresar.css" rel="stylesheet" type="text/css"/>
        <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
        <link rel="shortcut icon" type="image/x-icon" href="../Img/logo3.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            function comprobar() {
                //Ingresamos un mensaje a mostrar
                var mensaje = confirm("¿Estas seguro de que los datos son correctos?");
                //Detectamos si el usuario acepto el mensaje
                if (mensaje) {
                    document.formulario.submit();
                }
            }
        </script>
    </head>
    <body>
        <div class="Todo">
            <div class="Div">
                <Img src="../Img/logo1.jpg" alt="logo">
            </div>
            <div class="Div2">
                <div class="ochenta"><br><br>
                    <form action="../Comprobacion" method="POST"name="formulario">
                        Confirmar usuario:<br><br>
                        <input type="text" placeholder="Username" name="user">
                        <input type="text" placeholder="Correo electronico" name="correo">
                        <input type="password" placeholder="Contraseña" name="contra"><br><br>
                        <center><button onclick="comprobar()">Confirmar</button>
                            <br><br><br>
                        </center><br><br><br>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
