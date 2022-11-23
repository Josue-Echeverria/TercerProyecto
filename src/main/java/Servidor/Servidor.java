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
    public int contadorTurno = 0;
    public boolean cambiaturno = true;
    public  ArrayList<ThreadServidor> clientesAceptados;
    ServerConnectionsThread conexionsThread;
    public ArrayList<Usuario> UsuarioRegistrados;
    public ProcesadorMensaje lector ;
    public EnvioInformacion envioInformacion;
   
    
    
    
    public Servidor(PantallaServidor pantalla){
        this.pantalla = pantalla;
        connect();
        UsuarioRegistrados  = new ArrayList<Usuario>();
        envioInformacion = new EnvioInformacion(UsuarioRegistrados);
        lector = new ProcesadorMensaje(this);
        
        clientesAceptados = new ArrayList<ThreadServidor>();
        
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
        String procesado = "holaaa";
        cambiaturno = true;
        if (!envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre.equals(mensaje.getEnviador())){
                mensaje.setMensaje("Juega "+ envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre);
        }else{
            procesado = lector.leeMensaje(mensaje.getMensaje(),mensaje.getEnviador());
            if(cambiaturno){
               contadorTurno += 1;
                if(contadorTurno == envioInformacion.UsuarioRegistrados.size()){
                    contadorTurno = 0;
                }
             
            }
            mensaje.setMensaje(procesado);
        }

        //procesado += "/"+Integer.toString(envioInformacion.UsuarioRegistrados.size());

        //mensaje.setEnvioInformacion(envioInformacion);
       
                for (ThreadServidor cliente : clientesAceptados) {
                    try {
                            //System.out.println("******");
                            mensaje.clearUsuariosEnviados();
                            for (Usuario UsuarioRegistrado : this.envioInformacion.UsuarioRegistrados) {
                                //System.out.println("----");
                                //System.out.println(UsuarioRegistrado.nombre);
                                //System.out.println(UsuarioRegistrado.ataques);
                                Usuario usuario3 = new Usuario(UsuarioRegistrado.nombre, UsuarioRegistrado.Personajes, UsuarioRegistrado.victorias, UsuarioRegistrado.perdidas, UsuarioRegistrado.ataques, UsuarioRegistrado.exitosos, UsuarioRegistrado.fallidos, UsuarioRegistrado.rendiciones);

                                System.out.println("----");
                                mensaje.addUsuariosEnviados(usuario3);

                            }
                            
            
                            //System.out.println("******");
                            if(mensaje.getEnviador().equals(cliente.nombre)){
                                cliente.salida.writeObject(mensaje);
                                
                                
                            }else{
                                mensaje.setMensaje("Juega "+ envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre);
                                cliente.salida.writeObject(mensaje);
                            }
                            



                    } catch (IOException ex) {

                    /*}
                }
            }else{
                for (ThreadServidor cliente : clientesAceptados) {
                    try {
                        if(mensaje.getEnviador().equals(cliente.nombre)){
                            cliente.salida.writeObject(new Mensaje(cliente.nombre, "No es tu turno"));
                            this.pantalla.write("No es tu turno");
                            break;
                        }
                    } catch (IOException ex) {

                    }
                }*/
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
