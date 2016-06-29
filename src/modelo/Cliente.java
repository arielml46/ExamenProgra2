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
import javax.swing.JEditorPane;

/**
 *
 * @author Ariel
 */
public class Cliente implements Runnable{

    private ArrayList lista;
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private int port= 2030;
    private String host;
    private String mensajes = "";
    private String chat;
    JEditorPane panel;
    
    public Cliente(JEditorPane panel, String miChat){
        this.panel = panel;
        chat=miChat;
        try{
            
            cliente = new Socket(host,port);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        }   catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void run() {
        try{
            enviarMsj(chat);
             while(true){
                lista=new ArrayList<String>();
                mensajes += in.readUTF();
                if(cliente.isConnected())
                {
                    for(int i=0;i<lista.size();i++)
                    {
                        panel.setText("\n"+(String) lista.get(i)+"\n");
                    }
                    panel.setText(mensajes);
                    lista=null;
                }
            }
        }catch(Exception e){
            lista.add(mensajes);
        }
    }
        
        
    
    
    public void enviarMsj(String msg){
        try {
            out.writeUTF(msg);

        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }
    
    
}
