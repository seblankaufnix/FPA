package aufgabenplaner;

import l4_dm.DmAufgabe;
import l4_dm.DmAufgabeStatus;
import l4_dm.DmSchritt;
import l4_dm.DmVorhaben;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Date;

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
        final Object[] columnNames = {
                "ID","Titel","#Teile","Status"
        };

        final DmVorhaben vorhaben = new DmVorhaben(){
            @Override
            public Long getId(){
                return 123L;
            }
        };
        vorhaben.setTitel("testVorhaben");
        vorhaben.setEndTermin(new Date(2000,1,1));
        final DefaultTableModel dtm = new DefaultTableModel(new Object[][]{}, columnNames);

        final DmAufgabe a1 = new DmSchritt(){
            @Override
            public Long getId(){
                return 321L;
            }
            @Override
            public DmAufgabeStatus getStatus(){
                return DmAufgabeStatus.inBearbeitung;
            }
        };
        a1.setTitel("Schritt 1: Vorbereiten");
        a1.setGanzes(vorhaben);
        final Object[] data1 ={a1.getId(), a1.getTitel(), a1.getAnzahlTeile(), a1.getStatus()};
        dtm.addRow(data1);

        final DmAufgabe a2 = new DmVorhaben(){
            @Override
            public Long getId(){
                return 123L;
            }
            @Override
            public DmAufgabeStatus getStatus(){
                return DmAufgabeStatus.erledigt;
            }
        };
        a2.setTitel("Vorhaben: Bearbeiten");
        a2.setGanzes(vorhaben);
        final Object[] data2 ={a2.getId(), a2.getTitel(), a2.getAnzahlTeile(), a2.getStatus()};
        dtm.addRow(data2);

        final DmAufgabe a3 = new DmSchritt(){
            @Override
            public Long getId(){
                return 12L;
            }
            @Override
            public DmAufgabeStatus getStatus(){
                return DmAufgabeStatus.neu;
            }
        };
        a3.setTitel("Schritt 3: Beenden");
        a3.setGanzes(vorhaben);
        final Object[] data3 = {a3.getId(), a3.getTitel(), a3.getAnzahlTeile(), a3.getStatus()};
        dtm.addRow(data3);



        setModel(dtm);
        getColumnModel().getColumn(0).setMaxWidth(25);
        getColumnModel().getColumn(2).setMaxWidth(50);
    }


}
