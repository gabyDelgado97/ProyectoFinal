package capa_de_datos;

import capa_Logica_Negocios.ValidarExistenciaTablaModificar;
import capa_Logica_Negocios.ValidarTablaVaciaModificar;
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

public class Modificar_Tabla {

    public static boolean proceso_modificar_tabla(ArrayList<String> tokens) {
        // cambiar un campo por el nuevo en la tabla 

        boolean correctaModificacionTabla = false;
        ArrayList<String> campos = new ArrayList<>();
        File archivo = new File("Tablas/" + tokens.get(2) + ".CSV");
        String[] arregloCampos = null;
        String camposNuevos = "";
        ArrayList<String> nuevoarregloCampos = new ArrayList<String>();

        try {
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');

            lector.readHeaders();
            arregloCampos = lector.getHeaders();
            for (int i = 0; i < arregloCampos.length; i++) {
                //System.out.println("CAMPOS->"+arregloCampos[i]);
                if (arregloCampos[i].equals(tokens.get(4))) {
                    arregloCampos[i] = tokens.get(4);
                    nuevoarregloCampos.add(tokens.get(6));
                } else {
                    nuevoarregloCampos.add(arregloCampos[i]);
                }
            }
            lector.close();

            CsvWriter escritor = new CsvWriter(new FileWriter(archivo), ';');
            for (int i = 0; i < nuevoarregloCampos.size(); i++) {
                // escribir en la tabla las cabeceras
                escritor.write(nuevoarregloCampos.get(i));
                //sacar en un String los campos separados por coma
                if (i < nuevoarregloCampos.size() - 1 && i >= 0) {
                    camposNuevos += nuevoarregloCampos.get(i).concat(",");
                } else if (i == nuevoarregloCampos.size() - 1 && i >= 0) {
                    camposNuevos += nuevoarregloCampos.get(i);
                }
            }

            escritor.endRecord();
            escritor.close();
            correctaModificacionTabla = true;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            File archivoMetaBD = new File("ArchivoMetaBD/MetaBD.CSV");
            File archivoAux = new File("ArchivoMetaBD/MetaBDAux.CSV");
            CsvWriter salidaMetaAux = new CsvWriter(new FileWriter(archivoAux), ';');
            CsvReader lector = new CsvReader(new FileReader(archivoMetaBD), ';');
            lector.readHeaders();
            String[] arrayHeaders = lector.getHeaders();
            salidaMetaAux.writeRecord(arrayHeaders);
            // copiar de METABD A METABD AUX
            while (lector.readRecord()) {
                // si el campo a por el que vamos a cambiar es el mismo del campo clave entonces tambien cambiamos el campo clave 
                if (tokens.get(2).equals(lector.get("Nombre_Tabla")) && tokens.get(4).equals(lector.get("Campo_Clave"))) {
                    salidaMetaAux.write(lector.get("Borrado_Logico"));

                    salidaMetaAux.write(lector.get("Nombre_Tabla"));
                    salidaMetaAux.write(lector.get("Numero_Campos"));
                    salidaMetaAux.write(camposNuevos);

                    salidaMetaAux.write(tokens.get(6));
                    salidaMetaAux.write(lector.get("Longitud_Campos"));
                    salidaMetaAux.endRecord();
                } // si el campo por el que vamos a cambiar no es el mismo del campo clave
                else if (tokens.get(2).equals(lector.get("Nombre_Tabla"))) {
                    salidaMetaAux.write(lector.get("Borrado_Logico"));

                    salidaMetaAux.write(lector.get("Nombre_Tabla"));
                    salidaMetaAux.write(lector.get("Numero_Campos"));
                    salidaMetaAux.write(camposNuevos);

                    salidaMetaAux.write(lector.get("Campo_Clave"));
                    salidaMetaAux.write(lector.get("Longitud_Campos"));
                    salidaMetaAux.endRecord();
                } else {
                    salidaMetaAux.writeRecord(lector.getValues());
                }
            }

            salidaMetaAux.close();
            lector.close();

            // copiar de METABDAUX A METABD
            CsvWriter salidaMeta = new CsvWriter(new FileWriter(archivoMetaBD), ';');
            CsvReader lectorAux = new CsvReader(new FileReader(archivoAux), ';');
            while (lectorAux.readRecord()) {
                salidaMeta.writeRecord(lectorAux.getValues());
            }
            salidaMeta.close();
            lectorAux.close();
            archivoAux.delete();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaModificar.class.getName()).log(Level.SEVERE, null, ex);
            correctaModificacionTabla = false;
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaModificar.class.getName()).log(Level.SEVERE, null, ex);
            correctaModificacionTabla = false;
        }

        return correctaModificacionTabla;

    }

}
