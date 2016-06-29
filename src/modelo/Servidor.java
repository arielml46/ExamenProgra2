/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.DataInputStream;
import java.io.IOException;
import static java.lang.System.in;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Ariel
 */
public class Servidor {

    private final int port= 2030;
    private final int noConexion= 20;
    
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
    private DataInputStream in;
    private String chat, clave;
    private String[] lista;
    private int i;
    private ArrayList arreglo;
    private Map<String, arreglo> nombreMap;
    private Map<String, Socket> sockets;
    public void listen()
    {
        try{
        ServerSocket servidor = new ServerSocket(port, noConexion);
        nombreMap = new HashMap<String, arreglo>();
        sockets = new HashMap<String, Socket>();
        while(true){
                System.out.println("Escuchando...");
                Socket cliente = servidor.accept();
                in = new DataInputStream(cliente.getInputStream());
                chat=in.readUTF();
                lista=chat.split(",");
                clave=lista[1];
                nombreMap.put(clave, arreglo.add(lista[0]));
                sockets.put(lista[0], cliente);
                for(i=2;i<lista.length;i++)
                {
                    nombreMap.put(clave, arreglo.add(lista[i]));
                }
                        
                Runnable  run = new HiloServidor(cliente,nombreMap, sockets);
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
