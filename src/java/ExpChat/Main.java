/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpChat;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.WindowConstants;

/**
 *
 * @author Alumno
 */
public class Main extends javax.swing.JFrame implements Runnable {
    
    Thread hilo1;

    Thread hilo2;

    Thread hilo3;
    
    private JButton iniciar;

    
    public Main(){
        initComponents();
        configurarventana();
    }
    
    private void configurarventana(){
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        iniciar =new JButton ("Iniciar");
        
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    correr();
               }
        });
        
        setLayout (new FlowLayout ());
        add(iniciar);
    }

    public void correr(){
        hilo1 = new Thread(this);
        hilo2 = new Thread(this);
        hilo3 = new Thread(this);

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
    
    @Override
    public void run() {
        Chat chats = new Chat();
        Scanner leer = new Scanner(System.in);
        String ip="";
        Thread ct = Thread.currentThread();
        String mensaje="";

        while (ct == hilo1) {
            
        mensaje=leer.next();   
        chats.enviar(ip, mensaje);
        
        }

        while (ct == hilo2) {

            try {
                mensaje=chats.recibir();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(mensaje);

        }

        while (ct == hilo3) {

            try {
                mensaje=chats.recibir();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(mensaje);

        }
    }
}
