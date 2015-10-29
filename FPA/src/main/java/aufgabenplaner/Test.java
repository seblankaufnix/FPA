package aufgabenplaner;

import javax.swing.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class Test {
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Aufgabenplaner");
        AufgabeUI schrittUI = new SchrittUI(frame, "123","not done","now", new String[]{"this","that"});
        AufgabeUI vorhabenUI = new VorhabenUI(frame, "123","99","1","not done", new String[]{"this","that"});

        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
