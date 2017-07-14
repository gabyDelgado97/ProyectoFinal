/*  0     1   2      3      4
INSERTAR EN tabla1 VALORES gab,yo
 */
package capa_de_datos;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crear_Registro {

    public static boolean escribirCrearRegistro(ArrayList<String> tokens) throws Exception {

        boolean escrito = true;

        try {
            File archivo_tabla = new File("Tablas/" + tokens.get(2) + ".CSV");  //archivo tabla
            CsvWriter escritor = new CsvWriter(new FileWriter(archivo_tabla, true), ';');
            CsvReader lectorTabla = new CsvReader(new FileReader(archivo_tabla), ';');
            lectorTabla.readHeaders();
            String[] arrayHeaders = lectorTabla.getHeaders();

            File archivo = new File("ArchivoMetaBD/MetaBD.CSV");  //archivo metadatos
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');
            ArrayList<String> campoEncripado = new ArrayList<String>();
            String campos1[] = new String[100];
            lector.readHeaders();
            while (lector.readRecord()) {
                if (tokens.get(2).equals(lector.get("Nombre_Tabla"))) {
                    campoEncripado.add(lector.get("Encriptado"));
                }
            }
            String campos[] = (campoEncripado.get(0).split(","));//todas las longitudes les quito la coma
            campos1 = campos;
            for (int k = 0; k < campos1.length; k++) {
                System.out.println("encrp: " + campos1[k]);
            }

            lector.close();
            int pos[] = new int[100000];
            int cont4 = 0;
            int validar = 0;
            for (int i = 0; i < arrayHeaders.length; i++) {
                if (cont4 < campos1.length) {
                    if (arrayHeaders[i].equals(campos1[cont4])) {
                        pos[cont4] = i;
                        cont4++;
                        validar = 1;
                    }
                }

            }
            for (int h = 0; h < 10; h++) {
                System.out.println("pos: " + pos[h]);
            }

            Encriptar encriptar = new Encriptar();
            String campos_ingresados = tokens.get(4), campo = "", textoEncriptado = "";
            int cont2 = 0, aux = 0, cont3 = 0;
            campos_ingresados += ",";
            System.out.println("campos ingres: " + campos_ingresados);
            for (int i = 0; i < campos_ingresados.length(); i++) {
                if (campos_ingresados.charAt(i) == ',') {
                    if (validar == 1) {
                        if ((cont2 == pos[cont3] || (cont2 - 1) == pos[cont3]) /*&& aux == 0*/) {
                            System.out.println("tiene que encriptarse: " + campo);
                            textoEncriptado = encriptar.encrypt(campo);
                            escritor.write(textoEncriptado);
                            //aux = 1;
                            cont3++;
                        } else {
                            escritor.write(campo);
                        }
                    } else if (campos1[cont3].equals("T")) {
                        System.out.println("tiene que encriptarse: " + campo);
                        textoEncriptado = encriptar.encrypt(campo);
                        escritor.write(textoEncriptado);
                        //aux = 1;
                        cont3++;
                    } else if (campos1[cont3].equals("F")) {
                        escritor.write(campo);
                        cont3++;
                    }

                    cont2++;
                    campo = "";
                    textoEncriptado = "";
                } else {
                    campo += campos_ingresados.charAt(i);
                }
            }
            escritor.write(campo);
            escritor.endRecord();
            escritor.flush();
            escritor.close();

        } catch (IOException ex) {
            Logger.getLogger(Crear_Registro.class.getName()).log(Level.SEVERE, null, ex);
            escrito = false;
        }

        return escrito;
    }
}
