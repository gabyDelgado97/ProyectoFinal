package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaModificarTabla extends Comando {

    //SINLGETON
    private static ValidarSentenciaModificarTabla instancia = null;

    private ValidarSentenciaModificarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarSentenciaModificarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaModificarTabla(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la sentencia modificar una tabla este bien escrita
        //MODIFICAR TABLA nombre_tabla CAMPO nombre_campo_anterior POR nombre_campo_nuevo

        if (tokens.size() == 7) {
            if ("MODIFICAR".equals(tokens.get(0))) {
                if ("TABLA".equals(tokens.get(1))) {
                    if ("CAMPO".equals(tokens.get(3))) {
                        if ("POR".equals(tokens.get(5))) {
                            return true;
                        }
                        errores += "Error -> El comando" + tokens.get(5)+" no se reconoce\n";
                        //System.out.println("Error -> Expected: POR- but Founded:  " + tokens.get(5));
                        return false;
                    }
                    errores += "Error -> El comando" + tokens.get(3)+" no se reconoce\n";
                    //System.out.println("Error -> Expected: CAMPO - but Founded:  " + tokens.get(3));
                    return false;
                }

                errores += "Error -> El comando" + tokens.get(1)+" no se reconoce\n";
                //System.out.println("Error -> Expected: TABLA - but Founded:  " + tokens.get(1));
                return false;

            }
            errores += "Error -> El comando" + tokens.get(0)+" no se reconoce\n";
            //System.out.println("Error -> Expected: MODIFICAR - but Founded: " + tokens.get(0));
            return false;
        }
        errores += "Error falta o hay un exceso de argumentos\n";
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        return false;
    }

}
