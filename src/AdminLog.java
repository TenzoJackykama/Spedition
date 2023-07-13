import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class AdminLog creazione log in Admin
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class AdminLog extends LogInPanel{

    /**
     * Costruttore AdminLog
     * Imposta pannello log in admin
     */
    public AdminLog(){
        super("admin");
        this.setBackground(Color.cyan);
    }

    /**
     * Aggiunge oggetto in ascolto degli eventi
     * @param obj Oggetto dedicato ascolto eventi
     */
    @Override
    public void addAction(Object obj) {
        super.switchLog.addActionListener((ActionListener) obj);
        super.continueButton.addActionListener((ActionListener) obj);
    }


}
