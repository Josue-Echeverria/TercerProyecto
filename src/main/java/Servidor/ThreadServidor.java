/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.Mensaje;
import Modelos.TipoMensaje;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jecheverria
 */
public class ThreadServidor extends Thread{
    public Socket socket;
    private Servidor server;
    private ObjectInputStream entrada;
    private DataInputStream entradaDatos;
     ObjectOutputStream salida;
     String nombre;
    
    private boolean isRunning = true;

    public ThreadServidor(Socket socket, Servidor server) {
        this.socket = socket;
        this.server = server;
        try {
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            
        }
    }

    @Override
    public void run() {
        Mensaje mensaje;
        try {
            nombre = entradaDatos.readUTF(); // lee el nombre
        } catch (IOException ex) {
            
        }
        server.pantalla.write("Recibido nombre: " + nombre);
        
        while (isRunning){
        try {
            
            
            try {
                mensaje = (Mensaje) entrada.readObject();
                server.pantalla.write("Recibido: " + mensaje.toString());
                
                if(mensaje.getTipo() == TipoMensaje.PUBLICO)
                    server.broadcoast(mensaje);
                else
                    server.privateMessage(mensaje);
                
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            
        } catch (IOException ex) {}
        
        }
        
        
    }
    
    
    
    
}
