/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personaje;

import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author jecheverria
 */
public class Personaje {
    private String Nombre;
    private Tipo Tipo;
    private int Vida = 100;
    private Arma[] Armas;
    private int PosTipo;
    private Icon Apariencia;
    private String Direccion;
    public Personaje() {
    
    }

    public Personaje(String Nombre, Tipo Tipo, Arma[] Armas, Icon Apariencia, String Direccion, int pos) {
        this.Nombre = Nombre;
        this.PosTipo = pos;
        this.Tipo = Tipo;
        this.Direccion = Direccion;
        this.Armas = Armas;
        this.Apariencia = Apariencia;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getPosTipo() {
        return PosTipo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public Icon getApariencia() {
        return Apariencia;
    }

    public void setApariencia(Icon Apariencia) {
        this.Apariencia = Apariencia;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Tipo getTipo() {
        return Tipo;
    }

    public void setTipo(Tipo Tipo) {
        this.Tipo = Tipo;
    }

    public int getVida() {
        return Vida;
    }

    public void setVida(int Vida) {
        this.Vida = Vida;
    }

    public Arma[] getArmas() {
        return Armas;
    }

    public void setArmas(Arma[] Armas) {
        this.Armas = Armas;
    }


    
}
