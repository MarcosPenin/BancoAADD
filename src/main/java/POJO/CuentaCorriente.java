package POJO;

import java.util.ArrayList;
import java.util.Set;

public class CuentaCorriente extends Cuenta {

    private double saldoActual;
    private ArrayList<Movimiento> movimientos;

    public CuentaCorriente(String numero, String sucursal, ArrayList<Cliente> clientes, double saldo) {
        super(numero, sucursal, clientes);
        this.saldoActual = saldo;
        this.movimientos = new ArrayList<Movimiento>();
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList movimientos) {
        this.movimientos = movimientos;
    }

    public void anadirMovimiento(Movimiento nuevoMovimiento) {
        if (this.movimientos == null) {
            this.movimientos = new ArrayList<Movimiento>();
            this.movimientos.add(nuevoMovimiento);
        } else {
            this.movimientos.add(nuevoMovimiento);
        }
    }

    @Override
    public String toString() {
        String mensaje = "\nCUENTA corriente " + this.getNumero() + "\nSucursal: " + this.getSucursal() + "\nSaldo actual: " + saldoActual + "\n";
        for (Cliente x : super.getClientes()) {
            mensaje += x.toString();
        }
        if (this.getMovimientos() != null) {
            for (Movimiento y : this.getMovimientos()) {
                mensaje += y.toString();
            }
        }
        return mensaje;
    }

}
