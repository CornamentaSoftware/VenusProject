<%-- 
    Document   : alta
    Created on : 29-oct-2017, 13:42:02
    Author     : rodri
--%>

<%@page import="Encriptacion.AES"%>
<%@page import="java.util.Set"%>
<%@page import="servlets.LoginServlet"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.net.UnknownHostException"%>
<%@page import="java.net.InetAddress"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page import="java.sql.*,java.io.*" %>
        <%
            String nombre = "";
            String apellidos = "";
            String nombreCompleto = "";
            String correo = "";
            String username = "";
            String contra = "";
            
            String ip="";
            
            Connection con = null;
            Statement sta = null;
            ResultSet result;
            
                String rutalocal=getServletContext().getRealPath("/");
                String rutacarp=rutalocal+"Uploads";
                String nruta=rutacarp.replace("\\","/");
                String ruta=nruta.replaceAll("/build",""); 
                String objeto = "";
                
                File destino = new File(ruta);
               
		ServletRequestContext src = new ServletRequestContext(request);
 
		if(ServletFileUpload.isMultipartContent(src)){
			DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
			ServletFileUpload upload = new  ServletFileUpload(factory);
 
			java.util.List lista = upload.parseRequest(src);
			File file = null;
			java.util.Iterator it = lista.iterator();
 
			while(it.hasNext()){
				FileItem item=(FileItem)it.next();
				if(item.isFormField()){                                       
                                        if (item.getFieldName().equals("nombre"))
                                            nombre = item.getString();
                                        if (item.getFieldName().equals("apellido"))
                                            apellidos = item.getString();
                                        if (item.getFieldName().equals("correo"))
                                            correo = item.getString();
                                        if (item.getFieldName().equals("username"))
                                            username = item.getString();
                                        if (item.getFieldName().equals("contrasenia"))
                                            contra = item.getString();
                                }
				else
				{
					file=new File(item.getName());
					item.write(new File(destino,file.getName()));
                                        objeto = ("Uploads/" + item.getName());
					out.println("Fichero subido");
				} // end if
			} // end while
                }

                InetAddress address;
                try {
                    address = InetAddress.getLocalHost();
                    ip=(address.getHostAddress());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost/VENUS", "root", "n0m3l0");
                sta = con.createStatement();
            } catch (SQLException error) {
                out.print(error.toString());
            }

            try {
                result = sta.executeQuery("select * from usuario where Username_Usuario = '" + username + "';");
                if(result.first()){
                    out.println("<script>alert('Ya existe este username dentro del sistema');"
                            + "window.location.href = 'http://localhost:8084/VenusProject/Plantillas/Registrarse.html';</script>");
                }
                else{
                    AES cifrar = new AES();
                    String contracifrada = cifrar.Encriptar(contra, username);
                    
                    sta.executeUpdate("insert into Usuario(Nombre_Usuario, Apellido_Usuario, Correo_Usuario, Username_Usuario, "
                            + "Contrasenia_Usuario, Imagen_Usuario, IP_Usuario)"
                            + "values('" + nombre + "','" + apellidos + "','" + correo + "', '" + username + "','" + contracifrada + "','" + objeto +"','" + ip + "');");
                    con.close();
                    session.setAttribute("usuario", username);
                    session.setAttribute("contrasenia", contra);
                    session.setAttribute("ImagenPerfil", objeto);
                    out.println("<script>alert('Registrado con éxito');window.location.href = 'http://localhost:8084/VenusProject/Plantillas/redireccionar.jsp';</script>");
                }
            } catch (SQLException error) {     
                out.println("<script>alert('Ha ocurrido un error con tu alta');window.location.href = 'http://localhost:8084/VenusProject/Plantillas/Registrarse.html';</script>");
            }
            con.close();
        %>
    </body>
</html>
