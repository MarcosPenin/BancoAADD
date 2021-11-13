
package Excepciones;

/**
 *
 * @author marco
 */
public class CodigoIncorrecto extends Exception {
     public CodigoIncorrecto() {
        super("Lo siento, el número de cuenta debe estar compuesto de cinco letras seguidas de un dígito numérico");
    }
}
