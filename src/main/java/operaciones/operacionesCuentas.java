package operaciones;

import Excepciones.ClienteRepetido;
import Excepciones.CodigoIncorrecto;
import Excepciones.DniInvalido;
import POJO.Banco;
import POJO.Cliente;
import POJO.Cuenta;
import POJO.CuentaCorriente;
import POJO.CuentaPlazo;
import POJO.Movimiento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilidades.ControlData;
import utilidades.Menu;

public class OperacionesCuentas {

    static Scanner sc = new Scanner(System.in);

    /**
     * Este método pide los datos necesarios para crear una nueva cuenta. Antes
     * de empezar comprueba si el número de cuenta introducido es válido con el
     * método de la clase ControlData "comprobar numCuenta". Si es válido
     * comprueba si está disponible y le pide al usuario los datos necesarios
     * para crear una Corriente o una Cuenta a Plazo según lo que este decida.
     *
     * @throws CodigoIncorrecto
     * @throws DniInvalido
     */
    public static void anadirCuenta() throws CodigoIncorrecto, DniInvalido {
        byte opcion, opcion2;
        Menu tipoCuenta = new Menu(tipoCuenta());
        Menu siNo = new Menu(siNo());

        System.out.println("Introduce el número de cuenta");
        String numCuenta = ControlData.lerString(sc);

        ControlData.comprobarNumCuenta(numCuenta);

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

                //Este método crea un nuevo cliente o recupera uno ya existente       
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
                System.out.println("¿Cuánto dinero depositará?");
                double depositoPlazo = ControlData.lerDouble(sc);
                System.out.println("¿Cuáles serán los intereses?");
                float intereses = ControlData.lerFloat(sc);
                System.out.println("¿En cuántos meses vencerá el depósito?");
                int plazo = ControlData.lerInt(sc);
                LocalDate fechaVencimiento = LocalDate.now().plusMonths(plazo);
                cuenta = new CuentaPlazo(numCuenta, sucursal, clientes, intereses, fechaVencimiento, depositoPlazo);
            }

        }
        Banco.getCuentas().add(cuenta);
    }

    /**
     * Este método controla la asignación de clientes a las cuentas. Antes de
     * nada comprueba si el DNI introducido es válido, en caso contrario lanza
     * una excepción. A continuación comprueba si hay algún cliente con ese DNI
     * registrado en el banco. En caso afirmativo recupera sus datos y los
     * devuelve. Si no lo hay solicita al usuario los datos necesarios para
     * crear un nuevo cliente y lo devuelve.
     *
     * @param dniNuevo
     * @return Cliente
     * @throws DniInvalido
     */
    public static Cliente comprobarCliente(String dniNuevo) throws DniInvalido {
        Cliente cliente = null;
        boolean flag;
        ControlData.comprobarDni(dniNuevo);

        if (Banco.getCuentas().isEmpty()) {
            flag = false;
            System.out.println("Por favor, introduce el nombre del cliente");
            String nombre = ControlData.lerString(sc);
            System.out.println("Introduce la dirección");
            String direccion = ControlData.lerString(sc);
            Cliente cliente1 = new Cliente(dniNuevo, nombre, direccion);
            cliente = cliente1;
        } else {
            flag = false;
            for (Cuenta cuentaBanco : Banco.getCuentas()) {
                for (Cliente clienteBanco : cuentaBanco.getClientes()) {
                    if (clienteBanco.getDni().equals(dniNuevo)) {
                        System.out.println("Ese cliente ya está registrado. Se han recuperado sus datos");
                        flag = true;
                        cliente = clienteBanco;
                    }
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

    /**
     * Este método comprueba si el número de cuenta solicitado está disponible o
     * ya ha sido utilizado
     *
     * @param numCuenta
     * @return boolean
     */
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

    /**
     * Este método añade un cliente a una cuenta ya existente, siempre que esta
     * exista y no tenga a ese mismo cliente ya asignado
     *
     * @param dni
     * @throws ClienteRepetido
     * @throws DniInvalido
     */
    public static void anadirCliente(String dni) throws ClienteRepetido, DniInvalido {
        ControlData.comprobarDni(dni);
        boolean flag = false;
        System.out.println("Introduzca el número de cuenta a la que desea añadir el cliente");
        String numCuenta = ControlData.lerString(sc);
        for (Cuenta x : Banco.getCuentas()) {
            if (x.getNumero().equals(numCuenta)) {
                for (Cliente y : x.getClientes()) {
                    if (y.getDni().equals(dni)) {
                        throw new ClienteRepetido();
                    }
                }
                Cliente cliente1 = OperacionesCuentas.comprobarCliente(dni);
                x.getClientes().add(cliente1);
                System.out.println("El cliente se ha añadido con éxito");
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Lo siento, no se ha encontrado ninguna cuenta con ese número");
        }
    }

    /**
     * Elimina una cuenta del registro de la clase Banco
     *
     * @param numCuenta
     */
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

    /**
     * Elimina un cliente de una cuenta
     *
     * @param numCuenta
     * @param dni
     */
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

    /**
     * Modifica la dirección de un cliente. El cambio se propaga a todas las
     * cuentas en las que está registrado
     *
     * @param dni
     */
    public static void modificarDireccionCliente(String dni) {
        String respuesta = "Lo siento, no se ha encontrado ningún cliente con ese DNI.";
        for (Cuenta x : Banco.getCuentas()) {
            for (Cliente j : x.getClientes()) {
                if (j.getDni().equals(dni)) {
                    System.out.println("Introduce la nueva direccion");
                    String direccion = ControlData.lerString(sc);
                    j.setDireccion(direccion);
                    respuesta = "Dirección cambiada";
                }
            }
        }
        System.out.println(respuesta);
    }

    /**
     * Muestra los clientes de una cuenta
     *
     * @param numCuenta
     */
    public static void mostrarClientes(String numCuenta) {
        boolean flag = false;
        for (Cuenta x : Banco.getCuentas()) {
            if (x.getNumero().equals(numCuenta)) {
                for (Cliente y : x.getClientes()) {
                    System.out.println(y.toString());
                }
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Lo siento, no se ha encontrado ninguna cuenta con ese número");
        }

    }

    /**
     * Muestra las cuentas de un determinado cliente
     *
     * @param dni
     */
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

    /**
     * Añade un movimiento al registro de una cuenta. Será un ingreso o una
     * retirada en función de lo que indique el usuario. Como restricciones, no
     * se pueden añadir movimientos a una CuentaPlazo ni realizar retiradas que
     * superen el saldo de la cuenta indicada
     *
     * @param numCuenta
     */
    public static void anadirMovimiento(String numCuenta) {
        boolean flag = false;
        Menu movimientos = new Menu(movimientos());
        for (Cuenta x : Banco.getCuentas()) {
            if (x.getNumero().equals(numCuenta)) {
                if (x instanceof CuentaCorriente) {
                    System.out.println("¿Deseas ingresar o retirar efectivo?");
                    movimientos.printMenu();
                    byte opcion = ControlData.lerByte(sc);
                    Movimiento movimiento;
                    if (opcion == 1) {
                        System.out.println("¿Cuánto deseas ingresar?");
                        double ingreso = ControlData.lerDouble(sc);
                        ((CuentaCorriente) x).setSaldoActual(((CuentaCorriente) x).getSaldoActual() + ingreso);
                        System.out.println("Se ha realizado el ingreso. Su nuevo saldo es de " + ((CuentaCorriente) x).getSaldoActual() + " euros.");
                        movimiento = new Movimiento(x.getNumero(), LocalDate.now(), ingreso, ((CuentaCorriente) x).getSaldoActual(), "Ingreso");
                        ((CuentaCorriente) x).anadirMovimiento(movimiento);
                    }
                    if (opcion == 2) {
                        System.out.println("¿Cuánto desea retirar?");
                        double retirada = ControlData.lerDouble(sc);
                        if (retirada <= ((CuentaCorriente) x).getSaldoActual()) {
                            ((CuentaCorriente) x).setSaldoActual(((CuentaCorriente) x).getSaldoActual() - retirada);
                            System.out.println("Se ha realizado la retirada. Su nuevo saldo es de " + ((CuentaCorriente) x).getSaldoActual() + " euros.");
                            movimiento = new Movimiento(x.getNumero(), LocalDate.now(), retirada, ((CuentaCorriente) x).getSaldoActual(), "Retirada");
                            ((CuentaCorriente) x).anadirMovimiento(movimiento);
                        } else {
                            System.out.println("SALDO INSUFICIENTE");
                        }
                    }
                    flag = true;
                } else {
                    System.out.println("Lo siento, no puede realizar operaciones sobre una cuenta a plazo");
                    flag = true;
                }
            }
        }
        if (!flag) {
            System.out.println("No se ha podido realizar la operación");
        }
    }

    /**
     * A partir de un número de cuenta, se solicita al usuario que indique dos
     * fechas y se le muestra los movimientos que se han realizado en esa cuenta
     * en el período comprendido entre ellas
     *
     * @param numCuenta
     */
    public static void consultarMovimientos(String numCuenta) {
        boolean flag = false;
        for (Cuenta x : Banco.getCuentas()) {
            if (x.getNumero().equals(numCuenta)) {
                if (x instanceof CuentaCorriente) {
                    System.out.println("¿Desde qué fecha desea consultar los movimientos?.Introdúzcala en formato M/d/yyyy");
                    String desde = ControlData.lerString(sc);
                    System.out.println("¿Hasta qué fecha desea consultar los movimientos? Introdúzcala en formato M/d/yyyy");
                    String hasta = ControlData.lerString(sc);
                    try {
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
                        LocalDate fechaInicio = LocalDate.parse(desde, dateFormat);
                        LocalDate fechaFin = LocalDate.parse(hasta, dateFormat);

                        for (Movimiento y : ((CuentaCorriente) x).getMovimientos()) {
                            if (y.getFechaOperacion().isAfter(fechaInicio) && y.getFechaOperacion().isBefore(fechaFin)) {
                                System.out.println(y.toString());
                                flag = true;
                            }
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("La fecha introducida no tiene un formato válido");
                    }
                } else {
                    System.out.println("No puede realizar movimientos sobre un depósito a plazo");
                }
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No se ha encontrado ninguna cuenta con ese número");
        }

    }
/**
 * Imprime la información de todas las cuentas guardadas
 */
    public static void mostrarTodo() {
        for (Cuenta x : Banco.getCuentas()) {
            System.out.println(x.toString());
        }
    }

    /**
     * Menús necesarios para algunos de los métodos
     *
     * @return
     */
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

    static ArrayList<String> movimientos() {
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Ingresar");
        opciones.add("Retirar");
        return opciones;
    }

}
