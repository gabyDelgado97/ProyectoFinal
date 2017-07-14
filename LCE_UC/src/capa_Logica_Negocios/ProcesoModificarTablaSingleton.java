package capa_Logica_Negocios;

import java.util.ArrayList;

public class ProcesoModificarTablaSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;

    //SINLGETON
    private static ProcesoModificarTablaSingleton instancia = null;

    //utilizamos el contructor heredado por la clase padre
    private ProcesoModificarTablaSingleton() {
        super();
    }

    public static ProcesoModificarTablaSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoModificarTablaSingleton();
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
        if ((tokens.size() >= 2 && "MODIFICAR".equals(tokens.get(0))) && "TABLA".equals(tokens.get(1))) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);
        }
    }

    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        
        managerComandos.setCommand(ValidarSentenciaModificarTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
 
    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablaModificar.getInstance(this));
        estado = managerComandos.EjecutarProceso();
        if (estado) {
            managerComandos.setCommand(ValidarTablaVaciaModificar.getInstance(this));
            estado = managerComandos.EjecutarProceso();
            if (estado) { 
                managerComandos.setCommand(ValidarExistenciaCampoAnterior.getInstance(this));
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

    @Override
    public boolean EjecutarComando() {
        managerComandos.setCommand(ModificarTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
