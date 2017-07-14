package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Existencia_Campo.verificarExistenciaCampo;
import java.util.ArrayList;

public class ValidarExistenciaCampos extends Comando {

    //SINLGETON
    private static ValidarExistenciaCampos instancia = null;
    
    private ValidarExistenciaCampos(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarExistenciaCampos getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaCampos(tipoProces);
        }
        return instancia;
    }
    
    
    
    

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que exista el campo calve y el campo anterior de la sentencia
        //MODIFICAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo_anterior POR valor_campo_nuevo
        if (verificarExistenciaCampo(tipoProceso.getTokens(),6))
            return true;
        
        errores += "El campo '"+tipoProceso.getTokens().get(6)+"' no existe en la tabla '"+tipoProceso.getTokens().get(2)+"'"+"\n";
        //System.out.println("El campo '"+tipoProceso.getTokens().get(6)+"' no existe en la tabla '"+tipoProceso.getTokens().get(2)+"'");
        return false;
    } 

}
