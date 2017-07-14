/*0     1     2     3         4       5   6   7       8     9        10
CREAR TABLA tabla1 CAMPOS id,nombre CLAVE id LONGITUD 3,4 ENCRIPTADO id
 */
package capa_de_datos;

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


public class Crear_Tabla {

    public static boolean escribirCrearTabla(ArrayList<String> parametros) {
        boolean aux = false;
        try {
            File archivo = new File("ArchivoMetaBD/MetaBD.CSV");
            File archivoAux = new File("ArchivoMetaBD/MetaBDAux.CSV");
            CsvWriter salidaMetaAux = new CsvWriter(new FileWriter(archivoAux), ';');
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');
            lector.readHeaders();
            String[] arrayHeaders = lector.getHeaders();
            salidaMetaAux.writeRecord(arrayHeaders);
            while (lector.readRecord()) {
                if ("1".equals(lector.get("Borrado_Logico")) && aux == false) {
                    salidaMetaAux.write("0");
                    salidaMetaAux.write(parametros.get(2));
                    String campos[] = parametros.get(4).split(",");
                    salidaMetaAux.write(String.valueOf(campos.length));
                    salidaMetaAux.write(parametros.get(4));
                    salidaMetaAux.write(parametros.get(6));
                    salidaMetaAux.write(parametros.get(8));
                    salidaMetaAux.write(parametros.get(10));
                    salidaMetaAux.endRecord();
                    aux = true;
                } else {
                    salidaMetaAux.writeRecord(lector.getValues());
                }
            }
            if (aux == false) {
                salidaMetaAux.write("0");
                salidaMetaAux.write(parametros.get(2));
                String campos[] = parametros.get(4).split(",");
                salidaMetaAux.write(String.valueOf(campos.length));
                salidaMetaAux.write(parametros.get(4));
                salidaMetaAux.write(parametros.get(6));
                salidaMetaAux.write(parametros.get(8));
                salidaMetaAux.write(parametros.get(10));
                
                salidaMetaAux.endRecord();
                aux = true;
            }
            salidaMetaAux.close();
            lector.close();
            CsvWriter salidaMeta = new CsvWriter(new FileWriter(archivo), ';');
            CsvReader lectorAux = new CsvReader(new FileReader(archivoAux), ';');
            while (lectorAux.readRecord()) {
                salidaMeta.writeRecord(lectorAux.getValues());
            }
            salidaMeta.close();
            lectorAux.close();
            archivoAux.delete();
            File tabla = new File("Tablas/" + parametros.get(2) + ".csv");
            CsvWriter salidaTabla = new CsvWriter(new FileWriter(tabla), ';');
            String campos[] = parametros.get(4).split(",");
            for (int i = 0; i < campos.length; i++) {
                salidaTabla.write(campos[i]);
            }
            salidaTabla.endRecord();
            salidaTabla.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aux;
    }
}
