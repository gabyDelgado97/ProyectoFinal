package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaEliminarTabla extends Comando {

    //SINLGETON
    private static ValidarSentenciaEliminarTabla instancia = null;
    
    private ValidarSentenciaEliminarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarSentenciaEliminarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaEliminarTabla(tipoProces);
        }
        return instancia;
    }
    

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la sentencia eliminar tabla este bien escrita
        //ELIMINAR TABLA nombre_tabla
        ArrayList<String> tokens = tipoProceso.getTokens();
        if(tokens.size() == 3){
            if ("ELIMINAR".equals(tokens.get(0)) && "TABLA".equals(tokens.get(1))) {
                return true;
            }
            errores += "Error -> El comando" + tokens+" no se reconoce\n";
            //System.out.println("Error -> Expected: VALOR - but Founded: " + tokens.get(3));
            return false;
        }
        
        if(tokens.size() > 3)
            errores += "Error -> hay un exceso de argumentos en sentencia ELIMINAR TABLA!!"+"\n";
            //System.out.println("Error -> hay un exceso de argumentos en sentencia ELIMINAR TABLA!!");
        else
            errores += "Error -> muy pocos argumentos en sentencia ELIMINAR TABLA!!"+"\n";
            //System.out.println("Error -> muy pocos argumentos en sentencia ELIMINAR TABLA!!");
        return false;
    }
}
