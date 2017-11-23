/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encriptacion;

import java.util.Scanner;

/**
 *
 * @author rodri
 */
public class Prueba {
    
    public static void main (String args[]) throws Exception{
    Scanner leer = new Scanner(System.in);
    
    String cosa=leer.next();
    
    AES cifrar = new AES();

    String cosacifrada = cifrar.Encriptar(cosa);
        System.out.println(cosacifrada);
        
    String cosadescifrada = cifrar.Desencriptar(cosacifrada);
        System.out.println(cosadescifrada);
    }
}
