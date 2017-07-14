/*   0       1      2     3    4     5      6   7  8      
SELECCIONAR DE Original DONDE DNI ORDENADO asc VER 9
SELECCIONAR DE Original DONDE DNI ORDENADO desc
SELECCIONAR DE tabla DONDE id = bh
SELECCIONAR DE tabla ORDENADO asc/desc  //campo clave
SELECCIONAR DE tabla   //mostrar todos los registros
 */
package capa_Logica_Negocios;

import static prueba.Prueba.errores;
import java.util.ArrayList;

public class ValidarSentenciaSeleccionarTabla extends Comando {

    //SINLGETON
    private static ValidarSentenciaSeleccionarTabla instancia = null;

    private ValidarSentenciaSeleccionarTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarSentenciaSeleccionarTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaSeleccionarTabla(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        //Aqui se va a validar que la sentencia seleccionar tabla este bien escrita
        //SELECCIONAR DE nombre_tabla DONDE nombre_campo = “Algo”

        if (tokens.size() == 7) {
            if (tokens.get(1).equals("DE")) {
                if (tokens.get(3).equals("DONDE")) {
                    if (tokens.get(5).equals("=") || tokens.get(5).equals("ORDENADO")) {
                        return true;
                    } else {
                        errores += "Error -> El comando " + tokens.get(5) + " no se reconoce\n";
                        //System.out.println("Error -> Expected: '=' in: " + tokens.get(4));
                        return false;
                    }
                } else {
                    errores += "Error -> El comando " + tokens.get(3) + " no se reconoce\n";
                    //System.out.println("Error -> Expected: DONDE - but Founded: " + tokens.get(3));
                    return false;
                }
            } else {
                errores += "Error -> El comando " + tokens.get(1) + " no se reconoce\n";
                //System.out.println("Error -> Expected: DE - but Founded: " + tokens.get(1));
                return false;
            }
        } else if (tokens.size() == 9) {
            if (tokens.get(1).equals("DE")) {
                if (tokens.get(3).equals("DONDE")) {
                    if (tokens.get(5).equals("ORDENADO")) {
                        if (tokens.get(7).equals("VER")) {
                            return true;
                        } else {
                            errores += "Error -> El comando " + tokens.get(7) + " no se reconoce\n";
                            return false;
                        }
                    } else {
                        errores += "Error -> El comando " + tokens.get(5) + " no se reconoce\n";
                        return false;
                    }
                } else {
                    errores += "Error -> El comando " + tokens.get(3) + " no se reconoce\n";
                    return false;
                }
            } else {
                errores += "Error -> El comando " + tokens.get(1) + " no se reconoce\n";
                return false;
            }
        } else if(tokens.size() == 5){
            if (tokens.get(1).equals("DE")) {
                if (tokens.get(3).equals("ORDENADO")) {
                    return true;
                } else {
                    errores += "Error -> El comando " + tokens.get(3) + " no se reconoce\n";
                    return false;
                }
            } else {
                errores += "Error -> El comando " + tokens.get(1) + " no se reconoce\n";
                return false;
            }
        } else if(tokens.size() == 3){
            if (tokens.get(1).equals("DE")) {
                return true;
            } else {
                errores += "Error -> El comando" + tokens.get(1) + " no se reconoce\n";
                return false;
            }
        }
        errores += "Error falta o hay un exceso de argumentos\n";
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        return false;
    }

}
