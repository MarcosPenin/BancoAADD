package POJO;

import java.util.ArrayList;

public class Banco {

    private static ArrayList<Cuenta> cuentas = new ArrayList();

    public static ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

}
