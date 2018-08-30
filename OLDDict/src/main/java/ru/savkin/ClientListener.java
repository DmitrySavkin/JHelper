
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

/**
 *
 * @author dmitriy
 */
@Deprecated
public class ClientListener implements ActionListener {

    ClientSent cs;


    public void actionPerformed(ActionEvent e) {

        Data data = new Data();
        if (e.getActionCommand().equals("database")) {
            Client.tabbedPane.setSelectedIndex(1);
            Client.fromDB.setSelected(true);

            Client.openFile.setText("Connect");
            Client.openFile.setActionCommand("connect");
            Client.portLabel.setVisible(true);
            Client.portField.setVisible(true);
            Client.fileLabel.setText("URL");


        }
        if (e.getActionCommand().equals("add")) {
            Error.errorMessage(Error.CHECK_TERM_AND_DEFINITION);
            if (Client.fromFile.isSelected()) {
                data.add(Client.searchField.getText(), Client.definitionField.getText());
            } else {
                ClientSent.sentMessage(Client.searchField.getText(), Client.definitionField.getText(), "add");
            }
        }

        if (e.getActionCommand().equals("delete")) {
            Error.errorMessage(Error.CHECK_TERM_AND_DEFINITION);
            int i = JOptionPane.showConfirmDialog(Client.frame, "Are you sure?", "Delete", JOptionPane.OK_CANCEL_OPTION);
            if (i == JOptionPane.OK_OPTION) {
                if (Client.fromFile.isSelected()) {

                    data.delete(Client.searchField.getText());
                } else {
                    ClientSent.sentMessage(Client.searchField.getText(), Client.definitionField.getText(), "delete");
                    ClientSent.sentMessage(Client.searchField.getText(), "next");
                }
            }
        }
        if (e.getActionCommand().equals("edit")) {
            Error.errorMessage(Error.CHECK_TERM_AND_DEFINITION);
            if (Client.fromFile.isSelected()) {
                data.editFile(Client.searchField.getText(), Client.definitionField.getText());
            } else {
                ClientSent.sentMessage(Client.searchField.getText(), Client.definitionField.getText(), "edit");
            }
        }
        if (e.getActionCommand().equals("exit")) {
            Client.frame.dispose();
        }
        if (e.getActionCommand().equals("next")) {

            Error.errorMessage(Error.CHECK_TERM);

            if (Client.fromFile.isSelected()) {

                Definition.defin = Definition.definition.get(Client.i++).toString();
                Client.definitionField.setText(
                        Definition.defin);
                if (Client.i == (Definition.definition.size())) {
                    Client.i = 0;

                }

            } else {
                ClientSent.sentMessage(Client.searchField.getText(), "next");

            }
        }
        if (e.getActionCommand().equals("previous")) {

            if (Client.fromFile.isSelected()) {
                Definition.defin = Definition.definition.get(Client.i--).toString();
                Client.definitionField.setText(
                        Definition.defin);
                if (Client.i < 0) {
                    Client.i = (Definition.definition.size() - 1);
                }
            } else {
                ClientSent.sentMessage(Client.searchField.getText(), "previous");

            }

        }
        if (e.getActionCommand().equals("fromFile")) {
            Client.openFile.setText("Open");
            Client.openFile.setActionCommand("open");
            Client.portLabel.setVisible(false);
            Client.portLabel.setVisible(false);
            Client.portField.setVisible(false);
            Client.fileLabel.setText("File");
            Client.openFile.setVisible(true);
        }
        if (e.getActionCommand().equals("fromDB")) {

            Client.openFile.setText("Connect");
            Client.openFile.setActionCommand("connect");
            Client.portLabel.setVisible(true);
            Client.portField.setVisible(true);
            Client.fileLabel.setText("URL");

        }
        if (e.getActionCommand().equals("open")) {
            JFileChooser chooser = new JFileChooser();

            int returnVal = chooser.showOpenDialog(null);
            String chooseFile;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    chooseFile = chooser.getSelectedFile().getCanonicalPath();
                    chooseFile = chooser.getSelectedFile().getCanonicalPath();
                    Client.fileField.setText(chooseFile);
                    Definition.file = chooseFile;
                } catch (IOException ex) {
                    new Error(Client.frame, Error.FILE_UNCORRECT, JOptionPane.WARNING_MESSAGE);

                }
            }
        }

        if (e.getActionCommand().equals("disconnect")) {
 
            System.exit(0);

        }
        if (e.getActionCommand().equals("connect")) {
            Integer portInt = 0;

            String host = Client.fileField.getText();
            String port = Client.portField.getText();

            try {
                portInt = Integer.parseInt(port);

            } catch (NumberFormatException ex) {

                new Error(Client.frame, Error.PORT_UNCORRECT, JOptionPane.WARNING_MESSAGE);
            }
            boolean flag = ClientSent.createSocket(host, portInt);
            if (flag) {
                Client.openFile.setText("Disconnect");
                Client.openFile.setActionCommand("disconnect");
            } else {
                new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getActionCommand().equals("disconnect")) {
            Client.openFile.setText("Connect");
            Client.openFile.setActionCommand("connect");
        }
        if (e.getActionCommand().equals("search")) {
            Error.errorMessage(Error.CHECK_TERM_AND_DEFINITION);

            if (Client.fromFile.isSelected()) {

                String search = Client.searchField.getText();

                data.searchTerm(search);
                if (Definition.definition.size() > 0) {

                    Object definition = Definition.definition.get(0);
                    Client.definitionField.setText(definition.toString());
                    Definition.defin = definition.toString();
                } else {
                    Client.definitionField.setText("No found");
                }
            } else {
                new Error(Client.frame, Error.PORT_UNCORRECT, JOptionPane.WARNING_MESSAGE);

                ClientSent.sentMessage(Client.searchField.getText(), "search");

            }
        }
        if (e.getActionCommand().equals("about")) {
            final JDialog dialog = new JDialog();
            dialog.setSize(250, 150);
            dialog.setResizable(false);
            JLabel about = new JLabel("<html>"
                    + "<h2 style=\"color:blue;margin=30%\">"
                    + "St.Peterburg</h2>"
                    + "<p>"
                    + "<div style=\"color:blue;\">"
                    + "dimsaz@mail.ru</div>"
                    + "</html>");
            Box b = Box.createHorizontalBox();
            Box bv = Box.createVerticalBox();
            JButton ok = new JButton("Ok");
            ok.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            ok.setSize(100, 30);
            b.add(Box.createHorizontalStrut(40));
            b.add(about);
            bv.add(b);
            bv.add(Box.createVerticalStrut(10));
            bv.add(ok);
            bv.add(Box.createVerticalStrut(5));
            dialog.setLocation(400, 350);
            dialog.add(bv);
            dialog.setModal(true);
            dialog.setVisible(true);
        }


    }
}
