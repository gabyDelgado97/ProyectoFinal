package capa_Logica_Negocios;

import static capa_de_datos.Obtener_Tablas.obtener_nombres_tablas;
import capa_de_datos.Seleccionar_Tabla;
import capa_de_datos.Unir_Tabla;

public class ObtenerInformacion {
    public static String[] NombresTablas() {
        return obtener_nombres_tablas();
    }
    public static String obtenerSeleccionar(){
        return Seleccionar_Tabla.seleccionar;
    }
    public static String obtenerUnir(){
        return Unir_Tabla.unir;
    }
}
