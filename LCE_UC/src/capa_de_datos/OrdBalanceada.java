package capa_de_datos;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class OrdBalanceada {
    
    private static int countColumn;

    public void sort(int campo, String rutaOriginal,String orden) throws FileNotFoundException, IOException, ParseException {
        String rutaF1 = "F1.csv";
        String rutaF2 = "F2.csv";
        int part;
        CsvReader Original = new CsvReader(rutaOriginal, ';');
        Original.readHeaders();
        countColumn = Original.getHeaderCount();
        Original.close();
        while ((part = Particiona(rutaOriginal,rutaF1,rutaF2,campo,orden)) > 1) {
            Fusiona(rutaOriginal,rutaF1,rutaF2,part,campo,orden);
        }
        DeleteFile(rutaF1, rutaF2);
        System.out.println("Se ha terminado la ordenacion");
    }

    public static void Fusiona(String rutaOriginal, String rutaF1, String rutaF2, int part, int campo,String orden) throws FileNotFoundException, IOException, ParseException {
        int limit = 1;
        CsvWriter Original = new CsvWriter(new FileWriter(rutaOriginal), ';');
        CsvReader F1 = new CsvReader(rutaF1, ';');
        CsvReader F2 = new CsvReader(rutaF2, ';');
        String auxR1 = "", auxR2 = "";
        boolean end1 = true, end2 = true;
        if (F1.readRecord()) {
            auxR1 = F1.get(campo);
            end1 = false;
        }
        if (F2.readRecord()) {
            auxR2 = F2.get(campo);
            end2 = false;
        }
        while ((!end1 || !end2) && limit < part) {
            while (!end1 && !end2 && (orderIt(auxR1, F1.get(campo),orden) && orderIt(auxR2, F2.get(campo),orden))) {
                if (ordenar(F1.get(campo),F2.get(campo),orden)) {
                    for (int i = 0; i < countColumn; i++) {
                        Original.write(F1.get(i));
                    }
                    auxR1 = F1.get(campo);
                    Original.endRecord();
                    end1 = true;
                    if (F1.readRecord()) {
                        if (orderIt(auxR1, F1.get(campo),orden)) {
                            auxR1 = F1.get(campo);
                        }
                        end1 = false;
                    }
                } else {
                    for (int i = 0; i < countColumn; i++) {
                        Original.write(F2.get(i));
                    }
                    auxR2 = F2.get(campo);
                    Original.endRecord();
                    end2 = true;
                    if (F2.readRecord()) {
                        end2 = false;
                        if (orderIt(auxR2, F2.get(campo),orden)) {
                            auxR2 = F2.get(campo);
                        }
                    }
                }
            }
            while (!end1 && orderIt(auxR1, F1.get(campo),orden)) {
                for (int i = 0; i < countColumn; i++) {
                    Original.write(F1.get(i));
                }
                auxR1 = F1.get(campo);
                Original.endRecord();
                end1 = true;
                if (F1.readRecord()) {
                    end1 = false;
                }
            }
            while (!end2 && orderIt(auxR2, F2.get(campo),orden)) {
                for (int i = 0; i < countColumn; i++) {
                    Original.write(F2.get(i));
                }
                auxR2 = F2.get(campo);
                Original.endRecord();
                end2 = true;
                if (F2.readRecord()) {
                    end2 = false;
                }
            }
            if (!end1) {
                auxR1 = F1.get(campo);
            }
            if (!end2) {
                auxR2 = F2.get(campo);
            }
            limit++;
        }
        Original.close();
        F1.close();
        F2.close();
    }

    public static int Particiona(String rutaOriginal, String rutaF1, String rutaF2, int campo,String orden) throws FileNotFoundException, IOException, ParseException {
        int particiona = 1;
        CsvReader Original = new CsvReader(rutaOriginal, ';');
        CsvWriter F1 = new CsvWriter(new FileWriter(rutaF1), ';');
        CsvWriter F2 = new CsvWriter(new FileWriter(rutaF2), ';');
        boolean end = true;
        boolean whatFile = true;
        String auxReg = "";
        if (Original.readRecord()) {
            end = false;
        }
        while (!end) {
            if (whatFile) {
                for (int i = 0; i < countColumn; i++) {
                    F1.write(Original.get(i));
                }
                F1.endRecord();
                end = true;
            } else {
                for (int i = 0; i < countColumn; i++) {
                    F2.write(Original.get(i));
                }
                F2.endRecord();
                end = true;
            }
            auxReg = Original.get(campo);
            if (Original.readRecord()) {
                end = false;
            }
            if (!end && ordenar(Original.get(campo), auxReg,orden)) {
                whatFile = !whatFile;
                particiona++;
            }
        }
        Original.close();
        F1.close();
        F2.close();
        return particiona;
    }

    public static boolean ordenar(String R1, String R2,String orden) {
        switch (orden){
            case "desc":
                return R1.compareTo(R2) > 0;
            case "asc":
                return R1.compareTo(R2) < 0;
        }
        return false;
    }

    public static boolean orderIt(String R1, String R2, String orden) {
        switch (orden){
            case "desc":
                return R1.compareTo(R2) >= 0;
            case "asc":
                return R1.compareTo(R2) <= 0;
        }
        return false;
    }

    public static void DeleteFile(String rutaF1, String rutaF2) {
        boolean existe = new File(rutaF1).exists();
        if (existe) {
            File ficheroAux = new File(rutaF1);
            ficheroAux.delete();
        }
        existe = new File(rutaF2).exists();
        if (existe) {
            File ficheroAux = new File(rutaF2);
            ficheroAux.delete();
        }
    }

    void sort(int pos, File archivo, String get) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
