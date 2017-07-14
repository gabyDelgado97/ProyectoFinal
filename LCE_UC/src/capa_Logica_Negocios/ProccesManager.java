package capa_Logica_Negocios;

import java.util.ArrayList;

public abstract class ProccesManager {
    public boolean estado;
    public CommandManager managerComandos; 
    private ArrayList<String> tokens;

    public ProccesManager() {
        estado = false;
        managerComandos = new CommandManager(); 
        tokens = new ArrayList<>();
    }
    
    
    //METODO TEMPLATE QUE SIEMPRE SE VA A EJECUATR EN EL ORDEN DEFINIDO
    public final boolean procesar() {
        estado = ValidarSentencia(); 
        if (estado) {
            estado = EjecutarValidaciones();
            if (estado) {
                estado = EjecutarComando();
                if (estado) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    //METODOS QUE SE DEBEN DEFINIR EN LAS CLASES HIJAS PARA DARLE UN COMPORTAMIENTO DISTINTO A CADA CLASE EXTENDIDA POR PROCCESMANAGER
    public abstract boolean ValidarSentencia();
    public abstract boolean EjecutarValidaciones();
    public abstract boolean EjecutarComando();
    
    
    
    
    //METODO PARA OBTENER LOS TOKENS DE LA SENTENCIA
    public ArrayList<String> splitTokens(String sentencia) {
      
        tokens=new ArrayList<>();
        String cadena = "";
        
        for (int i = 0; i < sentencia.length(); i++) {
            if ((sentencia.charAt(i)) != ' ') {
                cadena += (sentencia.charAt(i));
            }
            if ((sentencia.charAt(i)) == ' ' && !"".equals(cadena)) {
                tokens.add(cadena);
                cadena = "";
            }
            if (sentencia.length() - 1 == i && !"".equals(cadena)) {
                tokens.add(cadena);
                cadena = "";
            }
        }
        return tokens;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }
}
