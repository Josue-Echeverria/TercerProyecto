
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Personaje.Personaje;
import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Usuario implements Serializable{
    public String nombre ;
    public Personaje[] Personajes;
    public int victorias;
    public int perdidas;
    public int ataques;
    public int exitosos;
    public int fallidos;
    public int muertes;
    public int rendiciones;
    public boolean Jugando;
    public String UltimoAtaqueRealizado;
    public String UltimoAtaqueRecibido;

    public Usuario(String nombre, Personaje[] Personajes, int victorias, int perdidas, int ataques, int exitosos, int fallidos,int muertes, int rendiciones,boolean jugando,String realizado,String recibido) {
        this.nombre = nombre;
        this.Personajes = Personajes;
        this.victorias = victorias;
        this.perdidas = perdidas;
        this.ataques = ataques;
        this.exitosos = exitosos;
        this.fallidos = fallidos;
        this.rendiciones = rendiciones;
        this.Jugando = jugando;
        this.muertes = muertes;
        this.UltimoAtaqueRealizado = realizado;
        this.UltimoAtaqueRecibido = recibido;
    }
    
    
}
