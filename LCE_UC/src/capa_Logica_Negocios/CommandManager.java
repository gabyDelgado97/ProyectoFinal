package capa_Logica_Negocios;

public class CommandManager {

    private Comando comando;

    public void setCommand(Comando command) {
        this.comando = command;
    }

    public boolean EjecutarProceso() {
        return comando.ejecutar();
    }
}
