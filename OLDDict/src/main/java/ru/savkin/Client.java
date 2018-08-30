/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

/**
 * j
 *
 * @author dmitriy
 */
@Deprecated
public class Client extends JFrame {

    public static JFrame frame;
    public static JTextField fileField;
    public static JTextField searchField;
    public static JTextField portField;
    public static JTextField pf;
    public static JTextArea definitionField;
    public static JButton openFile;
    public static JRadioButton fromFile, fromDB;
    public static JLabel portLabel, fileLabel, passLabel;
    public static JTabbedPane tabbedPane;
    public static int i = 1;

    Client() {
        setTitle("JHelp");
        setSize(740, 440);
        setLocation(350, 250);

        Dimension dm = new Dimension();
        Container c = getContentPane();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        JLabel term = new JLabel("Term");
        JLabel def = new JLabel("Definition");
        portLabel = new JLabel("Port");
        passLabel = new JLabel("password");
        fileLabel = new JLabel("File");
        searchField = new JTextField(50);

        searchField.getPreferredSize();
        definitionField = new JTextArea(10, 45);
        fromFile = new JRadioButton("From File");
        fromFile.setSelected(true);
        fromFile.setActionCommand("fromFile");
        fromFile.addActionListener(new ClientListener());

        fromDB = new JRadioButton("From DataBase");
        fromDB.setActionCommand("fromDB");
        fromDB.addActionListener(new ClientListener());
        ButtonGroup group = new ButtonGroup();
        group.add(fromFile);
        group.add(fromDB);

        fileField = new JTextField(20);
        openFile = new JButton("Open");
        openFile.setActionCommand("open");
        openFile.addActionListener(new ClientListener());
        dm.setSize(100, 20);
        //openFile.setPreferredSize(dm.getSize());
        openFile.getPreferredSize();
        JScrollPane scrollPane = new JScrollPane(definitionField);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JMenuBar menu = new JMenuBar();
        JMenu fileM = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open file");
        openItem.setActionCommand("open");
        openItem.addActionListener(new ClientListener());
        JMenuItem databaseItem = new JMenuItem("Database");
        databaseItem.setActionCommand("database");
        databaseItem.addActionListener(new ClientListener());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setActionCommand("exit");
        exitItem.addActionListener(new ClientListener());
        fileM.add(openItem);
        fileM.add( databaseItem);
        fileM.addSeparator();
        fileM.add(exitItem);
        JMenu editM = new JMenu("Edit");
        JMenuItem addItem = new JMenuItem("Add");
        addItem.setActionCommand("add");
        addItem.addActionListener(new ClientListener());
        JMenuItem editItem = new JMenuItem("Edit");
        editItem.setActionCommand("edit");
        editItem.addActionListener(new ClientListener());
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.setActionCommand("delete");
        deleteItem.addActionListener(new ClientListener());
        JMenuItem nextItem = new JMenuItem("Next");
        nextItem.setActionCommand("next");
        nextItem.addActionListener(new ClientListener());
        JMenuItem previousItem = new JMenuItem("Previous");
        previousItem.setActionCommand("previous");
        addItem.addActionListener(new ClientListener());
        editM.add(addItem);
        editM.add(editItem);
        editM.add(deleteItem);
        editM.add(nextItem);
        editM.add(previousItem);
        JMenu helpM = new JMenu("Help");
        JMenuItem helpItem = new JMenuItem("Help");

        helpM.add(helpItem);

        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.setActionCommand("about");

        aboutItem.addActionListener(new ClientListener());


        helpM.add(aboutItem);
        menu.add(fileM);
        menu.add(editM);
        menu.add(helpM);
        setJMenuBar(menu);


        JButton search = new JButton("Search");
        search.setActionCommand("search");
        search.addActionListener(new ClientListener());
        search.setSize(dm);
        JButton add = new JButton("Add");
        add.setActionCommand("add");
        add.addActionListener(new ClientListener());

        JButton edit = new JButton("Edit");
        edit.setActionCommand("edit");
        edit.addActionListener(new ClientListener());

        JButton delete = new JButton("Delete");
        delete.setActionCommand("delete");
        delete.addActionListener(new ClientListener());

        JButton next = new JButton("Next");
        next.setActionCommand("next");
        next.addActionListener(new ClientListener());
        //next.setSize(dm);
        JButton prevous = new JButton("Previous");
        prevous.setActionCommand("previous");
        prevous.addActionListener(new ClientListener());
        //JButton exit = new JButton("Exit");
        prevous.setSize(dm);
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(9, 1, 5, 5));
        button.add(add);
        button.add(edit);
        button.add(delete);
        button.add(next);
        button.add(prevous);


        Box b1 = Box.createHorizontalBox();
        b1.add(term);
        b1.add(Box.createHorizontalStrut(5));
        b1.add(searchField);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(search);
        b1.add(Box.createHorizontalStrut(10));


        Box b2 = Box.createHorizontalBox();
        b2.add(Box.createHorizontalStrut(2));
        b2.add(scrollPane);
        b2.add(Box.createHorizontalStrut(23));
        b2.add(button);
        b2.add(Box.createHorizontalStrut(10));

        Box b3 = Box.createVerticalBox();
        b3.add(b1);
        b3.add(Box.createVerticalStrut(10));
        b3.add(def);
        b3.add(Box.createVerticalStrut(5));
        b3.add(b2);
        b3.add(Box.createVerticalGlue());
        //but.setLayout();
        p1.add(b3, BorderLayout.CENTER);
        add(p1);
        Box b6 = Box.createHorizontalBox();
        b6.add(fromFile);
        b6.add(Box.createHorizontalStrut(15));
        b6.add(fromDB);

        portField = new JTextField(10);
        pf = new JTextField(20);
        portField.setPreferredSize(dm.getSize());
        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(3, 3, 3, 3));
        pan.add(fileLabel);
        pan.add(fileField);
        pan.add(openFile);
        pan.add(portLabel);
        pan.add(portField);
        pan.add(new JLabel());
        pan.add(passLabel);

        pan.add(pf);
        Box b4 = Box.createVerticalBox();
        b4.add(b6);
        b4.add(Box.createVerticalStrut(10));
        b4.add(pan);


        p2.add(b4, BorderLayout.CENTER);

        portField.setVisible(false);
        pf.setVisible(false);
        portLabel.setVisible(false);
        passLabel.setVisible(false);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Main", p1);
        tabbedPane.add("Setting", p2).setPreferredSize(dm);

        c.add(tabbedPane);
        pack();
        setVisible(true);
        frame = this;
    }

    public static void main(String[] args) {
        Client c = new Client();
        c.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
