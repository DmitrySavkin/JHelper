package ru.savkin.localapp;

import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import ru.savkin.API;
import ru.savkin.Definition;
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

    private  DefinitionWindow addDefinitionWindow;
    private DefinitionWindow editDefinitionWindow;
    private JTextField defField;
    private JTextArea textArea;
    private Window parent;

    public Window() {

        this.parent = this;
        setLayout(new GridBagLayout());
        //setLayout(new BorderLayout());
        upperPanel();
        leftPanel();
        rightPanel();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private  void upperPanel() {
        JPanel upper = new JPanel();
        upper.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JLabel definion = new JLabel(" Definition");
        defField = new JTextField();
        defField.setPreferredSize(new Dimension(650,21));
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getResult();
            }
        });
        c.fill =  GridBagConstraints.HORIZONTAL;
        c.gridx  = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.gridwidth = 1;
        upper.add(definion, c);
        c.gridx = 2;
        c.weightx = 36;
        c.gridwidth = 2;
        c.insets = new Insets(0,13,0,10);

        upper.add(defField, c);
        c.gridx = 22;
        c.gridwidth = 3;
        c.weightx = 0.5;
        upper.add(searchButton, c);
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(upper, c);
    }

    private  void leftPanel() {
        int startPosition = 8;
        JPanel left = new JPanel();
        left.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton add = new JButton("Add");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = startPosition;
        c.gridx = 0;
        left.add(add, c);
        final JButton remove = new JButton("Remove");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = startPosition + 1;
        c.gridx = 0;
        left.add(remove, c);
        final JButton edit = new JButton("Edit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = startPosition + 2;
        c.gridx = 0;
        left.add(edit, c);
        JButton settings = new JButton("Settings");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = startPosition + 10;
        c.gridx = 0;
        left.add(settings, c);
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
                    //remove();
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

        JPanel right = new JPanel();
        //right.setLayout(new GridBagLayout());

        textArea = new JTextArea();
       // textArea.setSize(100,Short.MAX_VALUE * 10000);
        textArea.setMinimumSize(new Dimension(750,700));
        textArea.setPreferredSize(new Dimension(750,700));

        c.gridx = 4;
        int resizing = 160;
        c.gridwidth = 60;
        c.gridheight = resizing;
        c.weightx = 60;
        c.weighty = resizing;
        c.gridx = resizing;
        c.gridy = 1;
        c.insets = new Insets(0,0,5,5);
        left.add(textArea, c);
        c.gridx =0;
        c.gridy =1;
        getContentPane().add(left, c);
    }

    private void rightPanel() {
        //JTextField fgv = new JTextField(45);

       // add(right, BorderLayout.CENTER);
        System.out.println("r");
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
    public JTextArea getTextArea() {
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



}
