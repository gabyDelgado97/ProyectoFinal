package capa_Logica_Negocios;

public class ValidarExistenciaTablaEliminar extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablaEliminar instancia = null;

    private ValidarExistenciaTablaEliminar(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablaEliminar getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablaEliminar(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la tabla que se dea eliminar exista
        //ELIMINAR TABLA nombre_tabla
        return super.validarExistenciaTabla(2);
    }
}
