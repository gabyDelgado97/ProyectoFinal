package capa_Logica_Negocios;

import java.util.ArrayList;

public class ProcesoEliminarTablaSingleton extends ProccesManager implements ManejadorProcesos {
    
    
    
   //SINLGETON
    private static ProcesoEliminarTablaSingleton instancia = null;
    
    private ManejadorProcesos sucesor;
        //utilizamos el contructor heredado por la clase padre
    private ProcesoEliminarTablaSingleton() {
        super();
    }
    
    public static ProcesoEliminarTablaSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoEliminarTablaSingleton();
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
        if ((tokens.size() >= 2 && "ELIMINAR".equals(tokens.get(0))) && "TABLA".equals(tokens.get(1))) { 
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);
        }
    }  
    
    
       
    
    
    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        managerComandos.setCommand(ValidarSentenciaEliminarTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
    
    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablaEliminar.getInstance(this));
        return managerComandos.EjecutarProceso();
    }  
    
    @Override
    public boolean EjecutarComando() {
        managerComandos.setCommand(EliminarTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
