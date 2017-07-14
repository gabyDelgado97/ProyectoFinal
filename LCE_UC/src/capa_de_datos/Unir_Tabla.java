/*0         1     2      3        4       5           6     7   8  9      
UNIR nombre_tabla1,nombre_tabla2 POR nombre_campo = algo ORDENADO asc 
UNIR nombre_tabla1,nombre_tabla2 POR nombre_campo = algo
 */
package capa_de_datos;

import capa_Logica_Negocios.ValidarTablaVaciaModificar;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Unir_Tabla {
    public static String unir;
    
    public static boolean proceso_unir_tabla(ArrayList<String> tokens) {

        boolean correctaUnion = false;
        ArrayList<String> campos = new ArrayList<>();
        File archivo1 = new File("Tablas/" + tokens.get(1) + ".CSV");
        File archivo2 = new File("Tablas/" + tokens.get(2) + ".CSV");
        String[] arregloCampos = null;
        String[] arregloCampos2 = null;
        String cadenaConcatenada = "";
        int ubicacionCampoClave = 0;
        ArrayList<String> nuevoarregloCampos = new ArrayList<String>();

        try {
            CsvReader lector1 = new CsvReader(new FileReader(archivo1), ';');
            CsvReader lector2 = new CsvReader(new FileReader(archivo2), ';');

            lector1.readHeaders();
            lector2.readHeaders();
            arregloCampos = lector1.getHeaders();
            arregloCampos2 = lector2.getHeaders();

            /// sacar las cabeceras del archivo1 en un string
            for (int j = 0; j < arregloCampos.length; j++) {
                cadenaConcatenada += arregloCampos[j];
                cadenaConcatenada += " ";
            }
            /// sacar las cabeceras del archivo2 en un string
            for (int j = 0; j < arregloCampos2.length; j++) {
                if (!arregloCampos2[j].equals(tokens.get(4))) {
                    cadenaConcatenada += arregloCampos2[j];
                    cadenaConcatenada += " ";
                } else {
                    ubicacionCampoClave = j;
                }
            }

            /// buscar si el valor del archivo1 es igual al archivo2
            while (lector1.readRecord()) {
                lector2 = new CsvReader(new FileReader(archivo2), ';');
                lector2.readHeaders();
                while (lector2.readRecord()) {
                    if (lector1.get(tokens.get(4)).equals(lector2.get(tokens.get(4)))) {
                        // sacamos los valores de cada linea del archivo 1 y vamos concatenando en la cadena concatenada
                        cadenaConcatenada += "\n";
                        String[] cadAux = lector1.getValues();
                        for (int i = 0; i < cadAux.length; i++) {
                            cadenaConcatenada += cadAux[i];
                            cadenaConcatenada += " ";
                        }

                        // sacamos los valores de cada linea del archivo 2 y vamos concatenando en la cadena concatenada
                        String[] cadAux2 = lector2.getValues();
                        for (int i = 0; i < cadAux2.length; i++) {
                            if (ubicacionCampoClave != i) {
                                cadenaConcatenada += cadAux2[i];
                                cadenaConcatenada += " ";
                            }
                        }

                    }
                }
                lector2.close();
            }

            lector1.close();
            unir=cadenaConcatenada;
            correctaUnion = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correctaUnion;
    }

}
