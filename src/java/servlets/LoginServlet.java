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
                
                Comprobar comp = new Comprobar();
                
                boolean existe=comp.buscarUsuario(user, pass);
                
                HttpSession sesion = req.getSession(true);
                
		if (existe == true) {
			sesion.setAttribute("usuario", user);
                        Date estaconexion = new Date(sesion.getCreationTime() );
                        sesion.setAttribute("creacion", estaconexion);
                        Date ultimaconexion = new Date(sesion.getLastAccessedTime());
                        sesion.setAttribute("last", ultimaconexion);
                        sesion.setMaxInactiveInterval(300); //600 secs = 10 mins

                        String ip=GetIP();
                        sesion.setAttribute("IP", ip);
                        
                        Date now = new Date();
                        DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
                        resp.sendRedirect("http://localhost:8080/Venus-master/Plantillas/Menu.jsp");
		} else {
			response(resp, "<script>alert('La sesion es inválida o no existe');window.location.href = 'http://localhost:8080/Venus-master/Plantillas/Ingresar.html';</script>");
                        
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
