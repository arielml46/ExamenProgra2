/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 *
 * @author Ariel
 */
public class HiloServidor implements Runnable {

    
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private LinkedList<Socket> usuarios = new LinkedList<Socket>();   
        private ArrayList lista;
        private Map<String, lista> nombreMap;
        private String chat;

         public HiloServidor(Socket sock, LinkedList users){
             
             usuarios = users;
             socket= sock;
             lista=new ArrayList<>();
             nombreMap = new HashMap<String, lista>();
        }
         
        @Override
        public void run() {

            try{
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("<h2>Chat Iniciado</h2>");
                
                while(true){
               String capturado = in.readUTF();
               
               for (int i = 0; i < usuarios.size(); i++) {
                    out = new DataOutputStream(usuarios.get(i).getOutputStream());
                    out.writeUTF(capturado);
                    }
                }
                
            } catch (IOException ex) {
            }
        }
        
}