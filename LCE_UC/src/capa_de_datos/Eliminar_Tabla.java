/*
ELIMINAR TABLA tabla1
 */
package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ValidarExistenciaTablaEliminar;
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

public class Eliminar_Tabla {
  public static boolean escribirEliminarTabla(ArrayList<String> tokens) {
        //ELIMINAR TABLA nombre_tabla
        File archivoMetaBD = new File("ArchivoMetaBD/MetaBD.CSV");
        File archivoMetaBDAux = new File("ArchivoMetaBD/MetaBDAux.CSV");

        try {
            CsvReader lector = new CsvReader(new FileReader(archivoMetaBD), ';');
            CsvWriter escritor = new CsvWriter(new FileWriter(archivoMetaBDAux), ';');

            lector.readHeaders();
            String[] headers = lector.getHeaders();
            escritor.writeRecord(headers);

            while (lector.readRecord()) {
                if (tokens.get(2).equals(lector.get("Nombre_Tabla"))) {
                    escritor.write("1");
                    for (int i = 1; i < headers.length; i++) {
                        escritor.write(lector.get(i));
                    }
                    escritor.endRecord();
                } else {
                    String[] values = lector.getValues();
                    escritor.writeRecord(values);
                }
            }
            lector.close();
            escritor.flush();
            escritor.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            CsvReader lector = new CsvReader(new FileReader(archivoMetaBDAux), ';');
            CsvWriter escritor = new CsvWriter(new FileWriter(archivoMetaBD), ';');

            lector.readHeaders();
            escritor.writeRecord(lector.getHeaders());

            while (lector.readRecord()) {
                escritor.writeRecord(lector.getValues());
            }
            lector.close();
            escritor.flush();
            escritor.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        archivoMetaBDAux.delete();
        
        String directorio = "Tablas/" + tokens.get(2) + ".CSV";
        File archivo = new File(directorio);

        boolean eliminado = archivo.delete();

        if (!eliminado) {
            errores += "Error -> No se puedo eliminar la Tabla '" + tokens.get(2) + "' debido a un error interno. Intente de nuevo"+"\n";
            //System.out.println("Error -> No se puedo eliminar la Tabla '" + tokens.get(2) + "' debido a un error interno. Intente de nuevo");
        }

        return eliminado;
    }
}
