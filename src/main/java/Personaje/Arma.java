/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personaje;

import java.util.ArrayList;

/**
 *
 * @author jecheverria
 */
public class Arma {
   private String Nombre;
   
   private Tipo[] Daño;
   public boolean Disponible;
   public Arma(String Nombre) {
        this.Disponible = true;
        this.Nombre = Nombre;
        Tipo[] Tipos = Tipo.values();
        for (Tipo tipo : Tipos){
            tipo.generar_daño();
        }
        this.Daño = Tipos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Tipo[] getDaño() {
        return Daño;
    }
    
    


}
