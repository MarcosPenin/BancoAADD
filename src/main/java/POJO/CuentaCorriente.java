
package POJO;

import java.util.ArrayList;
import java.util.Set;

public class CuentaCorriente extends Cuenta {
    
    private double saldoActual;
    private ArrayList movimientos;

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public ArrayList getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList movimientos) {
        this.movimientos = movimientos;
    }
    
}
