/*0     1     2     3         4       5   6   7       8     9        10
CREAR TABLA tabla CAMPOS nombre,id CLAVE id LONGITUD 3,4 ENCRIPTADO id
 */
package capa_Logica_Negocios;

import java.util.ArrayList;
import static prueba.Prueba.errores;

public class ValidarSentenciaCrearTabla extends Comando {

    //SINLGETON
    private static ValidarSentenciaCrearTabla instancia = null;

    private ValidarSentenciaCrearTabla(ProccesManager tipoProceso) {
        super(tipoProceso);
    }

    public static ValidarSentenciaCrearTabla getInstance(ProccesManager tipoProces) {
        if (instancia == null) {
            instancia = new ValidarSentenciaCrearTabla(tipoProces);
        }
        return instancia;
    }

    @Override
    public boolean ejecutar() {
        ArrayList<String> tokens = tipoProceso.getTokens();
        ArrayList<String> listaCampos = new ArrayList<>();
        boolean verificarLongitud = false;
        //Aqui se va a validar la sentencia crear una tabla
        //CREAR TABLA nombre_tabla CAMPOS campo1, … , campoN CLAVE campo1 LONGITUD 5 
        int aux = 0;
        int aux2 = 0;
        if (tokens.size() == 11 || tokens.size() == 9) {
            if ("CAMPOS".equals(tokens.get(3))) {
                String campos[] = tokens.get(4).split(",");
                for (int j = 0; j < campos.length; j++) {
                    if (!listaCampos.contains(campos[j])) {
                        listaCampos.add(campos[j]);
                    }
                }
                if (listaCampos.size() == campos.length) {
                    if ("CLAVE".equals(tokens.get(5))) {
                        for (int i = 0; i < campos.length; i++) {
                            if (campos[i].equals(tokens.get(6))) {
                                aux = 1;
                            }
                        }
                        if (aux == 1) {
                            if ("LONGITUD".equals(tokens.get(7))) {
                                String camposLongitud[] = tokens.get(8).split(","); //separamos todos los campos hasta que halla una coma y lo guardamos en un string
                                if (camposLongitud.length != campos.length) {
                                    errores += "Error -> Error el numero de longitudes de campos no coincide con el numero de campos: " + tokens.get(8) + "\n";
                                    return false;
                                } else {
                                    for (int i = 0; i < camposLongitud.length; i++) {
                                        if (!isNumeric(camposLongitud[i])) {
                                            verificarLongitud = true;
                                        }
                                    }
                                    if (verificarLongitud) {
                                        errores += "Error -> La longitud de cada campo debe ser un numero entre 0 y 256\n";
                                        return false;
                                    } else {
                                        String camposEncriptados[] = tokens.get(10).split(",");
                                        if (camposEncriptados.length <= campos.length) {
                                            for (int j = 0; j < camposEncriptados.length; j++) {
                                                aux2 = 0;
                                                for (int i = 0; i < campos.length; i++) {
                                                    
                                                    if (camposEncriptados[j].equals(campos[i])) {
                                                        aux2 = 0;
                                                    }else if(camposEncriptados[j].equals("T")){
                                                        aux2 = 0;
                                                    }else if(camposEncriptados[j].equals("F")){
                                                        aux2 = 0;
                                                    }else{
                                                        System.out.println("entro1");
                                                        aux2 = aux2 + 1;
                                                    }
                                                }
                                                if (aux2 == campos.length) {
                                                    errores += "Error -> Alguno de los campos que desea encriptar no existe\n";
                                                    
                                                    return false;
                                                }
                                            }
                                            return true;

                                        } else {
                                            errores += "Error -> Alguno de los campos que desea encriptar no existe\n";
                                        }
                                    }
                                }
                            } else {
                                //System.out.println("Error -> Expected: LONGITUD - but Founded: " + tokens.get(7));
                                errores += "Error -> El comando" + tokens.get(3)+" no se reconoce\n";
                                return false;
                            }
                        } else {
                            //System.out.println("Error -> Expected: " + tokens.get(6) + " in " + tokens.get(4));
                            return false;
                        }
                    } else {
                        //System.out.println("Error -> Expected: CLAVE - but Founded: " + tokens.get(5));
                        errores += "Error -> El comando" + tokens.get(5)+" no se reconoce\n";
                        return false;
                    }
                } else {
                    //System.out.println("Error no se puede crear tabla con campos repetidos!");
                    return false;
                }
            } else {
                //System.out.println("Error -> Expected: CAMPOS - but Founded: " + tokens.get(3));
                errores += "Error -> El comando" + tokens.get(3)+" no se reconoce\n";
                return false;
            }
        }
        //System.out.println("Error falta o hay un exceso de argumentos!!");
        errores += "Error falta o hay un exceso de argumentos\n";
        return false;
    }

    private static boolean isNumeric(String cadena) {
        int num;
        try {
            num = Integer.parseInt(cadena);
            return num > 0 && num < 256;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
