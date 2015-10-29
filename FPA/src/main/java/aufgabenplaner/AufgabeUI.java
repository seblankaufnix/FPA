package aufgabenplaner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class AufgabeUI extends ContentFactory{

    final protected JTextField id, title, remainingHours,hoursWorked, status, endDate;
    final protected JComboBox<String> partOf;
    final protected JTextArea description;
    final protected JPanel buttons;


    public AufgabeUI(JFrame parent, String title, String endLabel) {
        super(parent,title);
        id = textFieldFactory("ID:");
        this.title = textFieldFactory("Titel:");
        description = textAreaFactory("Beschreibung:");
        partOf = comboBoxFactory("Teil von Vorhaben:");
        remainingHours = textFieldFactory("RestStunden:");
        hoursWorked = textFieldFactory("Ist-Stunden:");
        status = textFieldFactory("Status:");
        endDate = textFieldFactory(endLabel);
        buttons = buttonPanelFactory(new String[]{"Erfassen","Aendern"});
        super.jd.pack();
        super.jd.setSize(400, 700);
        super.jd.setVisible(true);
    }


}
