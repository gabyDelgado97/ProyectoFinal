package capa_Logica_Negocios;

import java.util.ArrayList;

/*LAS CLASES DE LA CADENA DE RESPONSABILIDAD SON LA QUE INICIAN TODO*/
 /*POR ESO DEBEMOS MANDARLE COMO PARAMETRO LA SENTENCIA COMPLETA LEIDA DESDE LA TERMINAL*/
public class ProcesoCrearTablaSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;

    //SINLGETON
    private static ProcesoCrearTablaSingleton instancia = null;

    //utilizamos el contructor heredado por la clase padre
    private ProcesoCrearTablaSingleton() {
        super();
    }

    public static ProcesoCrearTablaSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoCrearTablaSingleton();
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
        if ((tokens.size() >= 2 && "CREAR".equals(tokens.get(0))) && "TABLA".equals(tokens.get(1)) ) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            System.out.println("Todo bien");
            return super.procesar();
        } else {
            return sucesor.EjecutarProceso(sentencia);
        }
    }
  
    
    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD       
    @Override
    public boolean ValidarSentencia() {
        //validacion de la sentencia crear tabla
        managerComandos.setCommand(ValidarSentenciaCrearTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }

    @Override
    public boolean EjecutarValidaciones() {
        //validacion de la existencia de la tabla
        managerComandos.setCommand(ValidarExistenciaTablaCrear.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
  
    @Override
    public boolean EjecutarComando() {
        //ejecucion del proceso
        managerComandos.setCommand(CrearTabla.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
