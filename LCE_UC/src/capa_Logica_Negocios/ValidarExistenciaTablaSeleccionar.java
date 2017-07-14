package capa_Logica_Negocios;

public class ValidarExistenciaTablaSeleccionar extends Comando {

    //SINLGETON
    private static ValidarExistenciaTablaSeleccionar instancia = null;

    private ValidarExistenciaTablaSeleccionar(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarExistenciaTablaSeleccionar getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaTablaSeleccionar(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {

        //Aqui se va a validar que tabla que se desea seleccionar exista 
        //SELECCIONAR DE nombre_tabla DONDE nombre_campo = “Algo”
        return super.validarExistenciaTabla(2);
    }
}
