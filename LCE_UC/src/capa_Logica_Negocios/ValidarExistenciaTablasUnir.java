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

public class ValidarExistenciaTablasUnir extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablasUnir instancia = null;

    private ValidarExistenciaTablasUnir(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablasUnir getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablasUnir(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean validarExistenciaTabla(int index) {
        ArrayList<String> tokens = tipoProceso.getTokens();
        String aux = tokens.get(1);
        String tokenNombre1 = aux.replace(',', ' ');
        tokens.set(1, tokenNombre1);
        //Aqui se va a validar que la tabla en donde se va a crear el registro exista
        //CREAR REGISTRO nombre_tabla VALOR vCampo1 , vCampo2 ,... , vCampoN
        boolean encontrado = false;
        try {
            File archivo = new File("ArchivoMetaBD/MetaBD.CSV");
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
        return encontrado;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que las tablas ingresadas en la sentencias las dos existan
        //UNIR nombre_tabla1, nombre_tabla2 POR nombre_campo
        String aux = tokens.get(1);
        String tokenNombre1 = aux.substring(0, aux.length() - 1);
        tokens.set(1, tokenNombre1);
        boolean existenciaTabla1 = false, existenciaTabla2 = false;
        existenciaTabla1 = validarExistenciaTabla(1);
        existenciaTabla2 = validarExistenciaTabla(2);
        if (existenciaTabla1 == true && existenciaTabla2 == true) {
            return true;
        } else {
            if (existenciaTabla1 == false) {
                errores += "Error -> no existe una tabla con el nombre '" + tokens.get(1) + "'"+"\n";
                //System.out.println("Error -> no existe una tabla con el nombre '" + tokens.get(1) + "'");
            }
            if (existenciaTabla2 == false) {
                errores += "Error -> no existe una tabla con el nombre'" + tokens.get(2) + "'"+"\n";
                //System.out.println("Error -> no existe una tabla con el nombre'" + tokens.get(2) + "'");
            }
            return false;
        }

    }
}
