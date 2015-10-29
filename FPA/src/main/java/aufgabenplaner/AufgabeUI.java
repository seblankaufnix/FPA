package aufgabenplaner;

import com.sun.javaws.exceptions.MultipleHostsException;
import multex.Exc;
import multex.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.err.println(new Date() + this.getClass().getName() + " <init>");
        System.err.println("INFORMATION");
        System.out.println(e.getActionCommand() + " Schritt " + title.getText());

        try{
            actionPerformedwithTrows();
        }catch (Exc exc){
            Swing.report(new JDialog(), exc);
        }

    }

    public void actionPerformedwithTrows() throws Exc {
        System.out.println("Erledigen des Schritts " + title.getText());
        throw new Exc("Diese Aufgabe ist zu schwer");
    }


}
