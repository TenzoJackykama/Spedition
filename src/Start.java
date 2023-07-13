import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

/**
 * classe Start presenta scelta di log in
 * Admin
 * Utente log in
 * Utente sing in
 * Viene presentata la scelta iniziale per acedere al log in utente oppure admin
 * Da ogni sezione si puo cambiare ad una delle tre, admin, log in, registrazione
 *
 */
public class Start implements ActionListener {

    private final JFrame frame = new JFrame("credential");


    private final SingIn si = new SingIn();
    private final LogInUser liu = new LogInUser();
    private final AdminLog al = new AdminLog();
    private Database db;


    private final JFrame adminframe = new JFrame("ADMIN");
    private final JFrame userframe = new JFrame("user");

    private final JMenu menu = new JMenu("Menu");
    private final JMenuBar menuBar = new JMenuBar();
    protected JMenuItem logMenuButton = new JMenuItem("sing in");


    private final Timer timer = new Timer();


    private boolean singinp = false;
    String title;

    private UserSpace us;
    private AdminSpace as;

    private final String adminname = "admin";
    private final String adminpassword = "123";

    /**
     * Costruttore classe Start
     * Inizializzazione frame, pannelli per utente e admin
     * frame iniziale con scelta tra utente e admin
     * relativa presentazioen spazio log o registrazone
     * il frame non scelto viene eliminato
     */
    public Start() {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        adminframe.setSize(700, 500);
        adminframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        userframe.setSize(700, 500);
        userframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        liu.setPanel();
        liu.addAction(this);

        al.setPanel();
        al.addAction(this);

        frame.setVisible(true);

        Object[] options = {"Admin",
                "user"};

        int scelta = JOptionPane.showOptionDialog(frame, "choose between", "profile",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

       switch(scelta){
            case 0: {

                frame.dispose();
                setAdminFrame();
            }
            break;
            case 1:{

                frame.dispose();
                setUsrFrame();
            }
            break;
        }

    }

    /**
     * Imposta frame Admin con relativo pannello
     */
    private void setAdminFrame(){

        adminframe.add(al);
        adminframe.setVisible(true);
        title = adminframe.getTitle();
    }

    /**
     * Imposta frame utente con relativo pannello
     */
    private void setUsrFrame(){

        menu.add(logMenuButton);
        menuBar.add(menu);
        userframe.setJMenuBar(menuBar);
        logMenuButton.addActionListener(this);
        userframe.add(liu);
        userframe.setVisible(true);


        title = userframe.getTitle();
    }

    /**
     * Imposta lo spazio utente
     * @param db Database utente
     * @return UserSpace
     */
    private UserSpace makeUserSpace(Database db){
        return new UserSpace(db);
    }

    /**
     * Imposta Database per untente autenticato
     * @param name Nome utente
     * @param password Password utente
     * @return Database
     */
    private Database makeLogDb(String name, String password){
        return new Database(name, password);
    }

    /**
     * Iatanzia la classe AdminSpace
     * @return AdminSpace
     */
    private void makeAdminSpace(){

        timer.scheduleAtFixedRate(new AdminSpace(), 30, 3000);
    }

    /**
     * Azioni possibili dal pannello
     * rispecchiano l'interattivita mostrata dai nomi dei componenti sull' interfaccia
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();

        if (command.equals("sing in")) {
            switchToSingIn();

        } else if (command.equals("log in")) {
            switchToLogIn();

        } else if (command.equals("back")) {

            if (title.equals("ADMIN")) {
                setUsrFrame();
                adminframe.setVisible(false);

            } else if (title.equals("user")) {
                setAdminFrame();
                userframe.setVisible(false);

            }

        } else if (command.equals("continue")) {
            if (title.equals("user") && singinp) {
                db = new Database(si.getNameField().getText(), si.getTextFieldPassword().getText(), si.getAddrestextfield().getText());

                boolean existing_user = db.checkExistingUser();
                if(existing_user) {
                    JOptionPane.showMessageDialog(userframe, "utente gia esistente", null, JOptionPane.WARNING_MESSAGE);
                    setRegistrationFieldBlank();

                }
                else if (!existing_user){
                    us = makeUserSpace(db);
                    userframe.setVisible(false);

                }

            } else if (title.equals("user") && !singinp) {
                System.out.println("continue con login" + liu.getNameField().getText() + " " + liu.getTextFieldPassword().getText());
                db = makeLogDb(liu.getNameField().getText(), liu.getTextFieldPassword().getText());

                if (db.getPsn() != null) {
                    System.out.println("log as" + db.getPsn().getNameuser());

                    us = makeUserSpace(db);
                    userframe.setVisible(false);
                } else
                    System.out.println("utente non trovato");

            }
            else if(title.equals("ADMIN")) {
                if (al.getNameField().getText().equals(adminname) && al.getTextFieldPassword().getText().equals(adminpassword)) {
                    makeAdminSpace();
                    adminframe.setVisible(false);
                }
            }
        }
    }

    /**
     * Imposta una riga vuota per componente
     */
    private void setRegistrationFieldBlank(){
        si.getNameField().setText("");
        si.getTextFieldPassword().setText("");
        si.getAddrestextfield().setText("");
    }

    /**
     * Cambio pannello
     * da log in a sin in
     */
    private void switchToSingIn(){
        userframe.getContentPane().removeAll();
        si.setPanel();
        si.addAction(this);
        logMenuButton.setText("log in");
        userframe.setJMenuBar(menuBar);
        userframe.add(si);
        userframe.getContentPane().repaint();
        userframe.revalidate();
        singinp = true;
    }

    /**
     * Cambio pannello da
     * sing in a log in
     */
    private void switchToLogIn(){
        userframe.getContentPane().removeAll();
        liu.setPanel();
        liu.addAction(this);
        logMenuButton.setText("sing in");
        userframe.setJMenuBar(menuBar);
        userframe.add(liu);
        userframe.getContentPane().repaint();
        userframe.revalidate();
        singinp = false;
    }
}