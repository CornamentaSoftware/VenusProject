/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.*;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String usu = request.getParameter("user");
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        String mensaje = ip + " " + request.getParameter("Servicio") + " " + usu;
        //request.getParameter("Servicio") trae el nombre del servicio al que quiere acceder el usuario
        // Aquí se obtienen los datos del usuario que se enviarán al servidor de autenticacion
        String mensaje2 = ip + " " + request.getParameter("Servicio");
        String respuesta = "";
        try{
            //Cambiar las address dependiendo de la máquina donde esté cada servidor
        //Mensaje al servidor de autenticación
        mensaje(mensaje, "192.168.9.255",5000);
        //Mensaje al servidor de tickets
        mensaje(mensaje2, "192.168.9.255",3000);
        respuesta = respuesta();
        }
        catch (Exception e){
        }   
        
        if(respuesta.equals("Error")){
            //Enivarle un alert que diga "Ha ocurrido un error, por favor vuelve a iniciar sesion"
            //Direccionarlo al inicio de sesion y cerrar la sesion
        }
        else{
            String servicio = "";
            for(int i=0; i<respuesta.length(); i++){
                if(respuesta.charAt(i)==' '){
                    servicio = respuesta.substring(i+1);
                }
            }
            switch(servicio){
                case "CambiarD": resp.sendRedirect("http://localhost:8080/VenusProject/Plantillas/CambiarD.html");
                //Envía al módulo de cambiar datos
                    break;
            }
        }
        
    }
    
    private void mensaje(String mensaje, String address, int puerto) throws Exception{
        
    }
    
    private String respuesta() throws Exception{
        String respuesta="";
        return respuesta;
    }

}
