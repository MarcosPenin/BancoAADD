package Excepciones;

public class CuentaNoExiste extends Exception {

    public CuentaNoExiste() {
        super("Lo siento, la cuenta que busca no existe.");
    }
}
