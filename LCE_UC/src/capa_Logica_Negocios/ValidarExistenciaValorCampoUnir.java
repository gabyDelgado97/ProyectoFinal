package capa_Logica_Negocios;

import static capa_de_datos.Existencia_Valor_Campo.verificarExistenciaValorCampo;

public class ValidarExistenciaValorCampoUnir extends Comando{
    //SINLGETON
    private static ValidarExistenciaValorCampoUnir instancia = null;
    
    private ValidarExistenciaValorCampoUnir(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarExistenciaValorCampoUnir getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarExistenciaValorCampoUnir(tipoProces);
        }
        return instancia;
    }
      
    @Override
    public boolean ejecutar() {
       return verificarExistenciaValorCampo(tipoProceso.getTokens());
    }
}
