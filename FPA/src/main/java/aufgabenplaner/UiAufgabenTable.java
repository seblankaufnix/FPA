package aufgabenplaner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Seby on 30.10.2015.
 */
public class UiAufgabenTable extends JTable {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                final UiAufgabenTable table = new UiAufgabenTable(null);
                table.fillWithTestData();
                final JDialog thisDialog = new JDialog();
                thisDialog.setTitle("Test UiAufgabenTable");
                final Container container = thisDialog.getContentPane();
                final JScrollPane scrollPane = new JScrollPane(table);
                container.add(scrollPane, BorderLayout.CENTER);
                thisDialog.pack();
                thisDialog.setVisible(true);
            }
        });
    }

    public UiAufgabenTable(TableModel tm){
        super(tm);
    }

    public void fillWithTestData(){
        final Object[][] rowData = {
                {"1", "Schritt 1: Vorbereiten", "0", "erledigt"},
                {"2", "Vorhaben: Bearbeiten", "2", "in Bearbeitung"},
                {"5", "Schritt 3: Beenden", "0", "in Bearbeitung"}
        };
        final Object[] columnNames = {
                "ID","Titel","#Teile","Status"
        };
        setModel(new DefaultTableModel(rowData, columnNames));
        getColumnModel().getColumn(0).setMaxWidth(25);
        getColumnModel().getColumn(2).setMaxWidth(50);
    }


}
