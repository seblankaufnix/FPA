package aufgabenplaner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Aufgabenplaner");
        GeneralUI schrittUI = new SchrittUI(frame, "123","kappa","not done", new String[]{"this","that"});
        GeneralUI vorhabenUI = new VorhabenUI(frame, "99","1","kappa","not done", new String[]{"this","that"});
    }
}
