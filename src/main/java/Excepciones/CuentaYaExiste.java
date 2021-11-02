
package Excepciones;

public class CuentaYaExiste extends Exception {

    public CuentaYaExiste() {
        super("Lo siento, ya existe una cuenta con ese número.");
    }
    
}
