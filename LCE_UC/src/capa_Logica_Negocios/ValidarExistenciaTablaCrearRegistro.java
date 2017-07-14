package capa_Logica_Negocios;

import static capa_de_datos.Existencia_Numero_Campos.verificarExistenciaNumeroCamposYLongitud;
import java.util.ArrayList;

public class ValidarExistenciaTablaCrearRegistro extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablaCrearRegistro instancia = null;

    private ValidarExistenciaTablaCrearRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablaCrearRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablaCrearRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la tabla en donde se va a crear el registro exista
        //CREAR REGISTRO nombre_tabla VALOR vCampo1 , vCampo2 ,... , vCampoN
        if (super.validarExistenciaTabla(2)) {
            return verificarExistenciaNumeroCamposYLongitud(tokens);
        }

        return false;

    }
}
