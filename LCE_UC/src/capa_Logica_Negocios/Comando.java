package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Comando {

    protected ProccesManager tipoProceso;

    public Comando(ProccesManager tipoProceso) {
        this.tipoProceso = tipoProceso;
    }

    public boolean validarExistenciaTabla(int index) {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la tabla en donde se va a crear el registro exista
        //CREAR REGISTRO nombre_tabla VALOR vCampo1 , vCampo2 ,... , vCampoN
        boolean encontrado = false;

        try {
            File archivo = new File("ArchivoMetaBD/MetaBD.csv");
            CsvReader lector = new CsvReader(new FileReader(archivo), ';');
            lector.readHeaders();
            while (lector.readRecord()) {
                if ("0".equals(lector.get("Borrado_Logico")) && tokens.get(index).equals(lector.get("Nombre_Tabla"))) {
                    encontrado = true;
                }
            }
            lector.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ValidarExistenciaTablaEliminar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!encontrado)
            errores += "Mensaje -> No existe una tabla con el nombre: " + tokens.get(index)+"\n";
            //System.out.println("Mensaje -> No existe una tabla con el nombre: " + tokens.get(index));
        
        return encontrado;
    }
    public abstract boolean ejecutar();
    

}
