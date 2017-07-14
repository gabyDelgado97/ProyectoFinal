package capa_Logica_Negocios;

import static capa_de_datos.Unir_Tabla.proceso_unir_tabla;

public class UnirTabla extends Comando {

    //SINLGETON
    private static UnirTabla instancia = null;
    
    private UnirTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }
    
    public static UnirTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new UnirTabla(tipoProces);
        }
        return instancia;
    }
    
     
    @Override
    public boolean ejecutar() {
        //Aqui se va a validar realizar el proceso de unir las tablas
        //UNIR nombre_tabla1, nombre_tabla2 POR nombre_campo
        return proceso_unir_tabla(tipoProceso.getTokens());
    }

}
