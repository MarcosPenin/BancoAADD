
package POJO;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Movimiento implements Serializable {
    
    private String numeroCuenta;
    private LocalDate fechaOperacion;
    private double cantidad;
    private double saldo;
    private String tipo;

    public Movimiento(String numCuenta,LocalDate fechaoperacion, double cantidad,double saldo,String tipo){
        this.numeroCuenta=numCuenta;
        this.fechaOperacion=fechaoperacion;
        this.cantidad=cantidad;
        this.saldo=saldo;
        this.tipo=tipo;
    }
       
    
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  "\n"+this.tipo+"  "+this.cantidad+"  "+this.fechaOperacion+"  Nuevo saldo: "+this.saldo;

    }
    
    

}
