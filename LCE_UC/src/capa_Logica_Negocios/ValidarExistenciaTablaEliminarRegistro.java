package capa_Logica_Negocios;

import java.util.ArrayList;

public class ValidarExistenciaTablaEliminarRegistro extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablaEliminarRegistro instancia = null;

    private ValidarExistenciaTablaEliminarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablaEliminarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablaEliminarRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la tabla de la que se quiere eliminar el registro exista
        //ELIMINAR REGISTRO nombre_tabla CLAVE valorCampoClave
        return super.validarExistenciaTabla(2);
    }

}
