import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Classe creazione pannello log in utente
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class LogInUser extends LogInPanel{

    /**
     * Costruttore pannello log in utente
     */
    public LogInUser() {
        super("username");

    }

    /**
     * Imposta pannello log in utente
     */
    @Override
    public void setPanel() {
        super.setPanel();
        setBackground(Color.green);
    }

    /**
     * Aggiunta ascolto oggetto scolto eventi
     * @see LogInPanel
     * Componenti che effettuano eventi
     * @param obj Oggetto per ascolto eventi
     */
    @Override
    public void addAction(Object obj) {
        super.switchLog.addActionListener((ActionListener) obj);
        super.continueButton.addActionListener((ActionListener) obj);
    }


}
