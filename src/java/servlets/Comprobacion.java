/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
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
 * @author rodri
 */
@WebServlet(name = "Comprobacion", urlPatterns = {"/Comprobacion"})
public class Comprobacion extends HttpServlet {

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
            
                HttpSession sesion = req.getSession(true);
                
		String correo = req.getParameter("correo");
		String pass = req.getParameter("contra");
                String user = (String) sesion.getAttribute("usuario");
                String contra = (String) sesion.getAttribute("contrasenia");
                String ip = (String) sesion.getAttribute("IP");
      
                String correobase =buscarCorreo(user, contra);
                String contrabase = buscarContra(user, contra);
               
		if (correobase.equals(correo) && contrabase.equals(pass)) {
			actualizarip(ip, resp);
                        sesion.setAttribute("IP", ip);
                        
                        response(resp, "<script>alert('Tu comprobación es correcta');"
                                    + "window.location.href = 'http://localhost:8084/VenusProject/Plantillas/redireccionar.jsp';</script>");
		} else {
			response(resp, "<script>alert('La comprobación es incorrecta, intenta más tarde');window.location.href = 'http://localhost:8084/VenusProject/Plantillas/Inicio.html';</script>");   
		}
	}
        
        public String buscarCorreo(String user, String contra){
            String correo="";
                try{
                    Conexion c = new Conexion();
                    Connection con = c.getConexion();
                    
                    if (con!=null){
                        String sql = "SELECT * FROM usuario WHERE"
                                + " Username_Usuario='"+user+"' && "
                                + "Contrasenia_Usuario='"+contra+"';";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery(); 
                        
                        if (rs.next()){
                            correo = rs.getString("Correo_Usuario");
                        }
                        c.cerrarConexion();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        return correo;
    }

        public String buscarContra(String user, String contra){
            String contrasenia="";
                try{
                    Conexion c = new Conexion();
                    Connection con = c.getConexion();
                    
                    if (con!=null){
                        String sql = "SELECT * FROM usuario WHERE"
                                + " Username_Usuario='"+user+"' && "
                                + "Contrasenia_Usuario='"+contra+"';";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery(); 
                        
                        if (rs.next()){
                            contrasenia = rs.getString("Contrasenia_Usuario");
                        }
                        c.cerrarConexion();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        return contrasenia;
    }
        
        public void actualizarip(String ip, HttpServletResponse resp)
            throws ServletException, IOException {
            try{
                Conexion conect = new Conexion();
                Connection conexion = conect.getConexion();
                Statement stm = null;
                stm = (Statement) conexion.createStatement();
                
                stm.executeUpdate("insert into Usuario(IP_Usuario)"
                        + "values('"+ip+"');");
                conexion.close();
                }catch(SQLException ex){
                    response(resp, "<script>alert('Ha ocurrido un error con la actualizacion de tu nuevo equipo');"
                       + "window.location.href = 'http://localhost:8080/VenusProject/Plantillas/Ingresar.html';</script>");
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

