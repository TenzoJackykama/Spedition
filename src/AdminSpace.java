import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.TimerTask;

/**
 * Class AdminSpace creazione frame spazio di lavoro admin
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class AdminSpace extends TimerTask implements WindowListener, ActionListener {

    /**
     * Database dedicato alla cronologia ordini.
     * @see Database
     * @see SpeditionChronoAdmin
     */
    private Database db = new Database();
    private SpeditionChronoAdminThread speditionChronoAdmin = new SpeditionChronoAdminThread();

    private CustomTabelCellrendere customTabelCellrendere = new CustomTabelCellrendere();

    private Random rand = new Random();

    private  int random_number_state;
    private int upper_bound_row = speditionChronoAdmin.getDb().getArrayShoppingcart().size();
    private int upper_bound_column = 5;
    private int random_number_row;
    private int random_number_column;


    private JFrame adminFrame = new JFrame("spedition chronology");
    private JMenu menu = new JMenu("Menu");
    private JMenuItem menuItem = new JMenuItem("log out");
    private JMenuBar menuBar = new JMenuBar();

    int counter = 0;

    /**
     * Imposta stato frame AdminSpace
     */
    public AdminSpace(){

        adminFrame.addWindowListener(this);
        adminFrame.setSize(700, 500);

        menu.add(menuItem);
        menuBar.add(menu);
        menuItem.addActionListener(this);
        adminFrame.setJMenuBar(menuBar);

        speditionChronoAdmin.getTable().setDefaultRenderer(String.class, customTabelCellrendere);
        speditionChronoAdmin.setVisible(true);
        adminFrame.add(speditionChronoAdmin);
        adminFrame.setVisible(true);


    }

    /**
     * Scandisce il tempo di cambio degli stati e il colore associato
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        random_number_row = rand.nextInt(upper_bound_row);
        random_number_column = rand.nextInt(upper_bound_column);

        String state_for_change = getPossibleState();
        speditionChronoAdmin.getTable().repaint();
        if(!speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getState().equals("FALLITA")
            && !speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getState().equals("RIMBORSO EROGATO")) {
                speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).setState(state_for_change);
                speditionChronoAdmin.getTable().setValueAt(state_for_change, random_number_row, 5);
                speditionChronoAdmin.getDb().upDateOrderDbAdmin();
        }

        counter++;
    }

    /**
     * Individua il tipo di spedizione e sceglie random un tipo di stato
     * se lo stato è "in transito" con una certa prbabilita diventa "fallta"
     * @see ShoppingCart
     * @see AssicuratedShoppingCart
     * @return Stato da cambiare alla spedizione
     */
    private String getPossibleState(){
        int fail_probability = rand.nextInt(6);
        int length_ = 0;
        if (speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getSpedition_type().equals("assicurated"))
            length_ = speditionChronoAdmin.getStats().length;
        else if (speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getSpedition_type().equals("base"))
            length_ = speditionChronoAdmin.getStats().length - 2;   //less stats for base order object

        random_number_state = rand.nextInt(length_);

        String [] states = speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getStats();
        if(fail_probability == 0 && speditionChronoAdmin.getDb().getArrayShoppingcart().get(random_number_row).getState().equals("IN TRANSITO"))
            random_number_state = 3; //fail state

        return states[random_number_state];
    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Aggiornamento cronologia ordini a chiusura AdminSpace
     * @param windowEvent Evento provocato dalla chiusura del frame
     *
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        speditionChronoAdmin.getDb().upDateOrderDbAdmin();
        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    /**
     * Azione non prevista
     * @param windowEvent Evento provocato dalla finestra
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    /**
     * Log out dalla sessione corrente riproponendo la scelta del profilo
     * @param actionEvent Evento prvocato dalle azioni dei componenti di questa classe
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = (String) actionEvent.getActionCommand();

        if(action.equals("log out")){
            new Start();
            adminFrame.dispose();
        }
    }
}
