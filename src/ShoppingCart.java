import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Costruttore ShoppingCart
 * Classe ShoppingCart creazione ordine
 * @see Date usata per impostare la data corrente del momento di ordinazione
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 2L;
    /**
     * Spedition_type identifica tipo della spedizione
     * stringa "base" indica non assicurata
     */
    private String spedition_type = "base";
    private String nameuser;
    private String destination;
    private String date;
    private String state = "IN PREPARAZIONE";
    private String weight;
    protected String code;

    /**
     * Stati possibili per il singolo ordine
     */
    protected String [] stats = new String[]{"IN PREPARAZIONE", "IN TRANSITO", "RICEVUTA", "FALLITA", "", ""};



    private Date datec;
    private final SimpleDateFormat  dateformat;

    /**
     *
     * Costruttore Shopping Cart
     * @param destination Destinazione ordine
     * @param weight Peso ordine
     * @param code Codice incrementale ordine
     * @param nameuser Nome utente
     */
    public ShoppingCart(String destination, String weight, String code, String nameuser){
        this.destination = destination;
        this.weight = weight;
        this.code = code;
        this.nameuser = nameuser;

        datec = new Date();
        dateformat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateformat.format(datec);
    }
    /**
     * Costruttore di default
     * @see Database
     */
    public ShoppingCart() {

        dateformat = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * Restituisce peso ordine
     * @return
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Imposta peso ordine
     * @param weight
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * Restituisce codice ordine
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Imposta codice ordine
     * @param code codice ordine
     */
    public void setCode(String code) {
        this.code = String.valueOf(code);
    }

    /**
     * Restituisce nome utente ordine
     * @return
     */
    public String getNameuser() {
        return nameuser;
    }

    /**
     * Imposta nome utente ordine
     * @param nameuser
     */
    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    /**
     * Restituisce indirizzo destinazione ordine
     * @return
     */
    public String getDestination() {
        return destination;
    }


    /**
     * Imposta destinazioe ordine
     * @param destination Indirizzo
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Restituisce Classe Data
     * @return
     */
    public Date getDatec() {
        return datec;
    }

    /**
     * Imposta classe Data
     * @see Date
     * @param datec Classe Data
     */
    public void setDatec(Date datec) {
        this.datec = datec;
    }

    /**
     * Restituisce stato ordine
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Imposta lo stato di spedizione
     * @param state stato spedizione
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Restituisce data ordine
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Imposta la data dell' ordine
     * @param date data ordine
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Restituisce stati possibili ordine
     * @return
     */
    public String[] getStats() {
        return stats;
    }

    /**
     * Imposta gli stati possibili di spedizione
     * @param stats Array stati di pedizione possibili
     */
    public void setStats(String[] stats) {
        this.stats = stats;
    }

    /**
     * Restituisce tipo della spedizione
     * @return
     */
    public String getSpedition_type() {
        return spedition_type;
    }

    /**
     * Imposta tipo della spedizione
     * @param spedition_type tipo ordine corrente
     */
    public void setSpedition_type(String spedition_type) {
        this.spedition_type = spedition_type;
    }

}
