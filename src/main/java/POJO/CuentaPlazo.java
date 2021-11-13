package POJO;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaPlazo extends Cuenta {

    private float intereses;
    private LocalDate fechaVencimiento;
    private double depositoPlazo;

    public CuentaPlazo(String numero, String sucursal, ArrayList<Cliente> clientes, float intereses, LocalDate fechaVencimiento, double depositoPlazo) {
        super(numero, sucursal, clientes);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
        this.depositoPlazo = depositoPlazo;
    }

    public float getIntereses() {
        return intereses;
    }

    public void setIntereses(float intereses) {
        this.intereses = intereses;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(double depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }

    @Override
    public String toString() {
        String mensaje = "\nCUENTA plazo " + this.getNumero() + "\nSucursal: " + this.getSucursal() + "\nIntereses: " + intereses + "\nFecha vencimiento: " + fechaVencimiento+"\n";
        for (Cliente x : super.getClientes()) {
            mensaje += x.toString();
        }
        return mensaje;
    }

}
