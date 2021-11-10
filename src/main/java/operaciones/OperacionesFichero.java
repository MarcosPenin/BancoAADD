package operaciones;

import POJO.Banco;
import POJO.Cuenta;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a20marcosgp
 */
public class OperacionesFichero {

    

    public static void guardarDatos() {
        try {
           ArrayList<Cuenta> cuentas=Banco.cuentas;  
            File archivo = new File("Archivo.dat");
            archivo.createNewFile();
            ObjectOutputStream mos = new ObjectOutputStream(new FileOutputStream(archivo));
            mos.writeObject(cuentas);

        } catch (IOException ex) {
            Logger.getLogger(OperacionesFichero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void actualizarDatos() {

        try {
            File archivo = new File("Archivo.dat");
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() > 0) {
                Banco.setCuentas((ArrayList<Cuenta>) ois.readObject());

            }
        } catch (IOException e) {
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.getMessage();
        }
    }
}
