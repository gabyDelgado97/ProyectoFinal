package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ValidarCampoClave;
import capa_Logica_Negocios.ValidarExistenciaTablaEliminar;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Existencia_Campo_Clave {

    public static boolean verificarNoExistenciaCampoClave(ArrayList<String> tokens) {
        String campo_clave = "";

        try {
            File archivo_metaBD = new File("ArchivoMetaBD/MetaBD.CSV");
            CsvReader lector = new CsvReader(new FileReader(archivo_metaBD), ';');
            lector.readHeaders();
            while (lector.readRecord()) {
                if (tokens.get(2).equals(lector.get("Nombre_Tabla"))) {
                    campo_clave = lector.get("Campo_Clave");
                }
            }
            lector.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean clave_exist = true;
        String clave_ingresada = "", campos_ingresados = tokens.get(4);

        try {

            File archivo_tabla = new File("Tablas/" + tokens.get(2) + ".CSV");
            CsvReader lector = new CsvReader(new FileReader(archivo_tabla), ';');
            lector.readHeaders();

            //obtener la posicion del campo clave
            String[] headers = lector.getHeaders();
            int index_clave = 0;
            for (int i = 0; i < headers.length; i++) {
                if (campo_clave.equals(headers[i])) {
                    index_clave = i;
                }
            }

            //obtener la clave ingresada por el usuario
            int cont = 0;
            for (int i = 0; i < campos_ingresados.length(); i++) {
                if (campos_ingresados.charAt(i) == ',') {
                    cont++;
                }
                if (cont == index_clave && campos_ingresados.charAt(i) != ',') {
                    clave_ingresada += campos_ingresados.charAt(i);
                }
            }

            //comparar que la clave que no exista
            while (lector.readRecord()) {
                if (clave_ingresada.equals(lector.get(campo_clave))) {
                    clave_exist = false;
                }
            }
            lector.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarCampoClave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarCampoClave.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!clave_exist) {
            errores += "Error -> Ya existe un registro con el campo clave ingresado. Campo clave ingresado de la Tabla '" + tokens.get(2) + "' es " + clave_ingresada+"\n";
            //System.out.println("Error -> Ya existe un registro con el campo clave ingresado. Campo clave ingresado de la Tabla '" + tokens.get(2) + "' es " + clave_ingresada);
            return false;
        }

        return true;
    }
}
