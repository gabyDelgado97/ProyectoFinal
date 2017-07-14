package capa_Logica_Negocios;

import static capa_de_datos.Crear_Registro.escribirCrearRegistro;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrearRegistro extends Comando {

    //SINLGETON
    private static CrearRegistro instancia = null;

    private CrearRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static CrearRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new CrearRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        try {
            //Aqui se va a realizar el proceso de crear un registro
            //CREAR REGISTRO nombre_tabla VALOR vCampo1 , vCampo2 ,... , vCampoN
            return escribirCrearRegistro(tipoProceso.getTokens());
        } catch (Exception ex) {
            Logger.getLogger(CrearRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}