package capa_Logica_Negocios;

import java.util.ArrayList;

public class ValidarExistenciaTablaModificarRegistro extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablaModificarRegistro instancia = null;

    private ValidarExistenciaTablaModificarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablaModificarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablaModificarRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la tabla de la que se quiere editar un registro exista
        //MODIFICAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo_anterior POR valor_campo_nuevo 
        return super.validarExistenciaTabla(2);
    }

}
