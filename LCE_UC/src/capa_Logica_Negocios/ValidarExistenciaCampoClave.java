package capa_Logica_Negocios;

import static capa_de_datos.Existencia_Valor_Campo_Clave.verificarExistenciaValorCampoClave;
import java.util.ArrayList;

public class ValidarExistenciaCampoClave extends Comando {

    //SINLGETON
    private static ValidarExistenciaCampoClave instancia = null;
    
    private ValidarExistenciaCampoClave(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarExistenciaCampoClave getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaCampoClave(tipoProces);
        }
        return instancia;
    }
    

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que exista un registro con el campo clave que ingreso en la sentencia.
        //ELIMINAR REGISTRO nombre_tabla CLAVE valorCampoClave
        return verificarExistenciaValorCampoClave(tipoProceso.getTokens());
    }

}