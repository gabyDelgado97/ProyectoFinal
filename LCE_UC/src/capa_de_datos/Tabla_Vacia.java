package capa_de_datos;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tabla_Vacia {
    public static boolean verificarTablaVacia(ArrayList<String> tokens,int pos) {
        boolean vacio=true;
        File archivo =new File("Tablas/"+tokens.get(pos)+".CSV");
        try {
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');
            lector.readHeaders();
            while (lector.readRecord()) {
                vacio=false;
            }
            lector.close();
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(ValidarTablaVaciaModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        if(vacio)
            return true;
        
        return false;
    }
    
}
