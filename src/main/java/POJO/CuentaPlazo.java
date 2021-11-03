package POJO;

import java.util.ArrayList;
import java.util.Date;

public class CuentaPlazo extends Cuenta {

    private float intereses;
    private Date fechaVencimiento;
    private double depositoPlazo;

    public CuentaPlazo(String numero, String sucursal, ArrayList<Cliente> clientes, float intereses, Date fechaVencimiento, double depositoPlazo) {
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

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(double depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }
}
