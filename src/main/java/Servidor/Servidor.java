/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.EnvioInformacion;
import Modelos.Mensaje;
import Modelos.Usuario;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    private final int PORT = 8084;
    ServerSocket server;
    public PantallaServidor pantalla;
    
    public  ArrayList<ThreadServidor> clientesAceptados;
    ServerConnectionsThread conexionsThread;
    public static ArrayList<Usuario> UsuarioRegistrados;
    public ProcesadorMensaje lector ;
    public LogicaServidor logica;
    
    public Servidor(PantallaServidor pantalla){
        this.pantalla = pantalla;
        connect();
        lector = new ProcesadorMensaje(this);
        logica = new LogicaServidor(this);
        clientesAceptados = new ArrayList<ThreadServidor>();
        UsuarioRegistrados  = new ArrayList<Usuario>();
        conexionsThread = new ServerConnectionsThread(this);
        conexionsThread.start();
        
    }
    
    public void connect(){
        try {
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void broadcoast(Mensaje mensaje){
        
        String procesado = lector.leeMensaje(mensaje.getMensaje());
        
        mensaje.setMensaje(procesado);
        mensaje.setEnvioInformacion(new EnvioInformacion(UsuarioRegistrados));
       
        for (ThreadServidor cliente : clientesAceptados) {
            try {
                    System.out.println("----");
                    System.out.println(cliente.nombre);
                    System.out.println(mensaje.getEnvioInformacion().UsuarioRegistrados.size());
                    System.out.println("----");
                
                    cliente.salida.writeObject(mensaje);
                
                
            } catch (IOException ex) {
            
            }
        }
        //this.pantalla.write("Enviado " + clientesAceptados.size() +" veces: " + mensaje);
         this.pantalla.write(procesado);
        
    }
    
    
    public void privateMessage(Mensaje mensaje){
        
        for (ThreadServidor cliente : clientesAceptados) {
            try {
                if(mensaje.getReceptor().equals(cliente.nombre)){
                    cliente.salida.writeObject(mensaje);
                    break;
                }
            } catch (IOException ex) {
            
            }
        }
        this.pantalla.write("Enviado " + clientesAceptados.size() +" veces: " + mensaje);
        
    }
    
    
}
