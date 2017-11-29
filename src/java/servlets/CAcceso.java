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
import util.Socket;
/**
 *
 * @author Alumno
 */
public class CAcceso extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usu = request.getParameter("user");
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        String mensaje = ip + " " + request.getParameter("Servicio") + " " + usu;
        //request.getParameter("Servicio") trae el nombre del servicio al que quiere acceder el usuario
        // Aquí se obtienen los datos del usuario que se enviarán al servidor de autenticacion
        try{
        mensaje(mensaje);
        }
        catch (Exception e){
        }   
    }
    
    private void mensaje(String mensaje) throws Exception{
        InetAddress ipAS= InetAddress.getByName("192.168.9.255");
        //Dentro de las comillas va la IP del servidor de autenticacion que debe tener una máquina definida,
        //pero por ahora no tiene :p
        int puertoAS= 5000;
        int miPuerto= 4000;
        Socket socket = new Socket(miPuerto);
        socket.envia(ipAS, puertoAS, mensaje);
        //Envía un mensaje al servidor de autenticacion (AS) con la ip,
        //el servicio al que quiere acceder y su username, todo separado con espacios
    }

}
