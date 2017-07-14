/*   0        1        2    3    4   5     6     7   8 
ACTUALIZAR REGISTRO tabla CLAVE ab CAMPO nombre POR bh 
*/
package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ModificarRegistro;
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

public class Modificar_Registro {

    public static boolean modificarRegistro(ArrayList<String> tokens) {
        boolean clave = false, claveRepeat = false;
        String cabezeraClave = "";
        try {
            File archivo = new File("ArchivoMetaBD/MetaBD.CSV");
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');
            lector.readHeaders();
            while (lector.readRecord()) {
                if (tokens.get(2).equals(lector.get("Nombre_Tabla"))) {
                    cabezeraClave = lector.get("Campo_Clave");
                }
                if (tokens.get(6).equals(lector.get("Campo_Clave"))) {
                    clave = true;
                }
            }
            lector.close();

            File tabla = new File("Tablas/" + tokens.get(2) + ".CSV");
            File tablaAux = new File(tokens.get(2) + "Aux.CSV");
            CsvReader lectorTabla = new CsvReader(new FileReader(tabla), ';');
            CsvReader lectorTabla2 = new CsvReader(new FileReader(tabla), ';');
            CsvWriter salidaTablaAux = new CsvWriter(new FileWriter(tablaAux), ';');
            lectorTabla2.readHeaders();
            if (clave) {
                while (lectorTabla2.readRecord()) {
                    if (tokens.get(8).equals(lectorTabla2.get(cabezeraClave))) {
                        claveRepeat = true;
                    }
                }
            }
            if (!claveRepeat) {
                lectorTabla.readHeaders();
                String[] arrayHeaders = lectorTabla.getHeaders();
                salidaTablaAux.writeRecord(arrayHeaders);
                while (lectorTabla.readRecord()) {
                    if (tokens.get(4).equals(lectorTabla.get(cabezeraClave))) {
                        for (int i = 0; i < lectorTabla.getColumnCount(); i++) {
                            if (lectorTabla.getIndex(tokens.get(6)) == i) {
                                salidaTablaAux.write(tokens.get(8));
                            } else {
                                salidaTablaAux.write(lectorTabla.get(i));
                            }
                        }
                        salidaTablaAux.endRecord();
                    } else {
                        salidaTablaAux.writeRecord(lectorTabla.getValues());
                    }
                }
                lectorTabla.close();
                salidaTablaAux.close();
                CsvWriter salidaTabla = new CsvWriter(new FileWriter(tabla), ';');
                CsvReader lectorTablaAux = new CsvReader(new FileReader(tablaAux), ';');
                while (lectorTablaAux.readRecord()) {
                    salidaTabla.writeRecord(lectorTablaAux.getValues());
                }
                salidaTabla.close();
                lectorTablaAux.close();
                tablaAux.delete();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ModificarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModificarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (claveRepeat) {
            errores += "El campo ingresado coincide con un campo clave ingresado anteriormente\n";
            //System.out.println("El campo ingresado coincide con un campo clave ingresado anteriormente!");
            return false;
        } else {
            return true;
        }
    }
}
