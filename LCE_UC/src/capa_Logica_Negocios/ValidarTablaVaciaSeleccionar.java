package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Tabla_Vacia.verificarTablaVacia;
import java.util.ArrayList;

public class ValidarTablaVaciaSeleccionar extends Comando {

    //SINLGETON
    private static ValidarTablaVaciaSeleccionar instancia = null;
    
    private ValidarTablaVaciaSeleccionar(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarTablaVaciaSeleccionar getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarTablaVaciaSeleccionar(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens  = tipoProceso.getTokens();
        //Aqui se va a validar que la tabla que se desea seleccionar no este vacia
        //SELECCIONAR DE nombre_tabla DONDE nombre_campo = “Algo”
        if(!verificarTablaVacia(tokens,2)) {
            return true;
        }
        errores += "La tabla '"+tipoProceso.getTokens().get(2)+"' no tiene registros"+"\n";
        //System.out.println("La tabla '"+tipoProceso.getTokens().get(2)+"' no tiene registros");    
        return false;
    }
}
