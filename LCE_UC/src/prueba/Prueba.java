package prueba;

import capa_Logica_Negocios.ProcesoSeleccionarTablaSingleton;
import capa_Logica_Negocios.ProcesoCrearRegistroSingleton;
import capa_Logica_Negocios.ManejadorProcesos;
import capa_Logica_Negocios.ProcesoUnirTablaSingleton;
import capa_Logica_Negocios.ProcesoModificarRegistroSingleton;
import capa_Logica_Negocios.ProcesoModificarTablaSingleton;
import capa_Logica_Negocios.ProcesoEliminarRegistroSingleton;
import capa_Logica_Negocios.ProcesoEliminarTablaSingleton;
import capa_Logica_Negocios.ProcesoCrearTablaSingleton;

public class Prueba {
    
    public static String errores;
    public static String sentencia;
 
    public boolean Ejecutar(String  sentencia) {
        errores = "";
        
        ManejadorProcesos procesoCrearTabla = ProcesoCrearTablaSingleton.getInstance();

        ManejadorProcesos procesoModificarTabla = ProcesoModificarTablaSingleton.getInstance();
        procesoCrearTabla.ponerSucesor(procesoModificarTabla);

        ManejadorProcesos procesoEliminarTabla = ProcesoEliminarTablaSingleton.getInstance();
        procesoModificarTabla.ponerSucesor(procesoEliminarTabla);

        ManejadorProcesos procesoSeleccionarTabla = ProcesoSeleccionarTablaSingleton.getInstance();
        procesoEliminarTabla.ponerSucesor(procesoSeleccionarTabla);

        ManejadorProcesos procesoUnirTabla = ProcesoUnirTablaSingleton.getInstance();
        procesoSeleccionarTabla.ponerSucesor(procesoUnirTabla);

        ManejadorProcesos procesoCrearRegistro = ProcesoCrearRegistroSingleton.getInstance();
        procesoUnirTabla.ponerSucesor(procesoCrearRegistro);

        ManejadorProcesos procesoModificarRegistro = ProcesoModificarRegistroSingleton.getInstance();
        procesoCrearRegistro.ponerSucesor(procesoModificarRegistro);

        ManejadorProcesos procesoEliminarRegistro = ProcesoEliminarRegistroSingleton.getInstance();
        procesoModificarRegistro.ponerSucesor(procesoEliminarRegistro);
        
        //EL PROCESO ME DEVUELVE TRUEO FALSE DEPENDIENDO SI SE REALIZO O NO
        //SOLO CUANDO ME DEVUELVE FALSE MANDO A IMPRIMIR LOS ERRORES
        if (!procesoCrearTabla.EjecutarProceso(sentencia)){
            System.out.println("ERRORES: \n"+errores);
            sentencia="";
            return false;
            //procesoSeleccionarTabla=null;
        }
        return true;
        //procesoSeleccionarTabla=null;
    }

    
    
}
