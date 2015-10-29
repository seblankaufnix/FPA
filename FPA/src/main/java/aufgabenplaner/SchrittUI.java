package aufgabenplaner;

import javax.swing.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class SchrittUI extends AufgabeUI {

    public SchrittUI(JFrame parent, String id, String status, String endDate, String[] partOf){
        super(parent, "Schritt erfassen/aendern", "Erledigt-Zeitpunkt");
        build(id, status, endDate, partOf);
    }

    private void build(String id, String status, String endDate, String[] partOf){
        super.id.setText(id);
        super.id.setEditable(false);
        super.status.setText(status);
        super.status.setEditable(false);
        super.endDate.setText(endDate);
        super.endDate.setEditable(false);
        super.partOf.setModel(new JComboBox<>(partOf).getModel());
        super.partOf.setEditable(false);
        super.buttons.add(new JButton("Erledigen"));
    }
}
