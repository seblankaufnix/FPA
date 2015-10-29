package aufgabenplaner;

import javax.swing.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class VorhabenUI extends GeneralUI {

    public VorhabenUI(JFrame parent, String id, String remainingHours, String hoursWorked, String status, String[] partOf){
        super(parent,"Vorhaben erfassen/aendern");
        build(id, remainingHours, hoursWorked, status, partOf);
    }

    private void build(String id, String remainingHours, String hoursWorked, String status, String[] partOf){
        super.id.setText(id);
        super.id.setEditable(false);
        super.remainingHours.setText(remainingHours);
        super.remainingHours.setEditable(false);
        super.hoursWorked.setText(hoursWorked);
        super.hoursWorked.setEditable(false);
        super.status.setText(status);
        super.status.setEditable(false);
        super.partOf.setModel(new JComboBox<>(partOf).getModel());
        super.partOf.setEditable(false);

    }
}
