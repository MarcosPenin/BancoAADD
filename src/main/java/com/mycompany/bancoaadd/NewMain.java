package com.mycompany.bancoaadd;

import Excepciones.ClienteRepetido;
import Excepciones.CodigoIncorrecto;
import Excepciones.DniInvalido;
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
                    try {
                    OperacionesCuentas.anadirCuenta();
                } catch (CodigoIncorrecto e) {
                    System.out.println(e.getMessage());
                }catch(DniInvalido e){
                        System.out.println(e.getMessage());
                }
                break;
                case 2:
                    System.out.println("Introuzca el DNI del cliente que desea añadir");
                    String dni = ControlData.lerString(sc);
                    try {
                        OperacionesCuentas.anadirCliente(dni);
                    } catch (ClienteRepetido e) {
                        System.out.println(e.getMessage());
                    }catch(DniInvalido e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Introduce el número de cuenta a la que desea acceder");
                    String numCuenta = ControlData.lerString(sc);
                    OperacionesCuentas.anadirMovimiento(numCuenta);
                    break;
                case 4:
                    System.out.println("Introduce el número de cuenta");
                    numCuenta = ControlData.lerString(sc);
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
                    System.out.println("Introduce el número de cuenta del que quieres consultar los movimientos");
                    numCuenta=ControlData.lerString(sc);
                    OperacionesCuentas.consultarMovimientos(numCuenta);
                    break;
                case 9:
                    System.out.println("Introduce el número de DNI del cliente");
                    dni = ControlData.lerString(sc);
                    OperacionesCuentas.modificarDireccionCliente(dni);
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
