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

public class Existencia_Campos_Unir {

    public static int verificarExistenciaCampo(ArrayList<String> tokens, int pos) {
        boolean existencia = false;
        File tabla1 = new File("Tablas/" + tokens.get(1) + ".CSV");
        File tabla2 = new File("Tablas/" + tokens.get(2) + ".CSV");

        try {
            CsvReader lector = new CsvReader(new FileReader(tabla1), ';');
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
        
        if (existencia) {
            existencia = false;
            try {
                CsvReader lector = new CsvReader(new FileReader(tabla2), ';');
                lector.readHeaders();
                String[] arrayHeaders = lector.getHeaders();
                for (String arrayHeader : arrayHeaders) {
                    if (arrayHeader.equals(tokens.get(pos))) {
                        existencia = true;
                    }
                }
                lector.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(ValidarExistenciaCampoSeleccionado.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ValidarExistenciaCampoSeleccionado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (existencia)
                return 0;
            
            return 2;
        }

        return 1;
    }
}
