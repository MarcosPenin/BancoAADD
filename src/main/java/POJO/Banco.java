package POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class Banco implements Serializable {

    public static ArrayList<Cuenta> cuentas = new ArrayList();

    public static ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public static void setCuentas(ArrayList<Cuenta> aCuentas) {
        cuentas = aCuentas;
    }

    
    
    
    
}
