package capa_de_datos;

import capa_Logica_Negocios.ValidarExistenciaCampoSeleccionado;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Existencia_Campo {

    public static boolean verificarExistenciaCampo(ArrayList<String> tokens, int pos) {
        boolean existencia = false;
        if (tokens.size() == 5 || tokens.size() == 3) {
            existencia = true;
        } else {
            File archivo = new File("Tablas/" + tokens.get(2) + ".CSV");
            try {
                CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                lector.readHeaders();
                String[] arrayHeaders = lector.getHeaders();
                for (int i = 0; i < arrayHeaders.length; i++) {
                    if (arrayHeaders[i].equals(tokens.get(pos))) {
                        existencia = true;
                    }
                }
                lector.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ValidarExistenciaCampoSeleccionado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ValidarExistenciaCampoSeleccionado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return existencia;
    }

}
