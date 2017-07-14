package capa_Logica_Negocios;

import static capa_de_datos.Modificar_Tabla.proceso_modificar_tabla;

public class ModificarTabla extends Comando {

    //SINLGETON
    private static ModificarTabla instancia = null;
    
    private ModificarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static ModificarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ModificarTabla(tipoProces);
        }
        return instancia;
    }
    

    @Override
    public boolean ejecutar() {
        //Aqui se va a validar modificar la tabla
        //MODIFICAR TABLA nombre_tabla CAMPO nombre_campo_anterior POR nombre_campo_nuevo
        return proceso_modificar_tabla(tipoProceso.getTokens());
    }

}
