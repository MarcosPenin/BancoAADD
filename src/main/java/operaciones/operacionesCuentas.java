package operaciones;

import Excepciones.ClienteNoExiste;
import Excepciones.CuentaNoExiste;
import POJO.Banco;
import POJO.Cliente;
import POJO.Cuenta;
import POJO.CuentaCorriente;
import POJO.CuentaPlazo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import utilidades.ControlData;
import utilidades.Menu;

public class OperacionesCuentas {

    static Scanner sc = new Scanner(System.in);

    public static Cuenta buscarCuenta(String numCuenta) throws CuentaNoExiste {
        Cuenta cuenta = null;

        for (Cuenta x : Banco.getCuentas()) {
            if (numCuenta.equals(x.getNumero())) {
                cuenta = x;
            }
            if (cuenta == null) {
                throw new CuentaNoExiste();
            }
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
            System.out.println(e.getMessage());
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

    public static void anadirCuenta() {

        byte opcion, opcion2;
        Menu tipoCuenta = new Menu(tipoCuenta());
        Menu siNo = new Menu(siNo());

        System.out.println("Introduce el número de cuenta");
        String numCuenta = ControlData.lerString(sc);
        Cuenta cuenta = null;

        if (!OperacionesCuentas.comprobarCuenta(numCuenta)) {
            System.out.println("Lo siento, ese número de cuenta no está disponible");
        } else {
            System.out.println("Introduce la sucursal");
            String sucursal = ControlData.lerString(sc);

            ArrayList<Cliente> clientes = new ArrayList<>();

            do {
                System.out.println("Introduce el DNI del cliente");
                String dni = ControlData.lerString(sc);
                Cliente cliente = OperacionesCuentas.comprobarCliente(dni);
                clientes.add(cliente);
                System.out.println("¿Quieres añadir otro cliente?");
                siNo.printMenu();
                opcion2 = ControlData.lerByte(sc);
            } while (opcion2 == 1);

            System.out.println("Qué tipo de cuenta desea crear");
            tipoCuenta.printMenu();
            opcion = sc.nextByte();

            if (opcion == 1) {
                System.out.println("Introduce el saldo inicial");
                double saldo = ControlData.lerDouble(sc);
                cuenta = new CuentaCorriente(numCuenta, sucursal, clientes, saldo);
            } else if (opcion == 2) {
                System.out.println("Define los intereses");
                float intereses = ControlData.lerFloat(sc);
                System.out.println("Define el depósito a plazo");
                double depositoPlazo = ControlData.lerDouble(sc);
                System.out.println("Introduce la fecha de vencimiento");
                System.out.println("Año:");
                int ano = ControlData.lerInt(sc);
                System.out.println("Mes:");
                int mes = ControlData.lerInt(sc);
                System.out.println("Día:");
                int dia = ControlData.lerInt(sc);
                Date fechaVencimiento = new Date(ano, mes, dia);
                cuenta = new CuentaPlazo(numCuenta, sucursal, clientes, intereses, fechaVencimiento, depositoPlazo);
            }
            Banco.getCuentas().add(cuenta);
        }

    }

    public static void anadirCliente(String dni) {
        System.out.println("Introduzca el número de cuenta a la que desea añadir el cliente");
        String numCuenta = ControlData.lerString(sc);
        try {
            Cuenta cuenta1 = OperacionesCuentas.buscarCuenta(numCuenta);
            Cliente cliente1 = OperacionesCuentas.comprobarCliente(dni);
            cuenta1.getClientes().add(cliente1);
            System.out.println("El clliente se ha añadido a la cuenta");
        } catch (CuentaNoExiste e) {
            System.out.println(e.getMessage());
        }

    }

    public static void eliminarCuenta(String numCuenta) {
        int marcador = -1;
        for (int i = 0; i < Banco.getCuentas().size(); i++) {
            if (numCuenta.equals(Banco.getCuentas().get(i).getNumero())) {
                marcador = i;
            }
        }
        if (marcador >= 0) {
            Banco.getCuentas().remove(marcador);
            System.out.println("La cuenta se ha eliminado con éxito");
        } else {
            System.out.println("No se ha encontrado ninguna cuenta con ese número");
        }
    }

    public static void eliminarCliente(String numCuenta, String dni) {
        int marcador = -1, marcador2 = -1;
        for (int i = 0; i < Banco.getCuentas().size(); i++) {
            for (int j = 0; j < Banco.getCuentas().get(i).getClientes().size(); j++) {
                if (dni.equals(Banco.getCuentas().get(i).getClientes().get(j).getDni())) {
                    marcador = j;
                    marcador2 = i;
                }
            }
        }
        if (marcador >= 0) {
            Banco.getCuentas().get(marcador2).getClientes().remove(marcador);
            System.out.println("Se ha eliminado el cliente");
        } else {
            System.out.println("No se ha podido eliminar");
        }

    }

    public static void modificarDireccionCliente(String dni, String direccion) {
        String respuesta = "Lo siento, no se ha encontrado ningún cliente con ese DNI.";
        for (Cuenta x : Banco.getCuentas()) {
            for (Cliente j : x.getClientes()) {
                if (j.getDni().equals(dni)) {
                    j.setDireccion(direccion);
                    respuesta = "Dirección cambiada";
                }
            }
        }
        System.out.println(respuesta);
    }

    public static void mostrarClientes(String numCuenta) {
        try {
            Cuenta cuenta1 = OperacionesCuentas.buscarCuenta(numCuenta);
            for (Cliente x : cuenta1.getClientes()) {
                System.out.println(x.toString());
            }
        } catch (CuentaNoExiste e) {
            System.out.println(e.getMessage());
        }

    }

    public static void mostrarCuentas(String dni) {
        boolean flag = false;      
        
        for (Cuenta x : Banco.getCuentas()) {
            for (Cliente y : x.getClientes()) {
                if (y.getDni().equals(dni)) {
                    System.out.println(x.toString());
                    flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println("No hay ningún cliente con ese DNI");
        }
    }

    static ArrayList<String> tipoCuenta() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Cuenta corriente");
        opciones.add("Cuenta a plazo");
        return opciones;
    }

    static ArrayList<String> siNo() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Sí");
        opciones.add("No");
        return opciones;
    }

}
