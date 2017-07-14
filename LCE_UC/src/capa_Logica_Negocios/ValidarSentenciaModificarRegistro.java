package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaModificarRegistro extends Comando {

    //SINLGETON
    private static ValidarSentenciaModificarRegistro instancia = null;

    private ValidarSentenciaModificarRegistro(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarSentenciaModificarRegistro getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaModificarRegistro(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la sentencia modificar registro este bien escrita
        //MODIFICAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo_anterior POR valor_campo_nuevo 
        if (tokens.size() == 9) {
            if (tokens.get(0).equals("ACTUALIZAR")) {
                if (tokens.get(1).equals("REGISTRO")) {
                    if (tokens.get(3).equals("CLAVE")) {
                        if (tokens.get(5).equals("CAMPO")) {
                            if (tokens.get(7).equals("POR")) {
                                return true;
                            } else {
                                errores += "Error -> El comando" + tokens.get(7)+" no se reconoce\n";
                                //System.out.println("Error -> Expected: POR - but Founded: "+tokens.get(7));
                                return false;
                            }
                        } else {
                            errores += "Error -> El comando" + tokens.get(5)+" no se reconoce\n";
                            //System.out.println("Error -> Expected: CAMPO - but Founded: "+tokens.get(5));
                            return false;
                        }
                    } else {
                        errores += "Error -> El comando" + tokens.get(3)+" no se reconoce\n";
                        //System.out.println("Error -> Expected: CLAVE - but Founded: "+tokens.get(3));
                        return false;
                    }
                }else{
                    errores += "Error -> El comando" + tokens.get(1)+" no se reconoce\n";
                    return false;
                }
            }
            errores += "Error -> El comando" + tokens.get(0)+" no se reconoce\n";
            return false;
        }
        errores += "Error falta o hay un exceso de argumentos\n";
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        return false;
    }
}
