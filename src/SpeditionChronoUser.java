import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Classe gestione cronologia  ordini utente
 * Laggiornamento per ogni cambio di stato delle spedizioni è stato fatto in seguito al riscontro di meno
 * errori e mantenimento delle funzioni proprie della classe in questione, SpeditionChronoUser.
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class SpeditionChronoUser extends SpeditionChrono {

    /**
     * Costruttore SpeditionChronoUser
     * Seleziona le spedizioni relative a param name
     * Crea la tabella cronologia ordini
     * @param name nome dell' utente autoenticato
     */
    public SpeditionChronoUser(String name) {
        super();
        super.db.wrapSpeditionForName(name);
        super.makeTable();
    }

    /**
     * Alternative per il cambio dello stato di un ordine
     * per ogni cambio dello stato, aggiornamento dell' ArrayList ordini
     * comprendente tutte le spedizioni, all' index originale
     * @see Database
     * @param mouseEvent Evento provocato dall' azisone del mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        super.row = super.getTable().rowAtPoint(mouseEvent.getPoint());
        String column_value = (String) getTable().getValueAt(row, column_spedition_type);

        if (db.getArrayShoppingcart().get(row).getSpedition_type().equals("assicurated") && (db.getArrayShoppingcart().get(row).getState().equals("FALLITA"))) {
            String[] item = new String[]{"FALLITA", "RIMBORSO RICHIESTO"};
            super.comboBox = new JComboBox(item);

            int input = JOptionPane.showConfirmDialog(this, super.comboBox, "choose", JOptionPane.DEFAULT_OPTION);

            if (input == JOptionPane.OK_OPTION) {
                super.item = (String) comboBox.getSelectedItem();
                getTable().setValueAt(super.item, row, column_spedition_type);
                db.getArrayShoppingcart().get(row).setState(super.item);
                db.getArrayShoppingCartTmp().set(db.getIndex_referance().get(row), db.getArrayShoppingcart().get(row));
                db.upDateorderUser();
            }
        }
    }
}