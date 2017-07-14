package capa_Logica_Negocios;

import static capa_de_datos.Existencia_Valor_Campo_Clave.verificarExistenciaValorCampoClave;

public class ValidarValorCampoClaveModificarRegistro extends Comando{

    //SINLGETON
    private static ValidarValorCampoClaveModificarRegistro instancia = null;
    
    private ValidarValorCampoClaveModificarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarValorCampoClaveModificarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarValorCampoClaveModificarRegistro(tipoProces);
        }
        return instancia;
    }
    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que exista el valor del campo clave 
        //MODIFICAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo_anterior POR valor_campo_nuevo 
        return verificarExistenciaValorCampoClave(tipoProceso.getTokens());
    }
    
}
