import java.util.ArrayList;

/**
 * Classe creazione database:
 * utenti
 * ordini
 * Implementa la classe ToFromDB per gestire letture e scritture dai file @see ToFromDb
 * utilizzo per utenti o per ordini dato dal costruttore scelto
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class Database {

    /**
     *  nome file registro utenti
     *  nome file spedizioni totali effetuate
     */
    private String nomefile = "utenti.txt";
    private String nomefilespedition = "spedition.txt";
    private Person psn = null;
    private ArrayList<Person> arraypsn;
    private String msg_user = null;

    private ArrayList<ShoppingCart> arrayShoppingcart;
    private ArrayList<ShoppingCart> arrayShoppingCartTmp = new ArrayList<>();
    private  ToFromDb rwpsn;
    private final ToFromDb rwsh;
    private int userindex = 0;
    /**
     * Index degli ordini prelevati per nome utente
     * da ArrayList totale ordini
     */
    private ArrayList<Integer> index_referance = new ArrayList<Integer>();

    /**
     * Costruttore database per registrazione utente
     * aggiorna ArrayList delgli utenti
     * @param name Nome utente
     * @param password Password utente
     * @param addres Inidirizzo fornito dall' utente
     */
    public Database(String name, String password, String addres) {

        psn = new Person(name, password, addres);
        rwpsn = new ToFromDb<Person>(nomefile);
        arraypsn = (ArrayList<Person>) rwpsn.getArray();

        rwsh = new ToFromDb<ShoppingCart>(nomefilespedition);
        arrayShoppingcart = (ArrayList<ShoppingCart>) rwsh.getArray();

    }

    /**
     * Costruttore database log in utente
     * definisce index dell ArrayList degli utenti relativo all' utente autenticato
     * definisce psn come istanza dell' utente utilizzata nella sessione.
     * @param name Nome utente
     * @param password Password utente
     */
    public  Database(String name, String password) {

        rwpsn = new ToFromDb<Person>(nomefile);
        arraypsn = rwpsn.getArray();
        rwsh = new ToFromDb<ShoppingCart>(nomefilespedition);
        arrayShoppingcart = (ArrayList<ShoppingCart>) rwsh.getArray();

        for (int i = 0; i < arraypsn.size(); i++) {

            if (arraypsn.get(i).getNameuser().equals(name) && arraypsn.get(i).getPassword().equals(password)) {
                psn = arraypsn.get(i);
                userindex = i;

            }
        }
    }

    /**
     * Costruttore database gestione cronologia ordini
     */
    public Database(){
        rwsh = new ToFromDb<ShoppingCart>(nomefilespedition);

        arrayShoppingcart = (ArrayList<ShoppingCart>) rwsh.getArray();
        arrayShoppingCartTmp = new ArrayList<>(arrayShoppingcart);
    }

    /**
     * Aggiornamento database per ogni ordine
     * salva l' appropriato utente e le modifiche al numero incrementale degli ordini
     */
    public void upDatePersonStatus() {
        arraypsn.set(userindex, psn);
        rwpsn.setArray(arraypsn);
        rwpsn.saveToMemory();

        rwsh.setArray(arrayShoppingcart);
        rwsh.saveToMemory();
    }

    /**
     * Aggiornamento ordini da parte dell'admin
     */
    public void upDateOrderDbAdmin(){
        rwsh.setArray(arrayShoppingcart);
        rwsh.saveToMemory();
    }

    /**
     * Aggiornamento ordini utente
     */
    public void upDateorderUser(){
        reInsertUserOrder();
        rwsh.setArray(arrayShoppingCartTmp);
        rwsh.saveToMemory();
    }

    /**
     * selezione ordini rispettive all' utente autenticato
     * @param name Nome utente
     */
    public void wrapSpeditionForName(String name){
        ArrayList<ShoppingCart> list = new ArrayList<ShoppingCart>();
        for (int i = 0; i<arrayShoppingcart.size(); i++){
            if(name.equals(arrayShoppingcart.get(i).getNameuser())) {
                list.add(arrayShoppingcart.get(i));
                index_referance.add(i);
            }
        }
        arrayShoppingcart = list;
    }

    /**
     * Reinserimento in ArrayList degli ordini utente al posto originale di lettura
     * da file spedizioni.txt
     */
    private void reInsertUserOrder(){

        int count = 0;
        while (count<arrayShoppingcart.size())
        for (int i = 0; i<arrayShoppingCartTmp.size(); i++){
            if((arrayShoppingCartTmp.get(i).getNameuser() == arrayShoppingcart.get(count).getNameuser())
                    && (arrayShoppingCartTmp.get(i).getCode() == arrayShoppingcart.get(count).getCode())){
                arrayShoppingCartTmp.set(i, arrayShoppingcart.get(count));
                count++;
            }
        }
    }

    /**
     * Controllo utente gia esistente
     * @return True esistente. False non esistente
     */
    public boolean checkExistingUser(){
        for (int i = 0; i<arraypsn.size(); i++){
            if (arraypsn.get(i).getNameuser().equals(psn.getNameuser())) {
                return true;
            }
        }

        arraypsn.add(psn);
        rwpsn.setArray(arraypsn);
        rwpsn.saveToMemory();

        return false;
    }

    /**
     * Restituisce la persona della classe corrente
     * @return Oggetto person
     * @see Person
     */
    public Person getPsn() {
        return psn;
    }

    /**
     * Ritorna il nome del file degli ordini
     * @return
     */
    public String getNomefilespedition() {
        return nomefilespedition;
    }

    /**
     * Imposta il nome del file spedizioni
     * @param nomefilespedition
     */
    public void setNomefilespedition(String nomefilespedition) {
        this.nomefilespedition = nomefilespedition;
    }

    /**
     * Restituisce l'index nel database dell' utente autenticato
     * @return
     */
    public int getUserindex() {
        return userindex;
    }

    /**
     * Imposta l'index del' uente autenticato
     * @param userindex
     */
    public void setUserindex(int userindex) {
        this.userindex = userindex;
    }

    /**
     * Restituisce l ArrayList degli ordini
     * @see Database
     * @return
     */
    public ArrayList<ShoppingCart> getArrayShoppingcart() {
        return arrayShoppingcart;
    }

    /**
     * Imposta ArrayList degli ordini
     * @param arrayShoppingcart
     */
    public void setArrayShoppingcart(ArrayList<ShoppingCart> arrayShoppingcart) {
        this.arrayShoppingcart = arrayShoppingcart;
    }

    /**
     * Restituisce l ArrayList utenti
     * @return
     */
    public ArrayList<Person> getArraypsn() {
        return arraypsn;
    }

    /**
     * Imposta ArrayList utenti
     * @param arraypsn
     */
    public void setArraypsn(ArrayList<Person> arraypsn) {
        this.arraypsn = arraypsn;
    }

    /**
     * Ordina alfabeticamente Arraylist ordini
     */
    public void sortShoppingCartList() {
        arrayShoppingcart.sort((s1, s2) -> s1.getNameuser().compareTo(s2.getNameuser()));
    }

    /**
     * Restituisce alfabeticamente Arraylist ordini usato come buffer
     * @see Database
     */
    public ArrayList<ShoppingCart> getArrayShoppingCartTmp() {
        return arrayShoppingCartTmp;
    }

    /**
     * Imposta alfabeticamente Arraylist ordini usato come buffer
     * @see Database
     * @param arrayShoppingCartTmp
     */
    public void setArrayShoppingCartTmp(ArrayList<ShoppingCart> arrayShoppingCartTmp) {
        this.arrayShoppingCartTmp = arrayShoppingCartTmp;
    }

    /**
     * Ritorna ArrayList per mantenere index degli ordini relativi alle persone autenticate
     * @return
     */
    public ArrayList<Integer> getIndex_referance() {
        return index_referance;
    }

    /**
     * Imposta ArrayList per mantenere index degli ordini relativi alle persone autenticate
     * @param index_referance
     */
    public void setIndex_referance(ArrayList<Integer> index_referance) {
        this.index_referance = index_referance;
    }

}