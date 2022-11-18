/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personaje;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.Icon;

/**
 *
 * @author jecheverria
 */
public class Personaje implements Serializable {
    private String Nombre;
    private Tipo Tipo;
    private int Vida = 100;
    private Arma[] Armas;
    private Icon Apariencia;
    public Personaje() {
    
    }

    public String getNombre() {
        return Nombre;
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
