/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("contra");
                
                Base comp = new Base();
                
                boolean existe=comp.buscarUsuario(user, pass);
                
                HttpSession sesion = req.getSession(true);
                
		if (existe == true) {
			sesion.setAttribute("usuario", user);
                        sesion.setAttribute("contrasenia", pass);
                        sesion.setAttribute("IdUser", comp.getIdUser());
                        Date estaconexion = new Date(sesion.getCreationTime() );
                        sesion.setAttribute("creacion", estaconexion);
                        Date ultimaconexion = new Date(sesion.getLastAccessedTime());
                        sesion.setAttribute("last", ultimaconexion);
                        sesion.setMaxInactiveInterval(300); //600 secs = 10 mins

                        String ipusuario=GetIP();
                        String ipbase=buscarIP(user, pass);
                           
                        Date now = new Date();
                        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
                        
                        if (!ipusuario.equals(ipbase)) {
                            resp.sendRedirect("http://localhost:8084/VenusProject/Plantillas/Menu.jsp");
                        } else {
                            response(resp, "<script>alert('Se ha iniciado sesión por última vez en otro equipo, es necesario validar la sesión');"
                                    + "window.location.href = 'http://localhost:8084/VenusProject/Plantillas/Comprobacion.jsp';</script>");
                        }
		} else {
			response(resp, "<script>alert('La sesion es inválida o no existe');window.location.href = 'http://localhost:8084/VenusProject/Plantillas/Ingresar.html';</script>");   
		}
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
        
        public String buscarIP(String user, String contra){
            String ip="";
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
                            ip = rs.getString("IP_Usuario");
                        }
                        c.cerrarConexion();
                    }
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
        return ip;
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
