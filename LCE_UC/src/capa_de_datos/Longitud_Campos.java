/*   0       1        2     3    4    5   6     7   8
ACTUALIZAR REGISTRO tabla CLAVE yo CAMPO id POR bh 
 */
package capa_de_datos;

import static prueba.Prueba.errores;
import capa_Logica_Negocios.ValidarLongitudCampo;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Longitud_Campos {

    public static int verificarLongitudCampos(ArrayList<String> tokens, int longitud, String texto) {
        boolean comprobacion = false;
        int longitudCampo = -1;
        File archivo = new File("ArchivoMetaBD/MetaBD.CSV");  //metadatos
        File archivoTabla = new File("Tablas/" + tokens.get(2) + ".CSV");  //archivo tabla
        String campoClaveMetaBd = "";
        ArrayList<String> longCampos = new ArrayList<String>();
        String longitudes[] = new String[100];
        String campos1[] = new String[100];
        int posicionCampos = 0;
        try {
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');  //metadatos
            lector.readHeaders();
            while (lector.readRecord()) {
                // almacenar en una variable el campo clave del METABD 
                if (lector.get("Nombre_Tabla").equals(tokens.get(2))) {
                    campoClaveMetaBd = lector.get("Campo_Clave");  //clave campo
                    longCampos.add(lector.get("Longitud_Campos")); //guardo todas las lontigudes
                    longitudes = (longCampos.get(0).split(","));//todas las longitudes les quito la coma
                    campos1 = longitudes; //guardo en el nuevo

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
                            posicionCampos = i;
                        }
                    }
                }
            }
            lectorTabla.close();
            lector.close();

            if (posicionCampos != -1) {
                if (longitud <= Integer.parseInt(campos1[posicionCampos])) {
                    comprobacion = true;
                } else {
                    longitudCampo = Integer.parseInt(campos1[posicionCampos]);
                }
                
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarLongitudCampo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarLongitudCampo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (comprobacion) {
            return 0;
        } else {
            errores += "El campo '" + texto + "' ingresado excede la longitud de '" + longitudCampo + "' del campo '" + tokens.get(6) + "'" + "\n";
            //System.out.println("El campo '"+texto+"' ingresado excede la longitud de '"+longitudCampo+"' del campo '"+tokens.get(6)+"'");
            return 1;
        }
    }
}
