
import java.io.Serializable;
/**
 * Classe Person dati utente
 * @author Bulla Andrea
 * @version 1.0.0
 */

public class Person implements Serializable {


    private static final long serialVersionUID = 1L;

    private String nameuser;
    private String password;
    private String addres;

    private ShoppingCart sh;
    private int ordernumber = 0;

    /**
     * Costruttore
     * @param name Nome utente
     * @param password Password utente
     * @param addres indirizzo utente
     */
    public Person (String name, String password, String addres){
        this.nameuser = name;
        this.password = password;
        this.addres = addres;
    }

    /**
     * Restituisce
     * @return
     */
    public String getNameuser() {
        return nameuser;
    }

    /**
     * Imposta nome utente
     * @param nameusere
     */
    public void setNameuser(String nameusere) {
        this.nameuser = nameusere;
    }

    /**
     * Restituisce password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce indirizzo
     * @return
     */
    public String getAddres() {
        return addres;
    }

    /**
     * Imposta indirizzo
     * @param addres
     */
    public void setAddres(String addres) {
        this.addres = addres;
    }

    /**
     * Restituisce numero ordine
     * @return
     */
    public int getOrdernumber() {
        return ordernumber;
    }

    /**
     * Imposta numero ordine
     * @param ordernumber
     */
    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    /**
     * Incremento codice numero ordini
     * @return numero ordini effettuati
     */
    public int incrementCode() {
        ordernumber += 1;
        return ordernumber;
    }
}

