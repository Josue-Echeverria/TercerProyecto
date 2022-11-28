/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.Mensaje;
import Modelos.TipoMensaje;
import Modelos.Usuario;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTML;

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
        int estaregistrado= -1;
        try {
            estaregistrado= -1;
            System.out.println("holaaaaaaaaaa1");
            nombre = entradaDatos.readUTF(); // lee el nombre
            String Scores[] = server.leeScore().split("&");
            for (int i = 0; i < Scores.length; i++) {
                if(Scores[i].split("-")[0].equals(nombre)){
                    estaregistrado = i;
                }
            }
            if(estaregistrado == -1){
                server.agregaAlScore(nombre+"-3-0-0-0-0-0-0");
            }else{
                String[] arreglodatos = Scores[estaregistrado].split("-");
                server.registradoActualiza = nombre +"-"+ arreglodatos[1]+"-"+ arreglodatos[2]+"-"+ arreglodatos[3]+"-"+ arreglodatos[4]+"-"+ arreglodatos[5]+"-"+ arreglodatos[6]+"-"+ arreglodatos[7];
            }
        } catch (IOException ex) {
            
        }
        server.pantalla.write("Recibido nombre: " + nombre);
        
        while (isRunning){
        try {
            
            
            try {
                
                    mensaje = (Mensaje) entrada.readObject();
                    boolean esta = false;
                    if (server.envioInformacion.UsuarioRegistrados != null){
                        for (Usuario UsuarioRegistrado : server.envioInformacion.UsuarioRegistrados) {
                        if(UsuarioRegistrado.nombre.equals(mensaje.getEnvioInformacion().usuario.nombre)){
                            esta = true;
                            break;
                        }
                    
                        }
                        if(!esta){
                            server.envioInformacion.UsuarioRegistrados.add(mensaje.getEnvioInformacion().usuario);
                        }
                    }
                    
                    mensaje.setEnvioInformacion(server.envioInformacion);
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
