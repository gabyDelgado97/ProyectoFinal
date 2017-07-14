/*   0       1      2     3    4     5      6   7  8      
SELECCIONAR DE Original DONDE DNI ORDENADO asc VER 9
SELECCIONAR DE Original DONDE DNI ORDENADO desc
SELECCIONAR DE tabla DONDE id = bh
SELECCIONAR DE Original   //mostrar todos los registros
 */
package capa_de_datos;

import capa_Logica_Negocios.SeleccionarTabla;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seleccionar_Tabla {

    public static String seleccionar;
    private static long time_start, time_end;

    public static boolean buscarSeleccionarTabla(ArrayList<String> parametros) throws ParseException {
        boolean aux = false;
        //aqui hay que cambiar para ordenar el primero
        if (parametros.size() == 9 || parametros.size() == 7) {//SELECCIONAR DE tabla DONDE nombre_campo ORDENADO asc/desc VER número_registros
            if (parametros.size() == 9) { //SELECCIONAR DE Original DONDE DNI ORDENADO asc VER 9
                String seleccion = "";
                int pos = 0;
                int cont = 0;
                try {
                    File archivo = new File("ArchivoMetaBD/MetaBD.CSV");  //archivo metadatos
                    CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                    lector.readHeaders();
                    ArrayList<String> campos = new ArrayList<String>();
                    String campos1[] = new String[100];
                    while (lector.readRecord()) {
                        if (parametros.get(2).equals(lector.get("Nombre_Tabla"))) {
                            campos.add(lector.get("Campos"));
                        }
                    }
                    System.out.println("campos: "+campos);
                    lector.close();
                    String campos3[] = (campos.get(0).split(","));//todas las longitudes les quito la coma
                    campos1 = campos3;

                    for (int i = 0; i < campos1.length; i++) {
                        if (campos1[i].equals(parametros.get(4))) {
                            pos = i;
                        }
                    }
                    
                    OrdBalanceada balanceada = new OrdBalanceada();
                    File archivoNuevo = new File("Tablas/" + parametros.get(2) + ".CSV");
                    time_start = System.currentTimeMillis();
                    balanceada.sort(pos, String.valueOf(archivoNuevo), String.valueOf(parametros.get(6)));
                    time_end = System.currentTimeMillis();
                    System.out.println(String.valueOf((time_end-time_start))+" milisegundos");
                    CsvReader lectorNuevo = new CsvReader(new FileReader(archivoNuevo), ';');
                    lectorNuevo.readHeaders();
                    while (lectorNuevo.readRecord() && cont < Integer.parseInt(parametros.get(8))) {
                        cont++;
                        seleccion += "Registro  " + cont + ": " + Arrays.toString(lectorNuevo.getValues()) + "\n";
                        aux = true;
                    }
                    seleccionar = seleccion;
                    lectorNuevo.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (parametros.size() == 7 || parametros.get(6).equals("asc") || parametros.get(6).equals("desc")) { //SELECCIONAR DE Original DONDE DNI ORDENADO desc
                String seleccion = "";
                int pos = 0;
                int cont = 0;
                try {
                    File archivo = new File("ArchivoMetaBD/MetaBD.CSV");  //archivo metadatos
                    CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                    lector.readHeaders();
                    ArrayList<String> campos = new ArrayList<String>();
                    String campos1[] = new String[100];
                    while (lector.readRecord()) {
                        if (parametros.get(2).equals(lector.get("Nombre_Tabla"))) {
                            campos.add(lector.get("Campos"));
                        }
                    }
                    System.out.println("campos: "+campos);
                    lector.close();
                    String campos3[] = (campos.get(0).split(","));//todas las longitudes les quito la coma
                    campos1 = campos3;

                    for (int i = 0; i < campos1.length; i++) {
                        if (campos1[i].equals(parametros.get(4))) {
                            pos = i;
                        }
                    }
                    OrdBalanceada balanceada = new OrdBalanceada();
                    File archivoNuevo = new File("Tablas/" + parametros.get(2) + ".CSV");
                    time_start = System.currentTimeMillis();
                    balanceada.sort(pos, String.valueOf(archivoNuevo), String.valueOf(parametros.get(6)));
                    time_end = System.currentTimeMillis();
                    System.out.println(String.valueOf((time_end-time_start))+" milisegundos");
                    CsvReader lectorNuevo = new CsvReader(new FileReader(archivoNuevo), ';');
                    lectorNuevo.readHeaders();
                    while (lectorNuevo.readRecord()) {
                        cont++;
                        seleccion += "Registro " + cont + ": " + Arrays.toString(lectorNuevo.getValues()) + "\n";
                        aux = true;
                    }
                    seleccionar = seleccion;
                    lectorNuevo.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int pos = 0;
                String seleccion = "";
                try {
                    File archivo = new File("Tablas/" + parametros.get(2) + ".CSV");
                    CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                    lector.readHeaders();
                    String[] arrayHeaders = lector.getHeaders();
                    for (int i = 0; i < arrayHeaders.length; i++) {
                        if (arrayHeaders[i].equals(parametros.get(4))) {
                            pos = i;
                        }
                    }
                    while (lector.readRecord()) {
                        if (lector.get(pos).equals(parametros.get(6))) {
                            seleccion += Arrays.toString(lector.getValues()) + "\n";
                            aux = true;
                        }
                    }
                    seleccionar = seleccion;
                    lector.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (parametros.size() == 5) {  //SELECCIONAR DE tabla ORDENADO asc/desc campo clave
            int pos = 0;
            String seleccion = "";
            try {
                File archivo_metaBD = new File("ArchivoMetaBD/MetaBD.CSV");
                CsvReader lector1 = new CsvReader(new FileReader(archivo_metaBD), ';');
                String campo_clave = "";
                lector1.readHeaders();
                while (lector1.readRecord()) {
                    if (parametros.get(2).equals(lector1.get("Nombre_Tabla"))) {
                        campo_clave = lector1.get("Campo_Clave");
                    }
                }
                lector1.close();
                File archivo = new File("Tablas/" + parametros.get(2) + ".CSV");
                CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                int cont = 0;
                lector.readHeaders();
                String[] headers = lector.getHeaders();
                for (int i = 0; i < headers.length; i++) {
                    if (campo_clave.equals(headers[i])) {
                        pos = i;
                    }
                }
                OrdBalanceada natural = new OrdBalanceada();
                time_start = System.currentTimeMillis();
                natural.sort(pos, String.valueOf(archivo), String.valueOf(parametros.get(4)));
                time_end = System.currentTimeMillis();
                System.out.println(String.valueOf((time_end-time_start))+" milisegundos");
                lector.readHeaders();
                while (lector.readRecord()) {
                    cont++;
                    seleccion += "Registro " + cont + ": " + Arrays.toString(lector.getValues()) + "\n";
                    aux = true;
                }
                seleccionar = seleccion;
                lector.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (parametros.size() == 3) {  //SELECCIONAR DE tabla
            int cont = 0;
            String seleccion = "";
            try {
                File archivo = new File("Tablas/" + parametros.get(2) + ".CSV");
                CsvReader lector = new CsvReader(new FileReader(archivo), ';');
                lector.readHeaders();
                String[] arrayHeaders = lector.getHeaders();
                //seleccion += arrayHeaders + ", ";
                while (lector.readRecord()) {
                    cont++;
                    seleccion += "Registro " + cont + ": " + Arrays.toString(lector.getValues()) + "\n";
                    aux = true;
                }
                seleccionar = seleccion;
                lector.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return aux;
    }
}