package operaciones;

import Excepciones.ClienteNoExiste;
import Excepciones.CuentaNoExiste;
import POJO.Banco;
import POJO.Cliente;
import POJO.Cuenta;
import java.util.Scanner;
import utilidades.ControlData;

public class OperacionesCuentas {

    static Scanner sc = new Scanner(System.in);

    public static Cuenta buscarCuenta(String numCuenta) {
        Cuenta cuenta = null;
        try {
            for (Cuenta x : Banco.getCuentas()) {
                if (numCuenta.equals(x.getNumero())) {
                    cuenta = x;
                }
                if (cuenta == null) {
                    throw new CuentaNoExiste();
                }
            }
        } catch (CuentaNoExiste e) {
            e.getMessage();
        }
        return cuenta;
    }

    public static Cliente buscarCliente(String dni) {
        Cliente cliente = null;
        try {
            for (Cuenta cuentaBanco : Banco.getCuentas()) {

                for (Cliente clienteBanco : cuentaBanco.getClientes()) {
                    if (clienteBanco.getDni().equals(dni)) {
                        cliente = clienteBanco;
                    }
                    if (cliente == null) {
                        throw new ClienteNoExiste();
                    }
                }
            }
        } catch (ClienteNoExiste e) {
            e.getMessage();
        }
        return cliente;

    }

    public static boolean comprobarCuenta(String numCuenta) {
        boolean disponible = true;
        if (Banco.getCuentas().isEmpty()) {
        } else {
            for (Cuenta x : Banco.getCuentas()) {
                if (numCuenta.equals(x.getNumero())) {
                    disponible = false;
                }
            }
        }
        return disponible;
    }

    public static Cliente comprobarCliente(String dniNuevo) {
        Cliente cliente = null;
        boolean flag;
        if (Banco.getCuentas().isEmpty()) {
            flag = false;
            System.out.println("Por favor, introduce el nombre");
            String nombre = ControlData.lerString(sc);
            System.out.println("Introduce la dirección");
            String direccion = ControlData.lerString(sc);
            Cliente cliente1 = new Cliente(dniNuevo, nombre, direccion);
            cliente = cliente1;
        } else {
            for (Cuenta cuentaBanco : Banco.getCuentas()) {
                flag = false;
                for (Cliente clienteBanco : cuentaBanco.getClientes()) {
                    if (clienteBanco.getDni().equals(dniNuevo)) {
                        System.out.println("Ese cliente ya está registrado. Se han recuperado sus datos");
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
        }
        return cliente;
    }

    public static String eliminarCuenta(String numCuenta) {
        String respuesta = "No se ha eliminado la cuenta, no exiten cuentas con ese número";
        for (Cuenta x : Banco.getCuentas()) {
            if (numCuenta.equals(x.getNumero())) {
                Banco.getCuentas().remove(x);
                respuesta = "La cuenta se ha eliminado con éxito";
            }
        }
        return respuesta;
    }

    public static String eliminarCliente(String numCuenta, String dni) {
        String respuesta = "No se ha podido eliminar, la cuenta especificada no tiene ningún cliente con ese DNI";
        Cuenta cuenta1 = OperacionesCuentas.buscarCuenta(numCuenta);
        for (Cliente x : cuenta1.getClientes()) {
            if (dni.equals(dni)) {
                cuenta1.getClientes().remove(x);
                System.out.println("Se ha eliminado el cliente");
            }
        }
        return respuesta;
    }

    public static String modificarDireccionCliente(String numCuenta, String dni) {
        String respuesta = "No se ha podido eliminar, la cuenta especificada no tiene ningún cliente con ese DNI";
        Cuenta cuenta1 = OperacionesCuentas.buscarCuenta(numCuenta);
        for (Cliente x : cuenta1.getClientes()) {
            if (dni.equals(dni)) {
                System.out.println(x.toString());
                System.out.println("Introduce la nueva dirección");
                String direccion = ControlData.lerString(sc);
                x.setDireccion(direccion);
                respuesta = "La modificación se ha guardado con éxito";
            }
        }
        return respuesta;
    }

    
    
    
}
