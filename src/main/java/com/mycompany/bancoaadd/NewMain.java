package com.mycompany.bancoaadd;

import POJO.Banco;
import POJO.Cliente;
import POJO.Cuenta;
import POJO.CuentaCorriente;
import POJO.CuentaPlazo;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import operaciones.OperacionesCuentas;
import operaciones.OperacionesFichero;
import utilidades.ControlData;
import utilidades.Menu;

/**
 *
 * @author a20marcosgp
 */
public class NewMain {

    static Scanner sc = new Scanner(System.in);
    static byte opcion, opcion2, opcion3;
    static Menu menuPrincipal = new Menu(opciones());
    static Menu tipoCuenta = new Menu(tipoCuenta());
    static Menu siNo = new Menu(siNo());
    
    
    public static void main(String[] args) {
        
        OperacionesFichero.actualizarDatos();

        System.out.println("*********************************************************************************************");
        System.out.println("*******************************BIENVENIDO A SU BANCO*********************************");
        do {
            System.out.println("*********************************************************************************************");
            menuPrincipal.printMenu();
            opcion = ControlData.lerByte(sc);
            switch (opcion) {
                case 1:
                    anadirCuenta();
                    break;
                case 2:
                    System.out.println("Pendiente de implementar");
                    break;
                case 3:
                    System.out.println("Pendiente de implementar");
                    break;
                case 4:
                    System.out.println("Introduce el número de cuenta");
                    String numCuenta = ControlData.lerString(sc);
                    OperacionesCuentas.eliminarCuenta(numCuenta);
                    break;
                case 5:
                    System.out.println("Introduce el número de cuenta");
                    numCuenta = ControlData.lerString(sc);
                    System.out.println("Introduce el DNI del cliente que deseas eliminar");
                    String dni = ControlData.lerString(sc);
                    OperacionesCuentas.eliminarCliente(numCuenta, dni);
                    break;
                case 6:
                    System.out.println("Introduce el número de cuenta");
                    numCuenta = ControlData.lerString(sc);
                    OperacionesCuentas.mostrarClientes(numCuenta);
                    break;
                case 7:
                    System.out.println("Introduce el número de DNI del cliente");
                    dni = ControlData.lerString(sc);
                    OperacionesCuentas.mostrarCuentas(dni);
                    break;
                case 8:
                    System.out.println("Pendiente de implementar");
                    break;
                case 9:
                    for (Cuenta x : Banco.getCuentas()) {
                        System.out.println(x.toString());
                    }
            }
        } while (opcion != 10);

    }

    public static void anadirCuenta() {

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
            OperacionesFichero.guardarDatos();
        }

    }

    static ArrayList<String> opciones() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Dar de alta una cuenta");
        opciones.add("Añadir un cliente a una cuenta");
        opciones.add("Añadir un movimiento a una cuenta");
        opciones.add("Dar de baja una cuenta");
        opciones.add("Quitar un cliente de una cuenta");
        opciones.add("Ver los clientes de una cuenta");
        opciones.add("Ver todas las cuentas de un cliente");
        opciones.add("Consultar los movimientos de una cuenta");
        opciones.add("Ver todas las cuentas");
        opciones.add("Salir");
        return opciones;
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
