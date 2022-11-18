/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import Modelos.Usuario;

/**
 *
 * @author PC
 */
public class LogicaServidor {
    Servidor server;

    public LogicaServidor(Servidor server) {
        this.server = server;
    }
    
    public String informacionGneral(){
        String informacion = "Informacion general-";
        for (Usuario UsuarioRegistrado : server.UsuarioRegistrados) {
            informacion += "Contricante #:";
            informacion += UsuarioRegistrado.nombre;
            informacion += "\n";
            informacion += "Victorias:";
            informacion += Integer.toString(UsuarioRegistrado.victorias);
            informacion += "\n";
            informacion += "Perdidas:";
            informacion += Integer.toString(UsuarioRegistrado.perdidas);
            informacion += "\n";
            informacion += "Ataques:";
            informacion += Integer.toString(UsuarioRegistrado.ataques);
            informacion += "\n";
            informacion += "Exitosos:";
            informacion += Integer.toString(UsuarioRegistrado.exitosos);
            informacion += "\n";
            informacion += "Fallidos:";
            informacion += Integer.toString(UsuarioRegistrado.fallidos);
            informacion += "\n";
            informacion += "Rendiciones:";
            informacion += Integer.toString(UsuarioRegistrado.rendiciones);
            informacion += "\n";
            
            
        }
        
        return informacion;
    }

    String informacionUsuario(String nombre) {
       String informacion = "Informacion Usuario-";
        for (Usuario UsuarioRegistrado : server.UsuarioRegistrados) {
            
            if(nombre.equals(UsuarioRegistrado.nombre.toUpperCase())){
                
                
                informacion += "Victorias:";
                informacion += Integer.toString(UsuarioRegistrado.victorias);
                informacion += "\n";
                informacion += "Perdidas:";
                informacion += Integer.toString(UsuarioRegistrado.perdidas);
                informacion += "\n";
                informacion += "Ataques:";
                informacion += Integer.toString(UsuarioRegistrado.ataques);
                informacion += "\n";
                informacion += "Exitosos:";
                informacion += Integer.toString(UsuarioRegistrado.exitosos);
                informacion += "\n";
                informacion += "Fallidos:";
                informacion += Integer.toString(UsuarioRegistrado.fallidos);
                informacion += "\n";
                informacion += "Rendiciones:";
                informacion += Integer.toString(UsuarioRegistrado.rendiciones);
                informacion += "\n";
                break;
            }
            
            
            
        }
        
        return informacion;
        
       }
}
