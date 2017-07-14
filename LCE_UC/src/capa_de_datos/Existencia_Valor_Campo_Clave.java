/*   0       1        2     3    4    5   6     7   8
ACTUALIZAR REGISTRO tabla CLAVE yo CAMPO id POR bh 
*/
package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ValidarTablaVaciaEliminarRegistro;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Existencia_Valor_Campo_Clave {

    public static boolean verificarExistenciaValorCampoClave(ArrayList<String> tokens) {
        boolean existe = false;
        ArrayList<String> campos = new ArrayList<>();
        File archivoMetaBD = new File("ArchivoMetaBD/METABD.CSV");
        File archivoTabla = new File("Tablas/" + tokens.get(2) + ".CSV");
        String campoClaveMetaBd = "";

        try {
            CsvReader lectorMetaBd = new CsvReader(new FileReader(archivoMetaBD), ';');  //metadatos
            lectorMetaBd.readHeaders();
            while (lectorMetaBd.readRecord()) {
                // almacenar en una variable el campo clave del METABD 
                if (lectorMetaBd.get("Nombre_Tabla").equals(tokens.get(2))) {
                    campoClaveMetaBd = lectorMetaBd.get("Campo_Clave");  //clave campo
                }
            }

            CsvReader lectorTabla = new CsvReader(new FileReader(archivoTabla), ';');  //archivo tabla
            lectorTabla.readHeaders();
            String[] cabecerasTabla = lectorTabla.getHeaders();  //todos los campos
            // buscar en las cabeceras de la  tabla cual es el campo clave
            for (int i = 0; i < cabecerasTabla.length; i++) {
                if (cabecerasTabla[i].equals(campoClaveMetaBd)) {
                    // buscar en la tabla  que el valor del campo clave exista
                    while (lectorTabla.readRecord()) {
                        if (lectorTabla.get(campoClaveMetaBd).equals(tokens.get(4))) {
                            existe = true;
                        }
                    }
                }
            }
            lectorTabla.close();
            lectorMetaBd.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarTablaVaciaEliminarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarTablaVaciaEliminarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (existe) {
            return true;
        } else {
            errores += "El valor '" + tokens.get(4) + "' del campo clave '" + campoClaveMetaBd + "' no existe en la tabla '" + tokens.get(2) + "'" + "\n";
            //System.out.println("El valor '" + tokens.get(4) + "' del campo clave '" + campoClaveMetaBd + "' no existe en la tabla '" + tokens.get(2) + "'");
            return false;
        }
    }
}
