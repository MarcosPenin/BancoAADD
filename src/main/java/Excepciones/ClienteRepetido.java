/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 *
 * @author marco
 */
public class ClienteRepetido extends Exception {

    public ClienteRepetido() {
        super("El cliente con ese DNI ya está registrado en esa cuenta");
    }
    
    
    
}
