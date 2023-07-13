import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * Classe per instanziare lo spazio profilo utente
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class UserSpace extends JFrame implements ActionListener, WindowListener {

    private JPanel spaceOrdination = new JPanel();
    private JButton ordinate = new JButton("ordinate");
    private JButton up = new JButton("up");
    private JButton down = new JButton("down");
    private JLabel weight = new JLabel("weight");
    private JTextField keepweight = new JTextField(10);
    private JLabel destination = new JLabel("spediton addres");
    private JTextField destination_textfield = new JTextField(40);
    private JButton renderAssicurated = new JButton("Assicurate");

    private JMenu menu = new JMenu("Menu");
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem order_chronology= new JMenuItem("order chronology");
    private JMenuItem order_make = new JMenuItem("order");
    private JMenuItem logOutButton = new JMenuItem("log out");
    private SpeditionChronoUser scu;


    private String pattern = "###.##";
    private DecimalFormat df = new DecimalFormat(pattern);
    private Float weight_l = 0.00F;


    private Database db;
    private ShoppingCart sh;
    private AssicuratedShoppingCart ash;

    /**
     *  Inizializza i possibili pannelli da utilizzare e
     *  il menu per il frame ospitante
     * @param db database dell' utente identificato
     * @see Start
     * @see Database
     */
    public UserSpace(Database db){

        super("spedition");
        this.db = db;
        scu = new SpeditionChronoUser(db.getPsn().getNameuser());

        menu.add(order_chronology);
        menu.add(order_make);
        menu.add(logOutButton);
        menuBar.add(menu);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(700, 500));
        setPanel();

        order_chronology.addActionListener(this);
        order_make.addActionListener(this);
        logOutButton.addActionListener(this);

        this.add(spaceOrdination);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }

    /**
     * Imposta il pannello e le azioni disponibili
     */
    private void setPanel(){

        spaceOrdination.setBackground(Color.GREEN);
        spaceOrdination.add(up);
        spaceOrdination.add(down);
        spaceOrdination.add(weight);
        keepweight.setText("0.00");
        spaceOrdination.add(keepweight);
        spaceOrdination.add(destination);
        spaceOrdination.add(destination_textfield);
        spaceOrdination.add(ordinate);
        spaceOrdination.add(renderAssicurated);
        spaceOrdination.setVisible(true);
        setAction();
     }

    /**
     * Azioni disponibili per il pannello
     */
    private void setAction(){
        up.addActionListener(this);
        down.addActionListener(this);
        ordinate.addActionListener(this);
        renderAssicurated.addActionListener(this);
     }

    /**
     * Ritorna istanza Shopping cart
     * @return ShoppingCart
     */
    private ShoppingCart makeShoppingCart(){
        return new ShoppingCart(destination_textfield.getText(), keepweight.getText(), String.valueOf(db.getPsn().getOrdernumber()), db.getPsn().getNameuser());
     }

    /**
     * Ritorna istanza Shpping cart assicurata
     * @param order_type tipo della spedizione deve essere AssicuratedShoppingCart
     * @see AssicuratedShoppingCart
     * @see Start
     * @return AssicuratedShoppingCart
     */
    private ShoppingCart makeShoppingCart(String order_type){
        return new AssicuratedShoppingCart(destination_textfield.getText(), keepweight.getText(), String.valueOf(db.getPsn().getOrdernumber()), db.getPsn().getNameuser());
     }

    /**
     * Azioni possibili in UserSpace
     * @param actionEvent evento intercettato dalla classe sottscritta
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action =  actionEvent.getActionCommand();
        switch (action) {
            case "up": {

                String str = keepweight.getText();
                Float weight_l = Float.parseFloat(str);
                weight_l = (weight_l + 0.01F);
                str = df.format(weight_l);
                keepweight.setText(str);
            }
            break;
            case "down": {

                String str = keepweight.getText();
                Float weight_l = Float.parseFloat(str);

                if (weight_l > 0.0) {
                    weight_l = (weight_l - 0.01F);
                    str = df.format(weight_l);
                    keepweight.setText(str);
                }
            }
            break;
            case "ordinate": {
                db.getPsn().incrementCode();
                sh = makeShoppingCart();
                db.getArrayShoppingcart().add(sh);
                db.upDatePersonStatus();

            }
            break;
            case "Assicurate":{
                db.getPsn().incrementCode();
                ash = (AssicuratedShoppingCart) makeShoppingCart("Assicurate");
                ash.setCode(ash.getCode());
                db.getArrayShoppingcart().add(ash);

                db.upDatePersonStatus();
            }
            break;
            case "order chronology":{
                scu = new SpeditionChronoUser(db.getPsn().getNameuser());

                this.getContentPane().remove(spaceOrdination);
                this.add(scu);
                this.getContentPane().repaint();
                this.getContentPane().revalidate();
            }
            break;
            case "order": {


                scu.getDb().upDateorderUser();
                this.getContentPane().remove(scu);
                this.add(spaceOrdination);
                this.getContentPane().repaint();
                this.getContentPane().revalidate();
            }
            break;
            case "log out": {
                this.dispose();
                new Start();


            }
        }
    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Aggiorna in chiusura del frame il database
     * @param windowEvent evento intercettato dal frame
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        scu.getDb().upDateorderUser();
        db.upDatePersonStatus();
    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    /**
     * Laciato intenzionalmente vuoto
     * non necessario
     * @param windowEvent evento provocato azione frame
     */
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
