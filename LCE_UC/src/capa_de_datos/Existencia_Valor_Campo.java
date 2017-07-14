package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ValidarTablaVaciaModificar;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Existencia_Valor_Campo {

    public static boolean verificarExistenciaValorCampo(ArrayList<String> tokens) {
        boolean existeValorCampo = false;
        boolean correctaUnion = false;
        ArrayList<String> campos = new ArrayList<>();
        File archivo1 = new File("Tablas/" + tokens.get(1) + ".CSV");
        File archivo2 = new File("Tablas/" + tokens.get(2) + ".CSV");
        String[] arregloCampos = null;
        String[] arregloCampos2 = null;
        String valorComun = "";
        ArrayList<String> nuevoarregloCampos = new ArrayList<String>();

        try {
            CsvReader lector1 = new CsvReader(new FileReader(archivo1), ';');
            //CsvReader lector2 = new CsvReader(new FileReader(archivo2), ';');

            lector1.readHeaders();

            /// buscar si el valor del archivo1 es igual al archivo2
            while (lector1.readRecord()) {
                CsvReader lector2 = new CsvReader(new FileReader(archivo2), ';');
                lector2.readHeaders();
                while (lector2.readRecord()) {
                    if (lector1.get(tokens.get(4)).equals(lector2.get(tokens.get(4)))) {
                        valorComun = lector1.get(tokens.get(4));
                        existeValorCampo = true;
                    }
                }
                lector2.close();
            }

            lector1.close();
            correctaUnion = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (existeValorCampo == true) {
            return true;
        } else {
            errores += "El valor del campo '" + tokens.get(4) + "' no coinciden en las dos tablas"+"\n";
            //System.out.println("El valor del campo '" + tokens.get(4) + "' no coinciden en las dos tablas");
            return false;
        }
    }
}
