package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaUnirTabla extends Comando {

    //SINLGETON
    private static ValidarSentenciaUnirTabla instancia = null;
    
    private ValidarSentenciaUnirTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarSentenciaUnirTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaUnirTabla(tipoProces);
        }
        return instancia;
    }
    
    
    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que la sentencia unir tablas este bien escrita
        //UNIR nombre_tabla1, nombre_tabla2 POR nombre_campo
        String aux=tokens.get(1);
        String tokenNombre1=aux.replace(',', ' ');
        tokens.set(1, tokenNombre1);
        if(tokens.size() == 6){
            if("UNIR".equals(tokens.get(0))) {
                if("POR".equals(tokens.get(3))) {
                   return true;
                }
                errores += "Error -> Expected: POR - but Founded: "+tokens.get(3)+"\n";
                //System.out.println("Error -> Expected: POR - but Founded: "+tokens.get(3));
                return false;
            }
            errores += "Error -> Expected: UNIR - but Founded: "+tokens.get(0)+"\n";
            //System.out.println("Error -> Expected: UNIR - but Founded: "+tokens.get(0));
            return false;
        }
        errores += "Error falta o hay un exceso de argumentos!!"+"\n";
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        return false;
    }

}
