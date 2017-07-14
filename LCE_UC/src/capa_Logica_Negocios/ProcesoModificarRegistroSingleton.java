package capa_Logica_Negocios;

import java.util.ArrayList;

public class ProcesoModificarRegistroSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;

    //SINLGETON
    private static ProcesoModificarRegistroSingleton instancia = null;

    private ProcesoModificarRegistroSingleton() {
        super();
    }
 
    public static ProcesoModificarRegistroSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoModificarRegistroSingleton();
        }
        return instancia;
    }

    //CADENA DE RESPONSABILIDAD
    @Override
    public void ponerSucesor(ManejadorProcesos sucesor) {
        this.sucesor = sucesor;
    }

    @Override
    public ManejadorProcesos getSucesor() {
        return this.sucesor;
    }

    @Override
    public boolean EjecutarProceso(String sentencia) {
        ArrayList<String> tokens = super.splitTokens(sentencia);
        if ((tokens.size() >= 2 && "ACTUALIZAR".equals(tokens.get(0))) && "REGISTRO".equals(tokens.get(1)) && "CLAVE".equals(tokens.get(3)) && "CAMPO".equals(tokens.get(5)) && "POR".equals(tokens.get(7))) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);

        }
    }

    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        managerComandos.setCommand(ValidarSentenciaModificarRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
 
    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablaModificarRegistro.getInstance(this));
        estado = managerComandos.EjecutarProceso();
        if (estado) {
            managerComandos.setCommand(ValidarValorCampoClaveModificarRegistro.getInstance(this));
            estado = managerComandos.EjecutarProceso();
            if (estado) {
                managerComandos.setCommand(ValidarExistenciaCampos.getInstance(this));
                estado = managerComandos.EjecutarProceso();
                if (estado) { 
                    managerComandos.setCommand(ValidarLongitudCampo.getInstance(this));
                    estado = managerComandos.EjecutarProceso();
                    if (estado) {
                        return true;
                    } 
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean EjecutarComando() {
        managerComandos.setCommand(ModificarRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
