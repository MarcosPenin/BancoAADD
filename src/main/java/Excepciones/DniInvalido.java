
package Excepciones;

/**
 *
 * @author marco
 */
public class DniInvalido extends Exception {
     public DniInvalido() {
        super("Lo siento, ha introducido un DNI inválido");
    }
}
