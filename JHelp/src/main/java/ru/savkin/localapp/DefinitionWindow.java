package ru.savkin.localapp;

import ru.savkin.DocumentReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This class provides the avaible to mske dialogs to manipulate the terms.
 * @version  1.0
 * @author dmitrysavkin
 */
public abstract class DefinitionWindow extends JDialog {


    private final GridBagConstraints constraints = new GridBagConstraints();

    private final Window frame;
    private JEditorPane editorPane;
    private JTextField defField;

    /**
     * Creates new dialog
     * @param frame the main window
     * @param title the name of current dialog
     */
    public DefinitionWindow(Window frame, String title) {
        this.frame = frame;
        setTitle(title);
        super.setLayout(new GridBagLayout());

    }

    /**
     * Provides and shows the dialog with defaults settings;
     */
    public final void  setWindows() {
        JLabel definition = new JLabel("Definition");
        defField = new JTextField(40);
        JLabel describe = new JLabel("Describe");
        editorPane = new  JEditorPane();
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(definition, constraints);
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(defField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(describe,constraints);
        editorPane = new JEditorPane();
        editorPane.setEditorKit( new HTMLEditorKit());
        JScrollPane sp1 = new JScrollPane(editorPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        editorPane.setSize(100,Short.MAX_VALUE * 10000);
        editorPane.setContentType("text/html");
        editorPane.setPreferredSize(new Dimension(300,100));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridheight = 10;
        constraints.gridwidth = 40;
        constraints.insets = new Insets(5,0,35,0);

        add(sp1, constraints);
        constraints.gridx = 5;
        constraints.gridy = 50;
        constraints.insets = new Insets(0,0,0,0);
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okMethod();
                setVisible(false);
            }
        });
        add(ok,constraints);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        constraints.gridx = 6;
        constraints.gridy = 50;
        add(cancel,constraints);
        pack();
    }

    /**
     * Action by click OK Button
     */
    protected abstract void okMethod();

    /**
     * Makes the dialog visible
     */
    public void init() {
        setVisible(true);
    }

    /**
     * Gets the layout
     * @return the gridbagcontraints with user settings
     */
    public GridBagConstraints getConstraints() {
        return constraints;
    }

    /**
     * Gets describing field
     * @return the java components
     */
    public JEditorPane getEditorPane() {
        return editorPane;
    }

    /**
     * Get definition field. The title of terms
     * @return the java components
     */
    public JTextField getDefField() {
        return defField;
    }

    /**
     * Gets main frame
     * @return the java components
     */
    public Window getFrame() {
        return frame;
    }

    private class ToolBar extends JToolBar {

        public  ToolBar()  {
            JButton list = new JButton();
            list.setPreferredSize(new Dimension(3,3));
            try {
                Image play = null;
                play = ImageIO.read(getClass().getResource("icons/list.png"));
                list.setIcon(new ImageIcon(play));
            } catch (IOException e) {
                e.printStackTrace();
            }

            add(list);
        }
    }
}
