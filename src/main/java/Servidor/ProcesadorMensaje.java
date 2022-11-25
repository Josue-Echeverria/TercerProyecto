/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.Usuario;
import Personaje.Arma;
import Personaje.Personaje;
import Personaje.Tipo;

/**
 *
 * @author PC
 */
public class ProcesadorMensaje {
    public Servidor server;
    public String[] arregloMensaje;
    public String enviador;

    public ProcesadorMensaje(Servidor server) {
        this.server = server;
    }
    
    
    
    public String leeMensaje(String mensaje,String enviador){
        this.enviador = enviador;
        
        System.out.println(mensaje);
        arregloMensaje = mensaje.split("-");
        switch (arregloMensaje[0].toUpperCase()) {
            case "ATACAR":
                return atacando(arregloMensaje);
            case "RENDIRSE":
                return Rendirse();
            case "PASAR":
                return "Paso";
            case "SALIDA MUTUA":
                server.rendisionMutua = true;
                return  "Salida Mutua";

            case "RECARGA DE ARMAS":
                return  RecargarArmas();
 
            case "USAR COMODIN":
                return  Comodin(arregloMensaje);
  
                

            case "CHAT PRIVADO":
                return  arregloMensaje[2];
                    
            case "CHAT":
                return arregloMensaje[1];
                
            
            default:
                server.cambiaturno = false;
                return "Error";
        } 
    }
    private int EljugadorExiste(String nombre){
        
        int i = 0;
        for (Usuario UsuarioRegistrado : server.envioInformacion.UsuarioRegistrados) {
            if(UsuarioRegistrado.nombre.equals(nombre))
                return i;
            i += 1;
        }
        return -1;
    }

    private String atacando(String[] arregloMensaje){
        int pos = EljugadorExiste(arregloMensaje[1]);
        int posEnviador = EljugadorExiste(this.enviador);
        int Sumatoria = 0;
        System.out.println("+++++++");
        System.out.println(arregloMensaje[1]);
        System.out.println(pos);
        System.out.println("+++++++");
        if(pos != -1){ 
            //server.envioInformacion.UsuarioRegistrados.get(pos).perdidas +=1;
            server.envioInformacion.UsuarioRegistrados.get(posEnviador).ataques +=1;
            Personaje atacante = null;
            for (int i = 0; i < 4; i++) {
                if(arregloMensaje[2].equals(server.envioInformacion.UsuarioRegistrados.get(posEnviador).Personajes[i].getNombre())){
                    atacante= server.envioInformacion.UsuarioRegistrados.get(posEnviador).Personajes[i];
                    break;
                }
            }
            if(atacante == null){
                server.cambiaturno = false;
                return "El personaje no existe";
            }
                
            Arma armaSeleccionada = null;
            int posarma = 0;
            for (int i = 0; i < 5; i++) {
                if(arregloMensaje[3].equals(atacante.getArmas()[i].getNombre())){
                    armaSeleccionada= atacante.getArmas()[i];
                    posarma = i;
                    break;
                }
            }
            if(armaSeleccionada == null){
                server.cambiaturno = false;
                return "El arma no existe";
            }
            if(armaSeleccionada.Disponible == false){
                server.cambiaturno = false;
                return "El arma no Disponible";
                }
            atacante.getArmas()[posarma] = new Arma(armaSeleccionada.getNombre());
            atacante.getArmas()[posarma].setDaño(armaSeleccionada.getDaño()); 
            atacante.getArmas()[posarma].Disponible = false;
            Arma nuevasArmas[] = new Arma[5];
            for (int j = 0; j < 5; j++) {
                    nuevasArmas[j] = new Arma(atacante.getArmas()[j].getNombre());
                    nuevasArmas[j].setDaño(atacante.getArmas()[j].getDaño());
                    nuevasArmas[j].Disponible = atacante.getArmas()[j].Disponible;
            }
            atacante.setArmas(nuevasArmas);
                
            Personaje nuevoarreglo[] = new Personaje[4];
            for (int i = 0; i < 4; i++) {
                
                Personaje viejo = server.envioInformacion.UsuarioRegistrados.get(pos).Personajes[i];
                
                Personaje nuevo;
                nuevo = new Personaje(viejo.getNombre(),viejo.getTipo(),viejo.getArmas(),viejo.getApariencia(),viejo.getDireccion(),viejo.getPosTipo());
                nuevo.setVida(viejo.getVida() - armaSeleccionada.getDaño()[viejo.getPosTipo()]);
                Sumatoria += armaSeleccionada.getDaño()[viejo.getPosTipo()];
                
                nuevoarreglo[i] = nuevo;
                
            }
            server.envioInformacion.UsuarioRegistrados.get(posEnviador).UltimoAtaqueRealizado = "Se Ataco a "+ server.envioInformacion.UsuarioRegistrados.get(pos).nombre+ "Con un ataque de "+Integer.toString(Sumatoria);
            server.envioInformacion.UsuarioRegistrados.get(pos).UltimoAtaqueRecibido = "Se recibio un ataque de "+ server.envioInformacion.UsuarioRegistrados.get(posEnviador).nombre+ "Con un ataque de "+Integer.toString(Sumatoria);
            server.envioInformacion.UsuarioRegistrados.get(pos).Personajes = nuevoarreglo;
            return "Se ataco a "+ arregloMensaje[1];
        }else{
            server.cambiaturno = false;
            return "No se ataco porque ese jugador no esta conectado";
        }
        
    }
    
    private String Comodin(String[] arregloMensaje){
        String ataqueuno = atacando(new String[]{arregloMensaje[0],arregloMensaje[1],arregloMensaje[2],arregloMensaje[3]});
        String ataquedos = atacando(new String[]{arregloMensaje[0],arregloMensaje[1],arregloMensaje[4],arregloMensaje[5]});
        System.out.println("ataquedos");
        System.out.println(ataquedos);
        if(ataqueuno.equals("El personaje no existe") || ataqueuno.equals("El arma no existe") || ataqueuno.equals("No se ataco porque ese jugador no esta conectado")){
            //server.cambiaturno = false;
            return "Error en el comodin no ataco el primer ataque";
        }else{
            if(ataquedos.equals("El personaje no existe") || ataquedos.equals("El arma no existe") || ataquedos.equals("No se ataco porque ese jugador no esta conectado")){
                //server.cambiaturno = false;
                return "Error en el comodin no ataco el segundo ataque";
            }else{
                //server.cambiaturno = false;
                return "Comodin exitoso";
        }
        }
    }

    private String RecargarArmas(){
        int posEnviador = EljugadorExiste(this.enviador);
        Boolean recargar = true;
        server.cambiaturno = false;
        for (Personaje personaje : server.envioInformacion.UsuarioRegistrados.get(posEnviador).Personajes) {
            for (int i = 0; i < 5; i++) {
                if(personaje.getArmas()[i].Disponible == true)
                    recargar = false;
            }
        }
        if(recargar){
            for (Personaje personaje : server.envioInformacion.UsuarioRegistrados.get(posEnviador).Personajes) {
                for (int i = 0; i < 5; i++) {
                    personaje.getArmas()[i].Disponible = true;
                       
                }
            }
            return "Se recargaron las armas";
        }
        return "Tienes armas disponibles";
    }
    
    private String Rendirse(){
        int posEnviador = EljugadorExiste(this.enviador);
        server.envioInformacion.UsuarioRegistrados.get(posEnviador).Jugando = false;
        server.envioInformacion.UsuarioRegistrados.get(posEnviador).rendiciones += 1;
        return "Me rindo";
    }
}
