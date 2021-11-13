package POJO;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable {

    private String numero;
    private String sucursal;
    private ArrayList<Cliente> clientes = null;
   

    public Cuenta(String numero, String sucursal, ArrayList<Cliente> clientes) {
        this.numero = numero;
        this.sucursal = sucursal;
        this.clientes = clientes;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {
        String mensaje = "\nCUENTA " + this.numero + "\nSucursal: " + this.sucursal+"\n";
        for (Cliente x : clientes) {
            mensaje += x.toString();
        }
        return mensaje;
    }
}
