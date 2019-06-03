
package database;

import Metodos.Metodos;
import static Metodos.Metodos.createNewDatabase;
import static Metodos.Metodos.createNewTable;
import Paleta.ConsolaDB;

/**
 *
 * @author X541
 */
public class Database {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         createNewDatabase("testeo.db");

        ConsolaDB consola = new ConsolaDB();
        consola.setVisible(true);
        createNewTable();
        Metodos bd = new Metodos();
    }
    
}
