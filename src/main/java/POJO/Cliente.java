package POJO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

public class Cliente implements Serializable{

    private String dni;
    private String nombre;
    private String direccion;

    public Cliente(String dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "\nCLIENTE\nNombre: " + this.nombre + "\nDNI: " + this.dni + "\nDirección: " + this.direccion;
    }

}
