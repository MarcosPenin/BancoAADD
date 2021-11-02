package POJO;

import Excepciones.CuentaYaExiste;
import java.util.ArrayList;

public class Banco {

    private static ArrayList<Cuenta> cuentas;

    public static ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList cuentas) {
        this.cuentas = cuentas;
    }
}
