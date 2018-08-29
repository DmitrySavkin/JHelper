/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author dm
 */
public class Error {

    public final static String USER_ERROR="User not correct";
    public final static String PORT_ERROR="Port not correct";
    public final static String PASSWORD_ERROR="Password not correct";
    public final static String URL_ERROR="URL not correct";

    Error(JFrame frame, String message, int type) {
        JOptionPane.showMessageDialog(frame, message, "Error", type);
    }
}