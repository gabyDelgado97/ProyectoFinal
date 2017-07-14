package capa_Logica_Negocios;

import static capa_de_datos.Seleccionar_Tabla.buscarSeleccionarTabla;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeleccionarTabla extends Comando {

    //SINLGETON
    private static SeleccionarTabla instancia = null;

    private SeleccionarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static SeleccionarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null)  {
            instancia = new SeleccionarTabla(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        try {
            //Aqui se va arealizar el proceso de seleccionar la tabla
            //SELECCIONAR DE nombre_tabla DONDE nombre_campo = “Algo”
            return buscarSeleccionarTabla(tokens);
        } catch (ParseException ex) {
            Logger.getLogger(SeleccionarTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
