import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;

/**
 * Classe SpeditionChronoAdmin gestione cronologia ordini totale utenti
 * @author Bulla Andrea
 * @version 1.0.0
 */
public class SpeditionChronoAdminThread extends SpeditionChrono{

    /**
     * imposta:
     * ArrayList ordina alfabeticamente gli ordini
     */
    public SpeditionChronoAdminThread(){
        super();
        super.db.sortShoppingCartList();
        super.makeTable();
    }

    /**
     * Possibilita di cancellazione ordine se in stato
     * "fallita" oppure "rimborso erogato"
     * Aggiorna il database
     * @param mouseEvent Evento provocato dal mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int row = getTable().getSelectedRow();
        String column_value = (String)getTable().getValueAt(row, column_spedition_type);

       if (column_value.equals("FALLITA") || column_value.equals("RIMBORSO EROGATO")){
            String [] options_pane = {"Yes", "No"};
            int option = JOptionPane.showOptionDialog(this, "erease ?", null,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_pane, options_pane[0]);
            if (option == 0) {
                    DefaultTableModel defaultTableModel;
                    defaultTableModel = (DefaultTableModel) getTable().getModel();
                    defaultTableModel.removeRow(row);
                    db.getArrayShoppingcart().remove(row);
                    db.upDateOrderDbAdmin();
                }
        }
    }
}
