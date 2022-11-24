/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Modelos.EnvioInformacion;
import Modelos.Mensaje;
import Modelos.Usuario;
import Servidor.Servidor;
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
public class ThreadCliente extends Thread{
    boolean isrunnig = true;
    private Socket socket;
    private Cliente cliente;
    private ObjectInputStream entrada;

    public ThreadCliente(Socket socket, Cliente cliente) {
        try {
            this.socket = socket;
            this.cliente = cliente;
            entrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            //Logger.getLogger(ThreadCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*System.out.println(mensaje.getMensaje());
                if("Informacion general".equals(mensaje.getMensaje().split("-")[0])){
                    cliente.pantalla.writeDatosEnemigos(mensaje.getMensaje().split("-")[1]); 
                }else if("Informacion Usuario".equals(mensaje.getMensaje().split("-")[0])){
                    System.out.println("holaaaaaaaaaaaaaaaaaaaa");
                    cliente.pantalla.writeMisDatos(mensaje.getMensaje().split("-")[1]); 
                }else{*/
    public void run(){
        
        EnvioInformacion hola;
        while(isrunnig){
            
            
            
          
                
            
                
                //System.out.println("tomeee");
           
                
           
            try {
                Mensaje mensaje;
                mensaje = (Mensaje) entrada.readObject();
                
                for (Usuario UsuarioRegistrado : mensaje.getUsuariosEnviados()) {
                        System.out.println("----");
                        System.out.println(UsuarioRegistrado.nombre);
                        for (int i = 0; i < 4; i++) {
                            System.out.println("***");
                            System.out.println(UsuarioRegistrado.Personajes[i].getNombre());
                            for (int j = 0; j < 5; j++) {
                                System.out.println(UsuarioRegistrado.Personajes[i].getArmas()[j].getNombre());
                                System.out.println(UsuarioRegistrado.Personajes[i].getArmas()[j].Disponible);
                                
                            }
                            System.out.println("***");
                        }

                        System.out.println("----");
                        
                    
                    }
                cliente.envioInformacion = mensaje.getEnvioInformacion();
                cliente.envioInformacion.UsuarioRegistrados = mensaje.getUsuariosEnviados();
               
                cliente.pantalla.write(mensaje.toString());
                cliente.pantalla.ActualizaPantalla();
                
                
            } catch (IOException ex) {
                
                        
            } catch (ClassNotFoundException ex) {
                
                        
            }

        }
        
    }
    
    
    
    
    
}
