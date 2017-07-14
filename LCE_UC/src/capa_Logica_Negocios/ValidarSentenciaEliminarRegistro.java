package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaEliminarRegistro extends Comando {

    //SINLGETON
    private static ValidarSentenciaEliminarRegistro instancia = null;
    
    private ValidarSentenciaEliminarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarSentenciaEliminarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaEliminarRegistro(tipoProces);
        }
        return instancia;
    }
    

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que la sentencia eliminar registro este bien escrita
        //ELIMINAR REGISTRO nombre_tabla CLAVE valorCampoClave
        if(tokens.size() == 5){
            if("BORRAR".equals(tokens.get(0))) {
                if("REGISTRO".equals(tokens.get(1))) {
                    if("CLAVE".equals(tokens.get(3))){
                        return true;
                    }
                    errores += "Error -> El comando" + tokens.get(3)+" no se reconoce\n";
                    //System.out.println("Error -> Expected: CLAVE - but Founded: "+tokens.get(3));
                    return false;
                }
                errores += "Error -> El comando" + tokens.get(1)+" no se reconoce\n";
                //System.out.println("Error -> Expected: REGISTRO - but Founded: "+tokens.get(1));
                return false;
            }
            errores += "Error -> El comando" + tokens.get(0)+" no se reconoce\n";
            //System.out.println("Error -> Expected: ELIMINAR - but Founded: "+tokens.get(0));
            return false;
        }
        errores += "Error falta o hay un exceso de argumentos\n";
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        return false;
    }

}
