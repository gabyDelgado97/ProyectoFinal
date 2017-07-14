package capa_Logica_Negocios;
 
import static capa_de_datos.Existencia_Campo_Clave.verificarNoExistenciaCampoClave;

public class ValidarCampoClave extends Comando {

    //SINLGETON
    private static ValidarCampoClave instancia = null;

    private ValidarCampoClave(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarCampoClave getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarCampoClave(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la el campo clave que nosotros querramos ingresar no exista en algun registro
        //CREAR REGISTRO nombre_tabla VALOR vCampo1,vCampo2,...,vCampoN
        return verificarNoExistenciaCampoClave(tipoProceso.getTokens());
    }

}
