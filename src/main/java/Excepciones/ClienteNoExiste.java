
package Excepciones;

/**
 *
 * @author a20marcosgp
 */
public class ClienteNoExiste extends Exception {

    public ClienteNoExiste() {
        super("Lo siento, el cliente que busca no existe.");
    }
    
}
