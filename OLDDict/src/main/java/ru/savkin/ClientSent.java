/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 *
 * @author dm
 */
public class ClientSent {

    static String term;
    static String command;
    static DataOutputStream dataOut;
    static DataInputStream dataIn;

    public static boolean createSocket(String host, Integer portInt) {
        if (host == null) {
            new Error(Client.frame, Error.HOST_ERROR, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        Socket sock = null;
        try {
            sock = new Socket(host, portInt);

        } catch (UnknownHostException ex) {
            new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.WARNING_MESSAGE);
            return false;
        } catch (IOException ex) {
            new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            dataIn = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
            dataOut = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            return true;
        } catch (IOException ex) {
            new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static void sentMessage(String term, String definition, String command) {
        Error.errorMessage(Error.CHECK_TERM_AND_DEFINITION);
        Error.errorMessage(Error.CHECK_HOST);
        try {
            String query = command + "]" + term + "[" + definition;

            dataOut.writeUTF(query);
            dataOut.flush();
        } catch (IOException ex) {
            new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void sentMessage(String term, String command) {
        Error.errorMessage(Error.CHECK_TERM);
        Error.errorMessage(Error.CHECK_HOST);
        String query = command + "]" + term + "[";
        try {
            dataOut.writeUTF(query);
            dataOut.flush();
            String answer = dataIn.readUTF();
            Client.definitionField.setText(answer);
        } catch (IOException ex) {
            new Error(Client.frame, Error.SERVER_ERROR, JOptionPane.ERROR_MESSAGE);
        }

    }
}
