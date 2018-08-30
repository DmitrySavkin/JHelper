package ru.savkin.localapp;

import ru.savkin.API;
import ru.savkin.Definition;
import ru.savkin.DocumentReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The main frame of system. This is GUI to use the information system.
 * The user can find the term, remove it.
 * Each term is editable.
 * The user can also add a new term to a system.
 * @author dmitrysavkin
 * @version  1.1
 *
 */
public final class Window extends JFrame {


    private  List<Definition> definitionList;
    private final GridBagConstraints constraints = new GridBagConstraints();
    private  DefinitionWindow addDefinitionWindow;
    private DefinitionWindow editDefinitionWindow;
    private JTextField defField;
    private JEditorPane textArea;
    private Window parent;


    public Window() {

        this.parent = this;
        super.setLayout(new GridBagLayout());

        upperPanel();
        leftPanel();
        rightPanel();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void rightPanel() {
      /*
        JButton buttonBold = new JButton("B");
        JButton buttonItalic = new JButton("I");
        JButton buttonUnderline = new JButton("U");
        buttonBold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonItalic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        buttonUnderline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JPanel upper = new JPanel();
        Border etched = BorderFactory.createEtchedBorder();
        upper.setBorder(etched);
        upper.setLayout(new GridLayout(1,3));
        upper.add(buttonBold);
        upper.add(buttonItalic);
        upper.add(buttonUnderline);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 10;
        constraints.gridwidth = 4;
        constraints.insets = new Insets(1,30,0,30);
        add(upper, constraints);
        */
        textArea = new JEditorPane();
        textArea.setSize(100,Short.MAX_VALUE * 10000);
        textArea.setContentType("text/html");
        textArea.setPreferredSize(new Dimension(300,300));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridheight = 110;
        constraints.gridwidth = 40;
        constraints.insets = new Insets(5,30,105,30);
        add(textArea,constraints);


    }

    private void leftPanel() {
        int startPosition = 8;
        JButton add = new JButton("Add");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = startPosition;
        constraints.gridx = 0;
        add(add, constraints);
        final JButton remove = new JButton("Remove");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = startPosition + 1;
        constraints.gridx = 0;
        add(remove, constraints);
        final JButton edit = new JButton("Edit");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = startPosition + 2;
        constraints.gridx = 0;
        add(edit, constraints);
        JButton settings = new JButton("Settings");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = startPosition + 10;
        constraints.gridx = 0;
        add(settings, constraints);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addDefinitionWindow == null) {
                    addDefinitionWindow = new AddDefinitionWindow(parent);
                }
                addDefinitionWindow.setVisible(true);
            }
        });
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int r  = JOptionPane.showConfirmDialog(parent, "Do you want to remove this definition?", "Removing", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                  remove();
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (defField.getText().isEmpty()) {
                    throw new IllegalArgumentException("No text");
                }
                if (editDefinitionWindow == null) {
                    editDefinitionWindow = new EditDefinitionWindow(parent);

                }
                editDefinitionWindow.init();
            }
        });
    }

    private void remove() {
        Definition definition = new Definition(defField.getText(), textArea.getText());
        API.remove(definition);
        defField.setText("");
        textArea.setText("");
    }

    private void upperPanel() {

        JLabel definion = new JLabel(" Definition");
        defField = new JTextField(45);
        defField.setScrollOffset(5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.insets = new Insets(5,30,5,10);
        add(definion, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.gridx = 1;
        add(defField, constraints);
        JButton searchButton = new JButton("Search");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 0;
        constraints.gridx = 3;
        constraints.insets = new Insets(5,10,5,30);
        add(searchButton, constraints);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getResult();

            }
         });

    }

    private final void getResult() {

       Definition definition =  API.getSearchResult(defField.getText());

        if  (definition == null) {
            JOptionPane.showMessageDialog(parent, "No matches", "Error",JOptionPane.ERROR_MESSAGE);
        } else {
            textArea.setText(definition.getDescription());
        }
    }


    /**
     * Gets the definition field(search field)
     * @return the java components
     */
    public JTextField getDefField() {
        return defField;
    }

    /**
     * Gets the description field(search field)
     * @return the java components
     */
    public JEditorPane getTextArea() {
        return textArea;
    }

    /**
     * Gets the window to add new term
     * @return the java components
     */
    public DefinitionWindow getAddDefinitionWindow() {
        return addDefinitionWindow;
    }

    /**
     * Gets the window to edit existing term
     * @return the java components
     */
    public DefinitionWindow getEditDefinitionWindow() {
        return editDefinitionWindow;
    }

    /**
     * Gets list of term contains into the system
     * @return the terms
     */
    public List<Definition> getDefinitionList() {
        return definitionList;
    }

    /**
     * Entry points
     * @param args not nessessary for this version
     */
    public static void main(String[] args) {
        new Window();
    }
}
