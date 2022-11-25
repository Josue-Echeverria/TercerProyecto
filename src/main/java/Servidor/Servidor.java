/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.EnvioInformacion;
import Modelos.Mensaje;
import Modelos.Usuario;
import Personaje.Arma;
import Personaje.Personaje;
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
    public int contadorgiro = 0;
    public boolean rendisionMutua = false;
   
    
    
    
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
    public ArrayList<Usuario> actualiza(){
        ArrayList<Usuario> hola = new ArrayList<Usuario>();
        for (Usuario UsuarioRegistrado : this.envioInformacion.UsuarioRegistrados) {
                                //System.out.println("----");
                                //System.out.println(UsuarioRegistrado.nombre);
                                //System.out.println(UsuarioRegistrado.ataques);
                                Personaje nuevoarreglo[] = new Personaje[4];
                                for (int i = 0; i < 4; i++) {

                                    Personaje viejo = UsuarioRegistrado.Personajes[i];
                                    Arma nuevasArmas[] = new Arma[5];
                                    for (int j = 0; j < 5; j++) {
                                            nuevasArmas[j] = new Arma(viejo.getArmas()[j].getNombre());
                                            nuevasArmas[j].setDaño(viejo.getArmas()[j].getDaño());
                                            nuevasArmas[j].Disponible = viejo.getArmas()[j].Disponible;
                                    }
                                    viejo.setArmas(nuevasArmas);
                                    Personaje nuevo;
                                    nuevo = new Personaje(viejo.getNombre(),viejo.getTipo(),viejo.getArmas(),viejo.getApariencia(),viejo.getDireccion(),viejo.getPosTipo());
                                    nuevo.setVida(viejo.getVida());
                                    
                                    nuevoarreglo[i] = nuevo;

                                }
                                Usuario usuario3 = new Usuario(UsuarioRegistrado.nombre, nuevoarreglo, UsuarioRegistrado.victorias, UsuarioRegistrado.perdidas, UsuarioRegistrado.ataques, UsuarioRegistrado.exitosos, UsuarioRegistrado.fallidos, UsuarioRegistrado.rendiciones,UsuarioRegistrado.Jugando,UsuarioRegistrado.UltimoAtaqueRealizado,UsuarioRegistrado.UltimoAtaqueRecibido);

                                System.out.println("----");
                                hola.add(usuario3);

                            }
        return hola;
    }
    public int cambioTurno(){
        contadorTurno += 1;
        if(contadorgiro == envioInformacion.UsuarioRegistrados.size()){
            pantalla.write("No quedan jugadores");
            return 0;
        }
        if(contadorTurno == envioInformacion.UsuarioRegistrados.size()){
            contadorTurno = 0;
        }
        if(envioInformacion.UsuarioRegistrados.get(contadorTurno).Jugando == false){
            contadorgiro += 1;
            cambioTurno();
            
        }else{
            contadorgiro = 0;
           return 0;
        }
        return 0;
        
    }
    private int EljugadorExiste(String nombre){
        
        int i = 0;
        for (Usuario UsuarioRegistrado : envioInformacion.UsuarioRegistrados) {
            if(UsuarioRegistrado.nombre.equals(nombre))
                return i;
            i += 1;
        }
        return -1;
    }
    
    private String revisandoRendisiones(String mensaje){
        String result = "";
        System.out.println("************");
        System.out.println(mensaje);
        System.out.println("************");
        if(mensaje.toUpperCase().endsWith( "SI")){
            
            contadorgiro += 1;
            result = "Si";
            
            
        }else{
            contadorgiro = 0;
            pantalla.write("Fallo la rendision");
            rendisionMutua = false;
            result = "Fallo la rendision";
        }
        if(contadorgiro == envioInformacion.UsuarioRegistrados.size()-1){
            pantalla.write("Todos se rindieron");
            for (Usuario UsuarioRegistrado : envioInformacion.UsuarioRegistrados) {
                UsuarioRegistrado.Jugando = false;
            }
            result = "Todos se rindieron";
        }
        return result;
    }
    public int broadcoast(Mensaje mensaje){
        String procesado = "holaaa";
        cambiaturno = true;
        if(envioInformacion.UsuarioRegistrados.get(EljugadorExiste(mensaje.getEnviador())).Jugando == false){
            privateMessage(new Mensaje(mensaje.getEnviador(),"Ya no estas jugando",mensaje.getEnviador()));
            return 0;
                    
        }
        if(rendisionMutua){
            
            mensaje.setMensaje(revisandoRendisiones(mensaje.getMensaje()));
            if(cambiaturno){
                cambioTurno();
               
             
            }
        }else{
            
        
        if (!envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre.equals(mensaje.getEnviador())){
                String[]  arregloMensaje = mensaje.getMensaje().split("-") ;
                if("CHAT".equals(arregloMensaje[0].toUpperCase())){
                    mensaje.setMensaje(arregloMensaje[1]);
                    
                    mensaje.clearUsuariosEnviados();
                    for (Usuario usuario : actualiza()) {
                        mensaje.addUsuariosEnviados(usuario);
                    }
                    
                    
                    cambiaturno = false;
                    mensajeTodos(mensaje);
                    return 0; 
                    
                }else
                    
                if("CHAT PRIVADO".equals(arregloMensaje[0].toUpperCase())){
                    mensaje.clearUsuariosEnviados();
                    for (Usuario usuario : actualiza()) {
                        mensaje.addUsuariosEnviados(usuario);
                    }
                    mensaje.setReceptor(arregloMensaje[1]);
                    mensaje.setMensaje(arregloMensaje[2]);
                    privateMessage(mensaje);
                    
                    cambiaturno = false;
                    
                    return 0; 
                }else{
                    mensaje.setMensaje("Juega "+ envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre);
                    mensaje.clearUsuariosEnviados();
                    for (Usuario usuario : actualiza()) {
                        mensaje.addUsuariosEnviados(usuario);
                    }
                    privateMessage(new Mensaje(mensaje.getEnviador(),"Juega "+ envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre,mensaje.getEnviador()));
                    mensajeTodos(mensaje);
                    return 0; 
                }
                    
        }else{
            procesado = lector.leeMensaje(mensaje.getMensaje(),mensaje.getEnviador());
            if(cambiaturno){
                cambioTurno();
               
             
            }
            mensaje.setMensaje(procesado);
        }
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
                                Personaje nuevoarreglo[] = new Personaje[4];
                                for (int i = 0; i < 4; i++) {

                                    Personaje viejo = UsuarioRegistrado.Personajes[i];
                                    Arma nuevasArmas[] = new Arma[5];
                                    for (int j = 0; j < 5; j++) {
                                            nuevasArmas[j] = new Arma(viejo.getArmas()[j].getNombre());
                                            nuevasArmas[j].setDaño(viejo.getArmas()[j].getDaño());
                                            nuevasArmas[j].Disponible = viejo.getArmas()[j].Disponible;
                                    }
                                    viejo.setArmas(nuevasArmas);
                                    Personaje nuevo;
                                    nuevo = new Personaje(viejo.getNombre(),viejo.getTipo(),viejo.getArmas(),viejo.getApariencia(),viejo.getDireccion(),viejo.getPosTipo());
                                    nuevo.setVida(viejo.getVida());
                                    
                                    nuevoarreglo[i] = nuevo;

                                }
                                Usuario usuario3 = new Usuario(UsuarioRegistrado.nombre, nuevoarreglo, UsuarioRegistrado.victorias, UsuarioRegistrado.perdidas, UsuarioRegistrado.ataques, UsuarioRegistrado.exitosos, UsuarioRegistrado.fallidos, UsuarioRegistrado.rendiciones,UsuarioRegistrado.Jugando,UsuarioRegistrado.UltimoAtaqueRealizado,UsuarioRegistrado.UltimoAtaqueRecibido);

                                System.out.println("----");
                                mensaje.addUsuariosEnviados(usuario3);

                            }
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
                            
                            cliente.salida.writeObject(mensaje);
                        
                                
                                
                            /*else{
                                mensaje.setMensaje("Juega "+ envioInformacion.UsuarioRegistrados.get(contadorTurno).nombre);
                                cliente.salida.writeObject(mensaje);
                            }*/
                            



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
         return 0; 
        
    }
    public void mensajeTodos(Mensaje mensaje){
        for (ThreadServidor cliente : clientesAceptados) {

                
            try {
                cliente.salida.writeObject(mensaje);
            } catch (IOException ex) {
                
            }
                    
                
        }
        this.pantalla.write("Enviado " + clientesAceptados.size() +" veces: " + mensaje);
    }
    
    
    public void privateMessage(Mensaje mensaje){
        mensaje.UsuarioRegistrados = envioInformacion.UsuarioRegistrados;
        for (ThreadServidor cliente : clientesAceptados) {
            try {
                System.out.println("+++");
                System.out.println(mensaje.getReceptor());
                System.out.println(cliente.nombre);
                System.out.println("+++");
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
