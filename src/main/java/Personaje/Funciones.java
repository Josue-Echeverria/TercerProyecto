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
public class Funciones {
    public Personaje[] OrdenarArrayPersonajes(Personaje[] ArrayPaOrdenar){
        Personaje[] NewArray = new Personaje[0];
        if (ArrayPaOrdenar == null){
            return NewArray;
        }
        else{
            NewArray = new Personaje[ArrayPaOrdenar.length];
            int cont =  0;
            for( Personaje Persomaje : ArrayPaOrdenar){
                if (Persomaje!= null){
                    NewArray[cont] = Persomaje;
                    cont++;
                }
            }
            return NewArray;
        }
    }
            
    public String[] PersonajeArraytoStringArray(Personaje[] parr){
        String[] arr = new String[0];
        if (parr == null){
            return arr;
        
        }else{
            arr = new String[parr.length];
            int cont = 0;
            for (Personaje p : parr){
                if (p != null){
                    arr[cont]= p.getNombre();
                    cont++;
                }
            }
            return arr;
        }      
    }
    
    public String[] ArrayListtoArray (ArrayList<String> lst){
        String[] arr = new String[lst.size()];
        int cont = 0;
        for (String str : lst){
            arr[cont] = str;
            cont++;
        }
        return arr;
    }
    
    
    
    
}
