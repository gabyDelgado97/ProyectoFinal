package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import static capa_de_datos.Tabla_Vacia.verificarTablaVacia;

public class ValidarTablaVaciaModificar extends Comando {
    
    //SINLGETON
    private static ValidarTablaVaciaModificar instancia = null;
    
    private ValidarTablaVaciaModificar(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ValidarTablaVaciaModificar getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarTablaVaciaModificar(tipoProces);
        }
        return instancia;
    }
    
    

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar que la tabla SI este vacia si se quiere modificar sus campos
        //MODIFICAR TABLA nombre_tabla CAMPO nombre_campo_anterior POR nombre_campo_nuevo
        if(verificarTablaVacia(tipoProceso.getTokens(),2))
            return true;
        
        errores += "La tabla '"+tipoProceso.getTokens().get(2)+"' no se puede modificar porque tiene registros"+"\n";
        //System.out.println("La tabla '"+tipoProceso.getTokens().get(2)+"' no se puede modificar porque tiene registros");
        return false;
    }
}
