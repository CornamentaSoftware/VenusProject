/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        Connection conexion = conect.getConexion();
        HttpSession session = request.getSession();
        
        try {
            stm = (Statement) conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Alta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try{
                result = stm.executeQuery("Select * from usuario where Username_Usuario = '" + username + "';");
            if(result.first()){
                response(resp, "<script>alert('Ya existe este username dentro del sistema');</script>");
            }
            else{
                stm.executeUpdate("insert into Usuario(Nombre_Usuario, Apellido_Usuario, Correo_Usuario, Username_Usuario, Contrasenia_Usuario)"
                        + "values('" + nombre + "','" + apellidos + "','" + correo + "', '" + username + "','" + contra + "');");
                conexion.close();
                session.setAttribute("usuario", username);
                session.setAttribute("contra", contra);
                response(resp, "<script>alert('Registrado con éxito');window.location.href = 'http://localhost:8080/Venus-master/Plantillas/redireccionar.jsp';</script>");
            }
                
            }catch(SQLException error)
            {
                response(resp, "<script>alert('Ha ocurrido un error con tu alta');"
                        + "window.location.href = 'http://localhost:8080/Venus-master/Plantillas/Registrarse.html';</script>");
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
}
