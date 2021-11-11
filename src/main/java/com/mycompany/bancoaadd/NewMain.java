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
    static byte opcion;
    static Menu menuPrincipal = new Menu(opciones());

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
                    OperacionesCuentas.anadirCuenta();
                    break;
                case 2:
                    System.out.println("Introuzca el dni del cliente que desea añadir");
                    String dni =ControlData.lerString(sc);
                    OperacionesCuentas.anadirCliente(dni);
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
                    dni = ControlData.lerString(sc);
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
                     System.out.println("Introduce el número de DNI del cliente");
                    dni = ControlData.lerString(sc);
                    System.out.println("Introduce la nueva dirección");
                    String direccion=ControlData.lerString(sc);
                    OperacionesCuentas.modificarDireccionCliente(dni,direccion);
                    break;
                case 10:
                    for (Cuenta x : Banco.getCuentas()) {
                        System.out.println(x.toString());
                    }
                    break;
            }
        } while (opcion != 11);
        
        OperacionesFichero.guardarDatos();

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
        opciones.add("Cambiar la dirección de un cliente");
        opciones.add("Ver todas las cuentas");
        opciones.add("Salir");
        return opciones;
    }

 
}
