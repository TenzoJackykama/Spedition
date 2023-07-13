import java.io.*;
import java.util.ArrayList;


/**
 * Classe usata per leggere e scrivere da file di testo
 * La scelta di usare generics e di poter leggere e scrivere
 * su file differenti diveresi tipi di classi.
 * @param <T> Generics
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class ToFromDb <T>{

    ArrayList<T> array;
    File makefile;
    String nomefile;

    /**
     * Legge dal file fornito creando
     * un ArrayList con il contenuto del file passato
     * di tipo indicato da param T
     * @param nomefile file da utilizare

     *
     */
    public ToFromDb(String nomefile){
        this.nomefile = nomefile;
        try {
            makefile = new File(nomefile);


            if (!makefile.createNewFile()) {
                if (makefile.length() != 0) {
                    FileInputStream file = new FileInputStream(nomefile);
                    ObjectInputStream ois = new ObjectInputStream(file);
                    array = (ArrayList<T>) ois.readObject();

                    ois.close();
                    file.close();
                }
                else {
                    array = new ArrayList<T>();
                }
            } else {
                makefile.createNewFile();

                array = new ArrayList<T>();

            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println("error io "+e);
        } catch (ClassNotFoundException e) {
            System.out.println(" class not found");
        }
    }

    /**
     * Salvataggio su file
     */
    public void  saveToMemory(){

        try {
            FileOutputStream fileo = new FileOutputStream(nomefile);
            ObjectOutputStream oos = new ObjectOutputStream(fileo);

            oos.writeObject(array);

            oos.close();
            fileo.close();

        }catch (FileNotFoundException e) {
            System.out.println("file not found "+e);
        } catch (IOException e) {
            System.out.println("error io "+e);
        }
    }

    /**
     * Ritorna ArrayList del tipo generato
     * @return ArrayList generato
     */
    public ArrayList<T> getArray() {
        return array;
    }

    /**
     * Imposta array locale a quello dato come parametro formale
     * @param array ArrayList generato
     */
    public void setArray(ArrayList<T> array) {
        this.array = array;
    }
}


