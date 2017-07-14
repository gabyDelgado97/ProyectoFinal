package capa_Logica_Negocios;

public interface ManejadorProcesos {
    public void ponerSucesor (ManejadorProcesos sucesor);
    public ManejadorProcesos getSucesor();
    public boolean EjecutarProceso(String sentencia);
}
