/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpChat;

import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class Main implements Runnable{
    
    Thread hilo1;

    Thread hilo2;

    Thread hilo3;
    
    Chat chats = new Chat();
    Scanner leer = new Scanner(System.in);
    String ip="";
    
    public void main(String args[]){
        hilo1 = new Thread(this);
        hilo2 = new Thread(this);
        hilo3 = new Thread(this);

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();
        String mensaje="";

        while (ct == hilo1) {
            
        mensaje=leer.next();   
        chats.enviar(ip, mensaje);
        
        }

        while (ct == hilo2) {

        mensaje=chats.recibir();
        System.out.println(mensaje);

        }

        while (ct == hilo3) {

        mensaje=chats.recibir();
        System.out.println(mensaje);

        }
    }
}
