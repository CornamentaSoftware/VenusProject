/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.*;
/**
 *
 * @author Alumno
 */
public class CAcceso extends HttpServlet{


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usu = request.getParameter("user");
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        
        // Aquí se obtienen los datos del usuario que se enviarán al servidor de autenticacion
        try{
        mensaje();
        }
        catch (Exception e){}
        
        
    }
    
    private void mensaje() throws Exception{
        InetAddress ipAS= InetAddress.getByName("");
        //Dentro de las comillas va la IP del servidor de autenticacion que debe tener una máquina definida,
        //pero por ahora no tiene :p
        int puertoAS= 5000;
        int miPuerto= 4000;
        
    }

}
