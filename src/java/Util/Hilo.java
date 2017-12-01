/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Alumno
 */
public class Hilo implements Runnable{

    private String cuenta;
    private boolean running = true;

    public Hilo(String cuenta){
        this.cuenta = cuenta;
    }

    public void run(){
        try{
            while(running){
                System.out.println(cuenta);
            }
        }
        catch(Exception e){}
    }

    public void parar(){
        running = false;
    }

}
