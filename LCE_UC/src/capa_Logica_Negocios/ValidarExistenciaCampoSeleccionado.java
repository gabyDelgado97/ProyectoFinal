package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Existencia_Campo.verificarExistenciaCampo;
import java.util.ArrayList;

public class ValidarExistenciaCampoSeleccionado extends Comando {

    //SINLGETON
    private static ValidarExistenciaCampoSeleccionado instancia = null;
    
    private ValidarExistenciaCampoSeleccionado(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarExistenciaCampoSeleccionado getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaCampoSeleccionado(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que el campo seleccionado exista en la tabla
        //SELECCIONAR DE nombre_tabla DONDE nombre_campo = “Algo”
        if (verificarExistenciaCampo(tokens,4)) {
            return true;
        }
        errores += "La Tabla '"+tipoProceso.getTokens().get(2)+"' no tiene el campo '"+tipoProceso.getTokens().get(4)+"'"+"\n";
        //System.out.println("La Tabla '"+tipoProceso.getTokens().get(2)+"' no tiene el campo '"+tipoProceso.getTokens().get(4)+"'");
        return true;
    }
}
