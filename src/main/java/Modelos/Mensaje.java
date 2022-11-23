/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jecheverria
 */
public class Mensaje implements Serializable{
    private String enviador;
    private String mensaje;
    private TipoMensaje tipo;
    private String receptor;
    public ArrayList<Usuario> UsuarioRegistrados;
    private EnvioInformacion envioInformacion;
    private ArrayList<Usuario> UsuariosEnviados;

    public Mensaje(String enviador, String mensaje, String receptor) {
        this.enviador = enviador;
        this.mensaje = mensaje;
        this.receptor = receptor;
        this.tipo = TipoMensaje.PRIVADO;
        if (UsuariosEnviados == null)
            this.UsuariosEnviados =  new ArrayList<Usuario>();
        this.envioInformacion = new EnvioInformacion(new ArrayList<Usuario>());
    }
    
    public Mensaje(String enviador, String mensaje) {
        this.enviador = enviador;
        this.mensaje = mensaje;
        this.tipo = TipoMensaje.PUBLICO;
        if (UsuariosEnviados == null)
            this.UsuariosEnviados =  new ArrayList<Usuario>();
        this.envioInformacion = new EnvioInformacion(new ArrayList<Usuario>());
    }

    public Mensaje(String enviador, String mensaje, EnvioInformacion envioInformacion) {
        this.enviador = enviador;
        this.mensaje = mensaje;
        this.tipo = TipoMensaje.PUBLICO;
        if (UsuariosEnviados == null)
            this.UsuariosEnviados =  new ArrayList<Usuario>();
        this.envioInformacion = envioInformacion;
    }

    
    @Override
    public String toString() {
        return "Mensaje "+ tipo + " de " + enviador + ": \"" + mensaje + "=";
        //return "Mensaje "+ tipo + " de " + enviador + ": \"" + mensaje;
    }

    public String getEnviador() {
        return enviador;
    }

    public void setEnviador(String enviador) {
        this.enviador = enviador;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoMensaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoMensaje tipo) {
        this.tipo = tipo;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public EnvioInformacion getEnvioInformacion() {
        return envioInformacion;
    }

    public void setEnvioInformacion(EnvioInformacion envioInformacion) {
        this.envioInformacion = envioInformacion;
    }

    public ArrayList<Usuario> getUsuarioRegistrados() {
        return UsuarioRegistrados;
    }

    public void setUsuarioRegistrados(ArrayList<Usuario> UsuarioRegistrados) {
        this.UsuarioRegistrados = UsuarioRegistrados;
    }

    public ArrayList<Usuario> getUsuariosEnviados() {
        return UsuariosEnviados;
    }

    public void setUsuariosEnviados(ArrayList<Usuario> UsuariosEnviados) {
        this.UsuariosEnviados = UsuariosEnviados;
    }
    public void addUsuariosEnviados(Usuario Usuarionuevo) {
        this.UsuariosEnviados.add(Usuarionuevo);
    }
    

    public void clearUsuariosEnviados() {
        this.UsuariosEnviados.clear();
    }
    
    
    
    
    
    
    
    
    
    
}
