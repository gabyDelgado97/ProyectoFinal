package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Tabla_Vacia.verificarTablaVacia;

public class ValidarTablaVaciaEliminarRegistro extends Comando {

    //SINLGETON
    private static ValidarTablaVaciaEliminarRegistro instancia = null;

    private ValidarTablaVaciaEliminarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarTablaVaciaEliminarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarTablaVaciaEliminarRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la tabla de la que se quiere eliminar un registro exista
        //ELIMINAR REGISTRO nombre_tabla CLAVE valorCampoClave
        if(!verificarTablaVacia(tipoProceso.getTokens(),2))
            return true;
         
        errores += "La tabla '"+tipoProceso.getTokens().get(2)+"' no tiene registros para eliminar"+"\n";
        //System.out.println("La tabla '"+tipoProceso.getTokens().get(2)+"' no tiene registros para eliminar");
        return false;
    }

}
