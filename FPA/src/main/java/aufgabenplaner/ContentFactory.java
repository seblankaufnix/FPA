package aufgabenplaner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sebastian on 23.10.2015.
 */
public class ContentFactory {

    Container content;
    protected JDialog jd;

    public ContentFactory(JFrame parent, String title){
        jd = new JDialog(parent, title);
        this.content = jd.getContentPane();
        content.setLayout(new BoxLayout(content,BoxLayout.Y_AXIS));
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    protected JTextField textFieldFactory(String title){
        labelFactory(title);
        JTextField field = new JTextField();
        content.add(field);
        return field;
    }

    protected JTextArea textAreaFactory(String title){
        labelFactory(title);
        JTextArea area = new JTextArea(4,50);
        area.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(area);
        return area;
    }

    protected JComboBox<String> comboBoxFactory(String title){
        labelFactory(title);
        JComboBox<String> box = new JComboBox<>(new String[5]);
        content.add(box);
        return box;
    }

    protected JPanel buttonPanelFactory(String[] titles){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(String title : titles){
            JButton button = new JButton(title);
            buttonPanel.add(button);
        }
        content.add(buttonPanel);
        return buttonPanel;
    }

    protected JLabel labelFactory(String title){
        JLabel label = new JLabel(title);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(label);
        return label;
    }

}
