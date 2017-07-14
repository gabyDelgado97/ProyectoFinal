package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Existencia_Campo.verificarExistenciaCampo;

public class ValidarExistenciaCampoAnterior extends Comando {

    //SINLGETON
    private static ValidarExistenciaCampoAnterior instancia = null;
    
    private ValidarExistenciaCampoAnterior(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarExistenciaCampoAnterior getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaCampoAnterior(tipoProces);
        }
        return instancia;
    }
     
 
    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que  el campo que se dea cambiar por otro exista en los campos de la tabla
        //MODIFICAR TABLA nombre_tabla CAMPO nombre_campo_anterior POR nombre_campo_nuevo
        if (verificarExistenciaCampo(tipoProceso.getTokens(),4)) {
            if (!verificarExistenciaCampo(tipoProceso.getTokens(),6))
                return true;
            errores += "Ya existe un campo con el nombre '" + tipoProceso.getTokens().get(6) + "' en la tabla '" + tipoProceso.getTokens().get(2)+"'"+"\n";
            //System.out.println("Ya existe un campo con el nombre '" + tipoProceso.getTokens().get(6) + "' en la tabla '" + tipoProceso.getTokens().get(2)+"'");
            return false;
        }
        
        errores += "NO existe campo '" + tipoProceso.getTokens().get(4) + "' en la tabla '" + tipoProceso.getTokens().get(2)+"'"+"\n";
        //System.out.println("NO existe campo '" + tipoProceso.getTokens().get(4) + "' en la tabla '" + tipoProceso.getTokens().get(2)+"'");
            
        return false;
    }
}
