package capa_Logica_Negocios;

import static capa_de_datos.Eliminar_Tabla.escribirEliminarTabla;
import java.util.ArrayList;

public class EliminarTabla extends Comando {

    //SINLGETON
    private static EliminarTabla instancia = null;
    
    private EliminarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static EliminarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new EliminarTabla(tipoProces);
        }
        return instancia;
    }
    
     
    @Override
    public boolean ejecutar() {
        return escribirEliminarTabla(tipoProceso.getTokens());
    }
    
}
