package capa_de_datos;

import capa_Logica_Negocios.ValidarExistenciaTablaEliminar;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tabla_Script {
    
    private static int countColumn;
    
    public void crearConScript(String nombreArchivo,String nombreTabla) throws FileNotFoundException, IOException {
        /*campoClave;campo2,campo3,campo4,campo5;6,5,5,10 */
        String numCampos = "codigo,sector,nombre,casado,nacimiento", campoClave = "codigo", camposEncriptados = "F,F,F,F,F", longitudesCampos = "6,5,5,5,10";
        CsvReader usuarios_import = new CsvReader(new FileReader(nombreArchivo+".csv"), ',');
        countColumn = usuarios_import.getHeaderCount();
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
                    salidaMetaAux.write(nombreTabla);
                    salidaMetaAux.write(String.valueOf(5));
                    salidaMetaAux.write(numCampos);
                    salidaMetaAux.write(campoClave);
                    salidaMetaAux.write(longitudesCampos);
                    salidaMetaAux.write(camposEncriptados);
                    salidaMetaAux.endRecord();
                    aux = true;
                } else {
                    salidaMetaAux.writeRecord(lector.getValues());
                }
            }
            if (aux == false) {
                salidaMetaAux.write("0");
                salidaMetaAux.write(nombreTabla);
                salidaMetaAux.write(String.valueOf(5));
                salidaMetaAux.write(numCampos);
                salidaMetaAux.write(campoClave);
                salidaMetaAux.write(longitudesCampos);
                salidaMetaAux.write(camposEncriptados);
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
            File archivo_tabla = new File("Tablas/" + nombreTabla + ".csv");
            CsvWriter escritor = new CsvWriter(new FileWriter(archivo_tabla, true), ';');
            while (usuarios_import.readRecord()) {
                escritor.writeRecord(usuarios_import.getValues());
            }
            usuarios_import.close();
            escritor.endRecord();
            escritor.flush();
            escritor.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
