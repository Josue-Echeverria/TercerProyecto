/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class ThreadCronometro extends Thread{
    private boolean isRunning = true;
    private boolean isPaused = false;
    private Pantalla refPantalla;
    private int contador = 0;
    private int contadorMinutos = 0;
    private String segundos;
    private String minutos;
    private int contadorHoras = 0;
    public ThreadCronometro(Pantalla refPantalla) {
        this.refPantalla = refPantalla;
    } 
    
    @Override
    public void run(){
        
        while (isRunning){
            
            try {
                sleep(1000);
                contador++;
                
                if (contador == 60){
                    contadorMinutos += 1;
                    
                    contador = 0;
                }
                if (contadorMinutos == 60){
                    contador = 0;
                    contadorMinutos = 0;
                    contadorHoras  +=1;
                }
                if(contador < 10){
                    
                    segundos = ("0"+Integer.toString(contador));
                }else{
                    segundos = (Integer.toString(contador));
                }
                if(contadorMinutos < 10){
                    
                    minutos = ("0"+Integer.toString(contadorMinutos));
                }else{
                     minutos = (Integer.toString(contadorMinutos));
                }
                if(contadorMinutos == 5){
                    refPantalla.lbCronometro.setText("Comodin disponible");
                    pausa();
                }else{
                    //System.out.println(minutos+":"+segundos);
                    refPantalla.lbCronometro.setText(minutos+":"+segundos);
                    
                }
                
            } catch (InterruptedException ex) {
            }
            
            while (isPaused) {                
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                }
            }
        }
        
        
    }

    public int getContador() {
        return contador;
    }
    public void Restar(){
        
        
        contador = 0;
        contadorMinutos = 0;
        contadorHoras = 0;
        refPantalla.lbCronometro.setText(minutos+":"+segundos);
    }
    
    
    public boolean pausa(){
        this.isPaused = !this.isPaused;
        return this.isPaused;
    }
    
    public void finish(){
        this.isRunning = false;
        this.isPaused = false;
    }
    
    
}
