/*
BORRAR REGISTRO tabla CLAVE gh
 */
package capa_de_datos;

import capa_Logica_Negocios.ValidarExistenciaTablaEliminarRegistro;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eliminar_Registro {

    public static boolean proceso_eliminar_registro(ArrayList<String> tokens) {
        boolean correctaEliminacionRegistro = false;
        //PROCESO PARA ELIMINAR UN REGISTRO DE LA TABLA

        ArrayList<String> campos = new ArrayList<>();
        File archivoMetaBD = new File("ArchivoMetaBD/METABD.CSV");
        File archivoTabla = new File("Tablas/" + tokens.get(2) + ".CSV");
        File archivoAux = new File("ArchivoMetaBD/MetaBDAux.CSV");
        String campoClaveMetaBd = "";

        try {
            CsvReader lectorMetaBd = new CsvReader(new FileReader(archivoMetaBD), ';');
            lectorMetaBd.readHeaders();
            while (lectorMetaBd.readRecord()) {
                // almacenar en una variable el campo clave del METABD 
                if (lectorMetaBd.get("Nombre_Tabla").equals(tokens.get(2))) {
                    campoClaveMetaBd = lectorMetaBd.get("Campo_Clave");
                }
            }

            CsvWriter salidaMetaAux = new CsvWriter(new FileWriter(archivoAux), ';');
            CsvReader lectorTabla = new CsvReader(new FileReader(archivoTabla), ';');
            lectorTabla.readHeaders();

            String[] cabecerasTabla = lectorTabla.getHeaders();
            // escribir las cabeceras en el archivoAux
            salidaMetaAux.writeRecord(cabecerasTabla);
            // buscar en las cabeceras de la  tabla cual es el campo clave
            for (int i = 0; i < cabecerasTabla.length; i++) {
                if (cabecerasTabla[i].equals(campoClaveMetaBd)) {
                    // copiar en el archivoAux los valores de los registros que no se eliminan
                    while (lectorTabla.readRecord()) {
                        if (!lectorTabla.get(campoClaveMetaBd).equals(tokens.get(4))) {
                            salidaMetaAux.writeRecord(lectorTabla.getValues());
                            correctaEliminacionRegistro = true;
                        }
                    }
                }
            }
            salidaMetaAux.close();
            lectorTabla.close();
            lectorMetaBd.close();

            // COPIAR DESDE EL ARCHIVOAUX A LA TABLA
            CsvWriter salidaTabla = new CsvWriter(new FileWriter(archivoTabla), ';');
            CsvReader lectorAux = new CsvReader(new FileReader(archivoAux), ';');
            while (lectorAux.readRecord()) {
                salidaTabla.writeRecord(lectorAux.getValues());
            }

            salidaTabla.close();
            lectorAux.close();
            archivoAux.delete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correctaEliminacionRegistro;
    }
}
