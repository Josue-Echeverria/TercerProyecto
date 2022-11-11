/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author jecheverria
 */
public class ServerConnectionsThread extends Thread{
    private boolean isRunning = true;
    Servidor server;

    public ServerConnectionsThread(Servidor server) {
        this.server = server;
    }
    
    public void run(){
        while (isRunning) {            
            try {
                server.pantalla.write("Esperando cliente ... ");
                
                Socket socket = server.server.accept();
                ThreadServidor ts = new ThreadServidor(socket, server);
                ts.start();
                server.clientesAceptados.add(ts);
                
                server.pantalla.write("Cliente conectado");
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }   
}
