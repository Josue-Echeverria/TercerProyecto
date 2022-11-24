/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Personaje;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jecheverria
 */
public class Arma implements Serializable{
   private String Nombre;

   private int[] Daño;

   public boolean Disponible;
   public Arma(String Nombre) {
        this.Disponible = true;
        this.Nombre = Nombre;
        this.Daño = new int[10];
        //Tipo[] Tipos = Tipo.values();
        for (int i = 0; i < 10; i++) {
            this.Daño[i] = generar_daño();
        }
        
        
    }

    public String getNombre() {
        return Nombre;
    }
    public int generar_daño(){
        return (20 + (int)(Math.random() * ((100 - 20) + 1)));
        
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int[] getDaño() {
        return Daño;
    }

    public void setDaño(int[] Daño) {
        this.Daño = Daño;
    }
    
    
    


}
