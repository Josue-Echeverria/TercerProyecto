/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

/**
 *
 * @author PC
 */
public class ProcesadorMensaje {
    Servidor server;

    public ProcesadorMensaje(Servidor server) {
        this.server = server;
    }
    
    
    
    public String leeMensaje(String mensaje){
        
        mensaje = mensaje.toUpperCase();
        System.out.println(mensaje);
        String[] arregloMensaje = mensaje.split("-");
        switch (arregloMensaje[0]) {
            case "ATACAR":
                return atacando(arregloMensaje);
            case "RENDIRSE":
                return "Rendirse";
            case "PASAR":
                return "Pasar";
            case "SALIDA MUTUA":
                return  "Salida Mutua";

            case "RECARGA DE ARMAS":
                return  "Recarga de Armas";
 
            case "USAR COMODIN":
                return  "Usar comodin";
  
            case "SELECCIONAR JUGADOR":
                return  "Seleccionar jugador";

            case "CHAT PRIVADO":
                return  "Chat privado";
                    
            case "CHAT":
                return "Chat";
                
            case "INFORMACION GENERAL":
                return "Informacion general";
            case "INFORMACION USUARIO":
                return server.logica.informacionUsuario(arregloMensaje[1]);
            default:
                return "Error";
        } 
    }

    private static String atacando(String[] arregloMensaje) {
        
        return "Error";
    }

}
