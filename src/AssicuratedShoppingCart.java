/**
 * Clase AssicuratedShoppingCart per la creazione di ordini assicurati
 * estende Shopping cart con gli stati di risposta per un' eventuale fallimento dell' ordine
 * @author Bulla Andrea
 * @version 1.0.0
 */

public class AssicuratedShoppingCart extends ShoppingCart {

    private static final long serialVersionUID = 3L;
    /**
     * Costruttore AssicuratedShoppingCart
     * Aggiunta stati per il rimborso
     * @param destination Destinazione ordine
     * @param weight peso dell' ordine
     * @param code Codice incrementake
     * @param nameuser Nome utente di riferimento alla spedizione
     */
    public AssicuratedShoppingCart(String destination, String weight, String code, String nameuser) {
        super(destination, weight, code, nameuser);
        super.stats[4] = "RIMBORSO RICHIESTO";
        super.stats[5] = "RIMBORSO EROGATO";
        setSpedition_type("assicurated");

    }

    /**
     * Costruttore di default
     * @see Database per utilizzo
     */
    public AssicuratedShoppingCart() {
        super();
    }

    /**
     * Aggiunge A all numero di spedizione indicando che è assicurata
     * @param code codice ordine
     */
    @Override
    public void setCode(String code){
        String combinazione = getSpedition_type();
        combinazione = combinazione.substring(0, 1);
        this.code = (String) String.valueOf(code)+combinazione;
    }
}