import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe SingIn creazione pannello registrazione utente
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class SingIn extends LogInPanel {

    private final JLabel addreslabel = new JLabel("address");
    private final JTextField addrestextfield = new JTextField(20);

    private final LogInUser logInUser = new LogInUser();

    /**
     * Costruttore classe SingIn
     * imposta pannello registrazione utente
     */
    public SingIn() {
        super("username");
    }

    /**
     * Aggiunge i campi necessari per l'indirizzo e-mail
     */
    @Override
    public void setPanel() {
        super.setPanel();
        setBackground(Color.magenta);
        add(addreslabel);
        add(addrestextfield);

    }

    /**
     * Aggiunge ascoltatore eventi
     */
    @Override
    public void addAction(Object obj) {
        super.switchLog.addActionListener((ActionListener) obj);
        super.continueButton.addActionListener((ActionListener) obj);

    }

    /**
     * Ritorna oggetto addrestextfield
     * @return campo testo indirizzo
     */
    public JTextField getAddrestextfield() {
        return addrestextfield;
    }

}
