/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Ana
 */
@WebServlet(name = "Alta", urlPatterns = {"/Alta"})
public class Alta extends HttpServlet {

public void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        Conexion conect = new Conexion();
        Statement stm = null;
        resp.setContentType("text/html;charset=UTF-8");
        ResultSet result;
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellido");
        String nombreCompleto = (nombre + " "  + apellidos);
        String correo = request.getParameter("correo");
        String username = request.getParameter("username");
        String contra = request.getParameter("contrasenia");
        String ip=GetIP();
        
        Connection conexion = conect.getConexion();
        HttpSession session = request.getSession();
        
        try {
            stm = (Statement) conexion.createStatement();
        } catch (SQLException ex) {
            ex.toString();
        }
        
                String rutalocal=getServletContext().getRealPath("/");
                String rutacarp=rutalocal+"Uploads";
                String nruta=rutacarp.replace("\\","/");
                String ruta=nruta.replaceAll("/build",""); 
                String objeto = "";
                
                File destino = new File(ruta);
                
		ServletRequestContext src = new ServletRequestContext(request);
 
                try{
                    if(ServletFileUpload.isMultipartContent(src)){
                            DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
                            ServletFileUpload upload = new  ServletFileUpload(factory);

                            java.util.List lista = upload.parseRequest(src);
                            File file = null;
                            java.util.Iterator it = lista.iterator();

                            while(it.hasNext()){
                                    FileItem item=(FileItem)it.next();
                                    if(item.isFormField())
                                            response(resp, item.getFieldName()+"<br>");
                                    else
                                    {
                                        try {
                                            file=new File(item.getName());
                                            objeto = (ruta + "/" + item.getName());
                                            response(resp, "Fichero subido");
                                            item.write(new File(destino,file.getName()));
                                        } catch (Exception ex) {
                                            response(resp, "<script>alert('Ha ocurrido un error con tu alta');"
                       + "window.location.href = 'http://localhost:8080/VenusProject/Plantillas/Ingresar.html';</script>");
                                        }
                                            
                                    } // end if
                            } // end while
                    }
                }catch(FileUploadException ex){
                            Logger.getLogger(Alta.class.getName()).log(Level.SEVERE, null, ex);
                            response(resp, "<script>alert('Ha ocurrido un error con tu alta');"
                       + "window.location.href = 'http://localhost:8080/VenusProject/Plantillas/Ingresar.html';</script>");
                        }
        
            try{
                result = stm.executeQuery("Select * from usuario where Username_Usuario = '" + username + "';");
            if(result.first()){
                response(resp, "<script>alert('Ya existe este username dentro del sistema');</script>");
            }
  
            }catch(SQLException error)
            {
                try{
                stm.executeUpdate("insert into Usuario(Nombre_Usuario, Apellido_Usuario, Correo_Usuario, Username_Usuario, Contrasenia_Usuario, Imagen_Usuario, IP_Usuario)"
                        + "values('" + nombre + "','" + apellidos + "','" + correo + "', '" + username + "','" + contra + "', '" + objeto +"', '" + ip +"');");
                conexion.close();
                session.setAttribute("usuario", username);
                session.setAttribute("contra", contra);
                response(resp, "<script>alert('Registrado con éxito');window.location.href = 'http://localhost:8080/VenusProject/Plantillas/redireccionar.jsp';</script>");
                }catch(SQLException ex){
                    response(resp, "<script>alert('Ha ocurrido un error con tu alta');"
                       + "window.location.href = 'http://localhost:8080/VenusProject/Plantillas/Ingresar.html';</script>");
                }
            }   
    }

        private void response(HttpServletResponse resp, String msg)
			throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<t1>" + msg + "</t1>");
		out.println("</body>");
		out.println("</html>");
	}
        
        public String GetIP() {

        String ip="";
            
        InetAddress address;
            try {
                address = InetAddress.getLocalHost();
                ip=("IP Local :"+address.getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        return ip;
        }
}
