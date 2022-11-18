/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Personaje;

import java.io.Serializable;

/**
 *
 * @author jecheverria
 */
public enum Tipo implements Serializable{
    FUEGO,
    AIRE,
    AGUA,
    MAGIABLANCA,
    MAGIANEGRA,
    ELECTRICIDAD,
    HIELO,
    ACIDO,
    ESPIRITUAL,
    HIERRO;
    public int Daño;

    public void setDaño(int Daño) {
        this.Daño = Daño;
    }
    /*public void generar_daño(){
        setDaño(20 + (int)(Math.random() * ((100 - 20) + 1)));
        
    }*/

    public int getDaño() {
        return Daño;
    }
}
