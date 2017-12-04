<%-- 
    Document   : Menu
    Created on : 04-nov-2017, 13:24:06
    Author     : rodri
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Venus</title>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Dancing+Script|Montserrat|Raleway" rel="stylesheet">
        <link href="../font-awesome-4.7.0/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="../css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="../css/menuA.css" rel="stylesheet" type="text/css"/>
        <link href="../css/General.css" rel="stylesheet" type="text/css"/>
        <link href="../css/botones.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" type="image/x-icon" href="../Img/logo3.png">
        <link href="../css/TimeLine.css" rel="stylesheet" type="text/css"/>
        <link href="../font-Awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <script>
        
        var cont=0;
        var cont2=0;
        var marcha=0;
        var cro=0;
        
        function inicia(){
            cont = window.setInterval("cierra()", 300000);
            cont2 = window.setInterval("avisa()", 241000);
        }
        
        function continua(){
            clearInterval(cont);
            inicia();
        }
        
        function cierra(){
            window.location.reload(true);
        }
        
        function avisa(){
            alert("La sesion se cerrara por inactividad en 30 segundos");
            HttpSession sesion = request.getSession();
            sesion.invalidate();
        }
        
        function deshabilitaRetroceso(){
            window.location.hash="no-back-button";
            window.location.hash="Again-No-back-button" //chrome
            window.onhashchange=function(){window.location.hash="no-back-button";}
        }
        
        function crono() {
            emp=new Date();
            divcrono=setInterval(tiempo,10); 
            marcha=1; 
        }
        
        function abandonar(){
           return 'Texto de aviso';
        }
        
        function tiempo() {
            actual=new Date();
            cro=actual-emp; 	
            cr=new Date(); 	
            cr.setTime(cro); 
            cs=cr.getMilliseconds(); 
            cs=cs/10;	
            cs=Math.round(cs); 	
            sg=cr.getSeconds(); 	
            mn=cr.getMinutes(); 	
            ho=(cr.getHours())-18; 	
            
            if (cs<10) {cs="0"+cs;} 
            if (sg<10) {sg="0"+sg;} 
            if (mn<10) {mn="0"+mn;} 
        }
        
        
    </script>
    <body onload="inicia(), deshabilitaRetroceso(), " onclick="continua()" onkeypress="continua()" OnMouseMove="continua()">
        <%@page import="java.io.*, java.text.SimpleDateFormat" %>
        <%
            String user = (String)session.getAttribute("usuario");
            String imagenperfil = (String)session.getAttribute("ImagenPerfil");
            
            if(user ==null)
            {
                response.sendRedirect("Inicio.html");
                return; //the return is important; forces redirect to go now
            }
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);           
            String conexion = (String) formatter.format(session.getAttribute("creacion"));
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat otroformato = new SimpleDateFormat("HH:mm");
                       
            String ultima = (String) formatter.format(session.getAttribute("last"));
            
            long longDuracion= session.getCreationTime(); 
            Date duracion=new Date(longDuracion);
            Date actual = new Date();
            
            String crop = ("Tiempo en la sesion: " + duracion.getMinutes() + "min." + duracion.getSeconds()+"seg");
            int diferenciaHoras=actual.getHours() - duracion.getHours();
            int diferenciaMinutos=actual.getMinutes() - duracion.getMinutes();
            //Muestra el resultado en el textfield
            String hora="Tiempo en la sesion: "+diferenciaHoras+" horas, "+diferenciaMinutos+" minutos.";
            
        %>
        <div class="MenuA">
            <div class="Logo">
                <img src="../Img/logo3.png" alt=""/>
            </div>
                <div class="Venus"style="float: left">
                Venus
                </div>
                <div class="bien">
                    <center><h1>¡Bienvenido: <%=user%>!<img style="height: 50px; width: 50px" src="../<%=imagenperfil%>"></h1></center>
                </div>
            <div class="Derecha">
                <div class="Reloj" name="cron" id="cronometro">
                    <h2 class="letraReloj"><br><%=hora%><br>Ultima conexion: <%=conexion%></h2>
                </div>
                <ul class="ca-menu"> 
                    <li class="chico">
                        <a href="salir.jsp">
                            <span class="fa fa-sign-out" id="H"></span>
                            <div class="ca-content">
                                <h2 class="ca-main">Salir</h2>
                            </div>
                        </a>
                    </li>
                    <li class="grande">
                        <a href="">
                            <span class="fa fa-bell" id="M"></span>
                            <div class="ca-content">
                                <h2 class="ca-main">Notificaciones</h2>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <nav>
                <ul class="mcd-menu">
                    <li>
                        <a href="" class="active">
                            <i class="fa fa-home"></i>
                            <strong>Home</strong>
                            <small>sweet home</small>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-female"></i>
                            <strong>Armario</strong>
                            <small>Tu tesoro personal</small>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-calendar"></i>
                            <strong>Calendario</strong>
                            <small>Agenda tu día a día</small>
                        </a>
                        <ul>
                            <li><a href="#"><i class="fa fa-refresh"></i>Ciclo</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-comments-o"></i>
                            <strong>Chats</strong>
                            <small>Siéntete libre de hablar</small>
                        </a>
                        <ul>
                            <li><a href="#"><i class="fa fa-unlock-alt"></i>Públicos</a></li>
                            <li><a href="#"><i class="fa fa-lock"></i>Privados</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="">
                            <i class="fa fa-picture-o"></i>
                            <strong>Tutoriales</strong>
                            <small>Aprende algo nuevo</small>
                        </a>
                    </li>
                    <li>
                        <a href="../Perfil/Perfil-Seguridad.html">
                            <i class="fa fa-user"></i>
                            <strong>Perfil</strong>
                            <small>Tu privacidad</small>
                        </a>
                        <ul>
                            <li><a href="#"><i class="fa fa-tags"></i>Mis outfits</a></li>
                            <li><a href="#"><i class="fa fa-heart"></i>Intereses</a></li>
                            <li><a href="#"><i class="fa fa-pencil"></i>Mis peticiones</a></li>
                            <li><a href="#"><i class="fa fa-group"></i>Contactos</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="TimeLine">
            <p class="DancingScript">Publicaciones</p>
            <textarea> </textarea><br>
            <button type="button" class="btn btnR btnDerecha">Publicar</button>
        </div>
        <div class="Intercambiable">
            <p class="DancingScript">Intercambiable</p>
            <div class="todoInt">
                <div class="User">
                    <img src="../Img/user.png" alt=""/>
                </div>
                <div class="Img">
                    <img src="../Img/playera.JPG" alt=""/>
                </div>
            </div>
        </div>
    </body>
</html>
