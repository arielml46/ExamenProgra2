/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * @author Ariel
 */
public class Servidor {

    private final int port= 2030;
    private final int noConexion= 20;
    
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    
    public void listen()
    {
        try{
        ServerSocket servidor = new ServerSocket(port, noConexion);
        
        while(true){
                System.out.println("Escuchando...");
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
        
                Runnable  run = new HiloServidor(cliente,usuarios);
                Thread hilo = new Thread(run);
                hilo.start();
                }
        
    } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static void main(String[] args) {
        Servidor servidor= new Servidor();
        servidor.listen();
    
    }
      
}
