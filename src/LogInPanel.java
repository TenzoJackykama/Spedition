import javax.swing.*;
/**
 * Classe creazione scheletro pannello
 * astratta in quanto definisce lo scheletro comune ai tipi di log in
 * @author Bulla Andrea
 * @version 1.0.0
 */
public abstract class LogInPanel extends JPanel{


    // Tipo di log in richiesto

    String typelog;

    private JLabel labelName = new JLabel();
    protected JTextField nameField = new JTextField(20);

    private final JLabel lablePassword = new JLabel("password");
    private final JTextField textFieldPassword = new JTextField(20);

    protected JButton continueButton = new JButton();
    protected  JButton switchLog = new JButton("back");


    private boolean added;

    /**
     * Costruttore pannello log in
     * @param string  String Dedicto al tipo di log in
     */
    public LogInPanel(String string){
        super();
        typelog = string;
        labelName.setText(string);
        continueButton.setText("continue");

    }

    /**
     * Imposta pannello log in generale
     */
    public void setPanel(){
        added = true;
        add(labelName);
        add(nameField);
        add(lablePassword);
        add(textFieldPassword);
        add(switchLog);
        add(continueButton);

        setVisible(true);

    }

    /**
     * Imposta oggetto destinato ascolto eventi
     * @param obj Oggetto per ascolto eventi
     */
    public abstract void addAction(Object obj);

    /**
     * Ritorna il text Field
     * @return Text field password
     */
    public JTextField getTextFieldPassword() {
        return textFieldPassword;
    }

    /**
     * Ritorna il text Field
     * @return Text field password
     */
    public JLabel getLabelName() {
        return labelName;
    }

    /**
     * Ritorna il text Field
     * @return Text field password
     */
    public JLabel getLablePassword() {
        return lablePassword;
    }

    /**
     * Ritorna campo added relativo al pannello
     * @return Campo added
     */
    public boolean isAdded() {
        return added;
    }

    /**
     * Imposta campo added relativo al pannello
     */
    public void setAdded(boolean added) {
        this.added = added;
    }

    /**
     * Imposta contenuto e istanzia
     * @param labelName
     */
    public void setLabelName(String labelName) {
        this.labelName = new JLabel(labelName);
    }

    /**
     * Ritorna il campo nome
     * @return NameField
     */
    public JTextField getNameField() {
        return nameField;
    }




}