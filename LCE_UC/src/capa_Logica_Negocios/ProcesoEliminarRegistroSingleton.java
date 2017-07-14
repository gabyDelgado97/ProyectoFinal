package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ProcesoEliminarRegistroSingleton extends ProccesManager implements ManejadorProcesos {

    private ManejadorProcesos sucesor;

    //SINLGETON
    private static ProcesoEliminarRegistroSingleton instancia = null;

    private ProcesoEliminarRegistroSingleton() {
        super();
    }

    public static ProcesoEliminarRegistroSingleton getInstance() {
        if (instancia == null) {
            instancia = new ProcesoEliminarRegistroSingleton();
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
        if ((tokens.size() >= 2 && "BORRAR".equals(tokens.get(0))) && "REGISTRO".equals(tokens.get(1))) { //OBTENER LAS DOS PRIMERAS PALABRAS DEL ATRIBUTO SENTENCIA QUE DEBEN SER IGUALES A CREAR TABLA PARA EJECUTAR EL PROCESO
            return super.procesar();
        } else {
            errores += "Error -> El comando" + tokens+" no se reconoce\n";
            //System.out.println("Error -> Unrefered to: " + tokens.get(0) + " " + tokens.get(1));
            return false;
        }
    }

    //REDEFINICION DE LOS METODOS PARA EL TEMPLATE METHOD
    @Override
    public boolean ValidarSentencia() {
        managerComandos.setCommand(ValidarSentenciaEliminarRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }

    @Override
    public boolean EjecutarValidaciones() {
        managerComandos.setCommand(ValidarExistenciaTablaEliminarRegistro.getInstance(this));
        estado = managerComandos.EjecutarProceso();
        if (estado) {
            managerComandos.setCommand(ValidarTablaVaciaEliminarRegistro.getInstance(this));
            estado = managerComandos.EjecutarProceso();
            if (estado) {
                managerComandos.setCommand(ValidarExistenciaCampoClave.getInstance(this));
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
        managerComandos.setCommand(EliminarRegistro.getInstance(this));
        return managerComandos.EjecutarProceso();
    }
}
