/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.savkin;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dm
 */
@Deprecated
public class Error {

    public final static String FILE_ERROR = "File isn't choose";
    public final static String FILE_UNCORRECT = "Uncorrect file";
    public final static String DEDFINITION_ERROR = "Definition is empty";
    public final static String HOST_ERROR = "Host is empty";
    public final static String PORT_UNCORRECT = "Uncorrect port";
    public final static String READING_ERROR = "Error by reading;";
    public final static String SERVER_ERROR = "Servet no found";
    public final static String TERM_ERROR = "Term is empty";
    public final static String WRITTING_ERROR = "Error by writing";
    public final static int CHECK_TERM_AND_DEFINITION = 1;
    public final static int CHECK_TERM = 2;
    public final static int CHECK_HOST = 3;

    Error(JFrame frame, String message, int type) {
        JOptionPane.showMessageDialog(frame, message, "Error", type);
    }

    public static void errorMessage(int i) {
        switch (i) {
            case 1:
                if (Client.definitionField.equals("")) {
                    new Error(Client.frame, Error.DEDFINITION_ERROR, JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (Client.searchField.equals("")) {
                    new Error(Client.frame, Error.TERM_ERROR, JOptionPane.WARNING_MESSAGE);

                    return;
                }
                break;
            case 2:
                if (Client.searchField.equals("")) {
                    new Error(Client.frame, Error.TERM_ERROR, JOptionPane.WARNING_MESSAGE);

                    return;
                }
                break;
            case 3:
                    if (ClientSent.dataOut == null || ClientSent.dataIn == null) {
            new Error(Client.frame, Error.HOST_ERROR, JOptionPane.WARNING_MESSAGE);
            return;
        }break;
            default:
        }
    }
}
