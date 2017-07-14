package capa_de_datos;

import java.io.File;

public class Obtener_Tablas {
    public static String[] obtener_nombres_tablas() {
        File f = new File("Tablas");
        
        if (f.exists()){
            File[] ficheros = f.listFiles();
            String [] nombres = new String[ficheros.length];
            for (int x=0;x<ficheros.length;x++){
                nombres[x]=ficheros[x].getName();
                //System.out.println(ficheros[x].getName());
            }
            return nombres;
        }
        else {
            System.out.println("Archivo no encontrado!");
            return null;
        } 
    }
}
