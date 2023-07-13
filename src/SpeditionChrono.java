import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * classe SpeditionChrono
 * imposta:
 * tabella della cronologia ordini richiesta
 * usa classe random per generare numeri casuali, usati per selezione
 * stato cliente
 * @see Random
 *
 * Alucuni metodi sono stati lasciati vuoti per evetuali modifiche future
 */

public class SpeditionChrono extends JPanel implements MouseListener, ItemListener {

    private Random rand = new Random();
    private int upperbound = 4;
    private int int_random = rand.nextInt(upperbound);
    private int stats_column = 5;

    private TableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private String [] columns = {"name", "weight", "addres", "data", "code", "state"};
    private String [][] data;

    protected JComboBox comboBox = null;
    protected final int column_spedition_type = 5;
    protected int row;

    protected String [] stats_base = new String[]{"IN PREPARAZIONE", "IN TRANSITO", "RICEVUTA", "FALLITA", "", ""};
    protected String [] stats = new String[]{"IN PREPARAZIONE", "IN TRANSITO", "RICEVUTA", "FALLITA", "RIMBORSO RICHIESTO", "RIMBORSO EROGATO"};
    protected String item;


    /**
     *  Database per la gestione della storia delle spedizioni
     */
    protected Database db = new Database();

    /**
     * Costruttore SpeditionChrono
     * Imposta:
     * colore pannello
     * visibilita true
     */
    public SpeditionChrono(){

        this.setBackground(Color.GREEN);
        this.setVisible(true);

    }

    /**
     * Crea una JTabel con :
     * righe, spedizioni
     * colonne, specifiche dell' ordine
     * Imposta TableModel necessario per modifica righe tabella
     * @see SpeditionChronoAdminThread
     * @see Database
     * @see ShoppingCart
     * @see AssicuratedShoppingCart
     * @return
     */
    private JTable createTable() {


        data = new String[db.getArrayShoppingcart().size()][6];

        for(int i = 0; i<db.getArrayShoppingcart().size(); i++) {
            data[i][0] = db.getArrayShoppingcart().get(i).getNameuser();
            data[i][1] = db.getArrayShoppingcart().get(i).getWeight();
            data[i][2] = db.getArrayShoppingcart().get(i).getDestination();
            data[i][3] = db.getArrayShoppingcart().get(i).getDate();
            data[i][4] = String.valueOf(db.getArrayShoppingcart().get(i).getCode());
            data[i][5] = db.getArrayShoppingcart().get(i).getState();
        }

        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        return new JTable(tableModel);

    }

    /**
     * Imposta la tabella per essere aggiunta al suo contenitore
     */
    protected void makeTable(){

        table = createTable();
        table.addMouseListener(this);
        scrollPane = new JScrollPane(table);
        scrollPane.setMaximumSize(new Dimension(400, 300));
        table.setFillsViewportHeight(true);
        this.add(scrollPane);

    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {

    }

    /**
     * Restituisce stati ordine assicurato
     * @return
     */
    protected String[] getPossibleSpeditionTypeAssicurate() {
        AssicuratedShoppingCart ash = new AssicuratedShoppingCart();
        return ash.getStats();
    }

    /**
     * Restituisce stati ordine
     * @return
     */
    protected String[] getPossibleSpeditionTypeBase() {
        ShoppingCart sh = new ShoppingCart();
        return sh.getStats();
    }

    /**
     * Restituisce database
     * @see Database
     * @return
     */
    public Database getDb() {
        return db;
    }

    /**
     * Imposta Database
     * @param db
     */
    public void setDb(Database db) {
        this.db = db;
    }

    /**
     * Restituisce tabella
     * @return
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Restituisce colonne tabella
     * @return
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * imposta colonna
     * @param columns colonna della tabella
     */
    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    /**
     * Restituisce dati tabella
     * @return
     */
    public String[][] getData() {
        return data;
    }

    /**
     * imposta i dati della tabella
     * @param data
     */
    public void setData(String[][] data) {
        this.data = data;
    }

    /**
     * Restituisce possibili stati spedizione
     * @return
     */
    public String[] getStats() {
        return stats;
    }

    /**
     * Imposta i possibili stati spedizione
     * @param stats stati spedizione
     */
    public void setStats(String[] stats) {
        this.stats = stats;
    }

}
