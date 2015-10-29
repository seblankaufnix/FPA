package aufgabenplaner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sebastian on 23.10.2015.
 */
public abstract class ContentFactory implements ActionListener{

    protected final Container content;
    protected final JDialog jd;

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public ContentFactory(JFrame parent, String title){
        jd = new JDialog(parent, title);
        this.content = jd.getContentPane();
        content.setLayout(new GridLayout(17, 1));
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
            button.setActionCommand(title);
            button.addActionListener(this);
        }
        content.add(buttonPanel);
        return buttonPanel;
    }

    protected JLabel labelFactory(String title){
        JLabel label = new JLabel(title);
        label.setAlignmentX(Container.LEFT_ALIGNMENT);
        content.add(label);
        return label;
    }
}
