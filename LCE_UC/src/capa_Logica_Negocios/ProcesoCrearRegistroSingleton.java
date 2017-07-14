package capa_Logica_Negocios;

import java.util.ArrayList;

public class ProcesoCrearRegistroSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;
    
    
    //SINLGETON
    private static ProcesoCrearRegistroSingleton instancia = null;
    
    //utilizamos el contructor heredado por la clase padre
    private ProcesoCrearRegistroSingleton() {
        super();
    }
    
    public static ProcesoCrearRegistroSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoCrearRegistroSingleton();
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
        if ((tokens.size() >= 2 && "INSERTAR".equals(tokens.get(0))) && "EN".equals(tokens.get(1)) && "VALORES".equals(tokens.get(3))) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);
        }
    }
    
    
    
    
    
    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        managerComandos.setCommand(ValidarSentenciaCrearRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
        
    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablaCrearRegistro.getInstance(this));
        estado = managerComandos.EjecutarProceso();
        if (estado) {
            managerComandos.setCommand(ValidarCampoClave.getInstance(this));
            estado = managerComandos.EjecutarProceso();
            if (estado)
                return true;
               
            return false;
        }   
        
        return false;
    }
        
    @Override
    public boolean EjecutarComando() {
        managerComandos.setCommand(CrearRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
