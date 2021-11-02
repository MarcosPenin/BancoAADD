
package POJO;

import java.util.Date;

public class CuentaPlazo extends Cuenta {
    
    private float intereses;
    private Date fechaVencimiento;
    private long depositoPlazo;

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

    public long getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(long depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }
}
