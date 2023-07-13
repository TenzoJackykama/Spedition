import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Random;

/**
 * Classe CustomTabelCellrendere
 * Rende le linee con il clore associato al tipo di stato della spedizione
 * @author Bulla Andrea
 * @version 1.0.0
 */
class CustomTabelCellrendere extends DefaultTableCellRenderer {

    private boolean showSelected = false;
    private int stats_column = 5;

    protected String [] stats = new String[]{"IN PREPARAZIONE", "IN TRANSITO", "RICEVUTA", "FALLITA", "RIMBORSO RICHIESTO", "RIMBORSO EROGATO"};

    Random rand = new Random();


    @Override
    public Component getTableCellRendererComponent (JTable table,
                                                    Object value,boolean isSelected, boolean hasFocus, int row, int column) {


        Component superComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        String state = (String) table.getValueAt(row, stats_column);

        switch (state) {
            case "IN PREPARAZIONE": {
                superComponent.setBackground(Color.green);
            }
            break;
            case "IN TRANSITO": {
                superComponent.setBackground(Color.cyan);
            }
            break;
            case "RICEVUTA": {
                superComponent.setBackground(Color.PINK);
            }
            break;
            case "FALLITA": {
                superComponent.setBackground(Color.red);
            }
            break;
            case "RIMBORSO RICHIESTO": {
                superComponent.setBackground(Color.orange);
            }
            break;
            case "RIMBORSO EROGATO": {
                superComponent.setBackground(Color.yellow);
            }
            break;
        }
        return superComponent;
    }

}