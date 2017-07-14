package capa_Logica_Negocios;

import java.util.ArrayList;

public class ProcesoUnirTablaSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;

    //SINLGETON
    private static ProcesoUnirTablaSingleton instancia = null;

    //utilizamos el contructor heredado por la clase padre
    private ProcesoUnirTablaSingleton() {
        super();
    }

    public static ProcesoUnirTablaSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoUnirTablaSingleton();
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
        if (tokens.size() >= 2 && "UNIR".equals(tokens.get(0))) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);
        }
    }

    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        managerComandos.setCommand(ValidarSentenciaUnirTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }  

    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablasUnir.getInstance(this));
        estado = managerComandos.EjecutarProceso();
        if (estado) {
            managerComandos.setCommand(ValidarTablasVaciaUnir.getInstance(this));
            estado = managerComandos.EjecutarProceso();
            if (estado) {     
                managerComandos.setCommand(ValidarExistenciaCampoUnir.getInstance(this));
                estado = managerComandos.EjecutarProceso();
                if (estado) {  
                    managerComandos.setCommand(ValidarExistenciaValorCampoUnir.getInstance(this));
                    estado = managerComandos.EjecutarProceso();
                } 
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
        managerComandos.setCommand(UnirTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
