package operaciones;

import Excepciones.CuentaYaExiste;
import POJO.Banco;
import POJO.Cliente;
import POJO.Cuenta;
import java.util.Scanner;
import utilidades.ControlData;

public class operacionesCuentas {

    static Scanner sc = new Scanner(System.in);

    public static String comprobarCuenta(String numCuenta) {

        try {
            for (Cuenta x : Banco.getCuentas()) {
                if (numCuenta.equals(x.getNumero())) {
                    throw new CuentaYaExiste();
                }
            }
        } catch (CuentaYaExiste e) {
            return e.getMessage();
        }
        return "El número de cuenta estádisponible";
    }

    public static Cliente comprobarCliente(String dniNuevo) {
        Cliente cliente = null;
        for (Cuenta cuentaBanco : Banco.getCuentas()) {
            boolean flag = false;
            for (Cliente clienteBanco : cuentaBanco.getClientes()) {
                if (clienteBanco.getDni().equals(dniNuevo)) {
                    System.out.println("Ese cliente ya está registrado en nuestra base de datos");
                    flag = true;
                    cliente = clienteBanco;
                }
            }
            if (flag == false) {
                System.out.println("Ese cliente no está registrado. Por favor, introduce su nombre");
                String nombre = ControlData.lerString(sc);
                System.out.println("Introduce la dirección");
                String direccion = ControlData.lerString(sc);
                Cliente cliente1 = new Cliente(dniNuevo, nombre, direccion);
                cliente = cliente1;
            }
        }
        return cliente;
    }

}
