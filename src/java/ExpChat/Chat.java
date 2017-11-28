/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpChat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Chat {
    
    public void enviar(String ipreceptor, String mensaje){
        try  {
            InetAddress maquinaReceptora = InetAddress.getByName(ipreceptor);  //1. ip
            int puertoReceptor = 3605;  //2. puerto 
            int miPuerto = 3605;  //3. mi puerto
            Scanner leer = new Scanner(System.in);
            EnviayRecibe miSocket = new EnviayRecibe(miPuerto);

            do{   
                miSocket.enviaMensaje(maquinaReceptora, puertoReceptor, mensaje);
            }while(mensaje != "adios");
                miSocket.close();
            }
        catch (Exception ex){
                ex.printStackTrace();
        }
    }
    
    public String recibir() throws SocketException, IOException{
        int miPuerto = 3605;
        EnviayRecibe miSocket = new EnviayRecibe(miPuerto);
        String mensaje = miSocket.recibeMensaje();
        
        return mensaje;
    }
}
