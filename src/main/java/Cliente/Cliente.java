/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cliente;

import Personaje.CrearPersonaje;
import Personaje.Personaje;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jecheverria
 */
public class Cliente {
    private final String IP = "localhost";
    private final int PORT = 8084;
    private Socket socket;
    ObjectOutputStream salida;
    private DataOutputStream salidaDatos;
    Pantalla pantalla;
    String nombre ;
    Personaje[] Personajes;
     
     ThreadCliente threadCliente;

    public Cliente() {
        this.Personajes = new Personaje[4];
        Personajes[0] = new Personaje();
        Personajes[1] = new Personaje();
        Personajes[2] = new Personaje();
        Personajes[3] = new Personaje();
        conectar();
    }

    
    
    public void conectar(){
        try {
            socket = new Socket(IP, PORT);
            salida = new ObjectOutputStream(socket.getOutputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            threadCliente = new ThreadCliente(socket, this);
            threadCliente.start();
            // al conectarse, envía el nombre
            this.nombre = JOptionPane.showInputDialog("Nombre: ");
            new CrearPersonaje(this,this.Personajes).setVisible(true);
            
            salidaDatos.writeUTF(nombre);
        } catch (IOException ex) {
            
        }
    }
}
